package CaseStudy.Commons.Config_Employee_CSV;

import CaseStudy.Models.Customer;
import CaseStudy.Models.Employee;
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

public class configEmployeeCSV {
    private static final String FILEEMPLOYEE = "src/CaseStudy/Data/Employee.csv";

    private static String[] employeeHeaderArr = new String[]{
            "employeeID",
            "employeeName",
            "employeeAge",
            "employeeAddress",
    };

    //WRITE DATA TO FILE
    public static void writeEmployeeCSVFile(ArrayList<Employee> employeeArrayList) {
        try (Writer writer = new FileWriter(FILEEMPLOYEE);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(employeeHeaderArr);

            //loop array list to write header at new another array
            for (Employee employee : employeeArrayList) {
                csvWriter.writeNext(new String[]{
                        employee.getEmployeeID() + "",
                        employee.getEmployeeName(),
                        employee.getEmployeeAge() + "",
                        employee.getEmployeeAddress()
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<Employee> readEmployeeCSVFile() {
        //check existion of file
        Path pathEmployeeFile = Paths.get(FILEEMPLOYEE);
        if (!Files.exists(pathEmployeeFile)) {
            try {
                Writer writer = new FileWriter(FILEEMPLOYEE);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<Employee> employeeStrategy = new ColumnPositionMappingStrategy<>();
        employeeStrategy.setType(Employee.class);
        employeeStrategy.setColumnMapping(employeeHeaderArr); // mapping data base on header

        CsvToBean<Employee> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<Employee>(new FileReader(FILEEMPLOYEE))
                    .withMappingStrategy(employeeStrategy)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withQuoteChar(DEFAULT_QUOTE)
                    .withSkipLines(NUMBER_LINE_SKIP)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assert csvToBean != null;
        return (ArrayList<Employee>) csvToBean.parse();
//        return ((ArrayList<Customer>)csvToBean.parse()); -> this way maybe produce "NullPointerException";
    }
}
