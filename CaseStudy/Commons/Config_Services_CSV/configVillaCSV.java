package CaseStudy.Commons.Config_Services_CSV;

import CaseStudy.Models.Villa;
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

public class configVillaCSV {
    private static final String FILEVILLA = "src/CaseStudy/Data/Villa.csv";

    private static String[] villaHeaderArr = new String[]{
            "id", "nameService", "areaUsed", "rentalCosts",
            "maxNumberOfPeople", "typeRent", "roomStandard",
            "convenientDescription", "areaPool", "numberOfFloors"
    };

    //WRITE DATA TO FILE
    public static void writeVillaCSVFile(ArrayList<Villa> villaArrayList) {
        try (Writer writer = new FileWriter(FILEVILLA);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(villaHeaderArr);

            //loop array list to write header at new another array
            for (Villa villa : villaArrayList) {
                csvWriter.writeNext(new String[]{
                        villa.getId(),
                        villa.getNameService(),
                        String.valueOf(villa.getAreaUsed()),
                        String.valueOf(villa.getRentalCosts()),
                        String.valueOf(villa.getMaxNumberOfPeople()),
                        villa.getTypeRent(),
                        villa.getRoomStandard(),
                        villa.getConvenientDescription(),
                        String.valueOf(villa.getAreaPool()),
                        String.valueOf(villa.getNumberOfFloors())
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<Villa> readVillaCSVFile() {
        //check existion of file
        Path pathVillaFile = Paths.get(FILEVILLA);
        if (!Files.exists(pathVillaFile)) {
            try {
                Writer writer = new FileWriter(FILEVILLA);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<Villa> villaStrategy = new ColumnPositionMappingStrategy<>();
        villaStrategy.setType(Villa.class);
        villaStrategy.setColumnMapping(villaHeaderArr); // mapping data base on header

        CsvToBean<Villa> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<Villa>(new FileReader(FILEVILLA))
                    .withMappingStrategy(villaStrategy)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withQuoteChar(DEFAULT_QUOTE)
                    .withSkipLines(NUMBER_LINE_SKIP)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assert csvToBean != null;
        return (ArrayList<Villa>) csvToBean.parse();
//        return ((ArrayList<Villa>)csvToBean.parse()); -> this way maybe produce "NullPointerException";
    }

    public static TreeSet<String> readVillaNotDuplicate(String pathFile) {
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
