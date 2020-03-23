package CaseStudy.Commons.Config_Services_CSV;

import CaseStudy.Models.House;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;

import static CaseStudy.Commons.Config_Services_CSV.MainConfig.*;

public class configHouseCSV {
    private static final String FILEHOUSE = "src/CaseStudy/Data/House.csv";

    private static String[] houseHeaderArr = new String[]{
            "id", "nameService", "areaUsed", "rentalCosts",
            "maxNumberOfPeople", "typeRent", "roomStandard",
            "convenientDescription", "numberOfFloors"
    };

    //WRITE DATA TO FILE
    public static void writeHouseCSVFile(ArrayList<House> houseArrayList) {
        try (Writer writer = new FileWriter(FILEHOUSE);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(houseHeaderArr);

            //loop array list to write header at new another array
            for (House house : houseArrayList) {
                csvWriter.writeNext(new String[]{
                        house.getId(),
                        house.getNameService(),
                        String.valueOf(house.getAreaUsed()),
                        String.valueOf(house.getRentalCosts()),
                        String.valueOf(house.getMaxNumberOfPeople()),
                        house.getTypeRent(),
                        house.getRoomStandard(),
                        house.getConvenientDescription(),
                        String.valueOf(house.getNumberOfFloors())
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<House> readHouseCSVFile() {
        //check existion of file
        Path pathHouseFile = Paths.get(FILEHOUSE);
        if (!Files.exists(pathHouseFile)) {
            try {
                Writer writer = new FileWriter(FILEHOUSE);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<House> houseStrategy = new ColumnPositionMappingStrategy<>();
        houseStrategy.setType(House.class);
        houseStrategy.setColumnMapping(houseHeaderArr); // mapping data base on header

        CsvToBean<House> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<House>(new FileReader(FILEHOUSE))
                    .withMappingStrategy(houseStrategy)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withQuoteChar(DEFAULT_QUOTE)
                    .withSkipLines(NUMBER_LINE_SKIP)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assert csvToBean != null;
        return (ArrayList<House>) csvToBean.parse();
//        return ((ArrayList<house>)csvToBean.parse()); -> this way maybe produce "NullPointerException";
    }

    public static TreeSet<String> readHouseNotDuplicate(String pathFile) {
        BufferedReader br = null;
        TreeSet<String> result = new TreeSet<>();
        try {
            String line;
            br = new BufferedReader(new FileReader(pathFile));

            while((line = br.readLine()) != null) {
                if(readNameServiceFromFile(line).equals("nameService")) {
                    continue;
                }
                result.add(readNameServiceFromFile(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
        //sorted increased
        return result;

        //sorted decreased
//        return result.descendingSet();
    }

    //skip header
    private static String readNameServiceFromFile(String csvLine) {
        String name = "";
        if(csvLine != null) {
            String[] splitData = csvLine.split(",");
            name = splitData[1];
        }
        return name;
    }
}
