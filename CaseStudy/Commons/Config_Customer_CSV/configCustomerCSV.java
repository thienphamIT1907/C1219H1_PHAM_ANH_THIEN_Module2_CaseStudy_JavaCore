package CaseStudy.Commons.Config_Customer_CSV;

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

public class configCustomerCSV {
    private static final String FILECUSTOMER = "src/CaseStudy/Data/Customer.csv";

    private static String[] customerHeaderArr = new String[]{
            "customerName",
            "customerBirthday",
            "customerGender",
            "customerIDCard",
            "customerNumberPhone",
            "customerEmail",
            "customerType",
            "customerAddress",
//            "customerUseService"
    };

    //WRITE DATA TO FILE
    public static void writeCustomerCSVFile(ArrayList<Customer> customerArrayList) {
        try (Writer writer = new FileWriter(FILECUSTOMER);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(customerHeaderArr);

            //loop array list to write header at new another array
            for (Customer customer : customerArrayList) {
                csvWriter.writeNext(new String[]{
                        customer.getCustomerName(),
                        customer.getCustomerBirthday(),
                        customer.getCustomerGender(),
                        customer.getCustomerIDCard(),
                        customer.getCustomerNumberPhone(),
                        customer.getCustomerEmail(),
                        customer.getCustomerType(),
                        customer.getCustomerAddress(),
//                        String.valueOf(customer.getCustomerUseService())
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<Customer> readCustomerCSVFile() {
        //check existion of file
        Path pathCustomerFile = Paths.get(FILECUSTOMER);
        if (!Files.exists(pathCustomerFile)) {
            try {
                Writer writer = new FileWriter(FILECUSTOMER);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<Customer> customerStrategy = new ColumnPositionMappingStrategy<>();
        customerStrategy.setType(Customer.class);
        customerStrategy.setColumnMapping(customerHeaderArr); // mapping data base on header

        CsvToBean<Customer> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<Customer>(new FileReader(FILECUSTOMER))
                    .withMappingStrategy(customerStrategy)
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
//        return ((ArrayList<Customer>)csvToBean.parse()); -> this way maybe produce "NullPointerException";
    }
}
