package CaseStudy.Commons.Config_Services_CSV;

import CaseStudy.Models.Customer;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static CaseStudy.Commons.Config_Services_CSV.MainConfig.*;

public class configBookingCSV {
    private static final String FILEBOOKING = "src/CaseStudy/Data/Booking.csv";

    private static String[] bookingHeaderArr = new String[]{
            "customerName",
            "customerBirthday",
            "customerGender",
            "customerIDCard",
            "customerNumberPhone",
            "customerEmail",
            "customerType",
            "customerAddress",
            "id",
            "nameService",
            "areaUsed",
            "rentalCosts",
            "maxNumberOfPeople",
            "typeRent"
    };

    //WRITE DATA TO FILE
    public static void writeBookingCSVFile(ArrayList<Customer> customerArrayListArrayList) {
        try (Writer writer = new FileWriter(FILEBOOKING);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(bookingHeaderArr);

            //loop array list to write header at new another array
            for (Customer customer : customerArrayListArrayList) {
                csvWriter.writeNext(new String[]{
                        customer.getCustomerName(),
                        customer.getCustomerBirthday(),
                        customer.getCustomerGender(),
                        customer.getCustomerIDCard(),
                        customer.getCustomerNumberPhone(),
                        customer.getCustomerEmail(),
                        customer.getCustomerType(),
                        customer.getCustomerAddress(),
                        customer.getCustomerUseService().getId(),
                        customer.getCustomerUseService().getNameService(),
                        String.valueOf(customer.getCustomerUseService().getAreaUsed()),
                        String.valueOf(customer.getCustomerUseService().getRentalCosts()),
                        String.valueOf(customer.getCustomerUseService().getMaxNumberOfPeople()),
                        String.valueOf(customer.getCustomerUseService().getMaxNumberOfPeople()),
                        customer.getCustomerUseService().getTypeRent()
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<Customer> readBookingCSVFile() {
        //check existion of file
        Path pathBookingFile = Paths.get(FILEBOOKING);
        if (!Files.exists(pathBookingFile)) {
            try {
                Writer writer = new FileWriter(FILEBOOKING);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<Customer> bookingStrategy = new ColumnPositionMappingStrategy<>();
        bookingStrategy.setType(Customer.class);
        bookingStrategy.setColumnMapping(bookingHeaderArr); // mapping data base on header

        CsvToBean<Customer> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<Customer>(new FileReader(FILEBOOKING))
                    .withMappingStrategy(bookingStrategy)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withQuoteChar(DEFAULT_QUOTE)
                    .withSkipLines(NUMBER_LINE_SKIP)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assert csvToBean != null;
        return (ArrayList<Customer>) csvToBean.parse();
//        return ((ArrayList<customer>)csvToBean.parse()); -> this way maybe produce "NullPointerException";
    }
}
