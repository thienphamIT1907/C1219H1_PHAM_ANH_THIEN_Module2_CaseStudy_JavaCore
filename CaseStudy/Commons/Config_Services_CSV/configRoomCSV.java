package CaseStudy.Commons.Config_Services_CSV;

import CaseStudy.Models.Room;
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

public class configRoomCSV {
    private static final String FILEROOM = "src/CaseStudy/Data/Room.csv";

    private static String[] roomHEaderArr = new String[]{
            "id", "nameService", "areaUsed", "rentalCosts",
            "maxNumberOfPeople", "typeRent", "freeService"
    };

    //WRITE DATA TO FILE
    public static void writeRoomCSVFile(ArrayList<Room> roomArrayList) {
        try (Writer writer = new FileWriter(FILEROOM);
             CSVWriter csvWriter = new CSVWriter(
                     writer,
                     DEFAULT_SEPARATOR, // ','
                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
                     CSVWriter.DEFAULT_LINE_END// "\n"
             )) {
            //write line by line
            csvWriter.writeNext(roomHEaderArr);

            //loop array list to write header at new another array
            for (Room room : roomArrayList) {
                csvWriter.writeNext(new String[]{
                        room.getId(),
                        room.getNameService(),
                        String.valueOf(room.getAreaUsed()),
                        String.valueOf(room.getRentalCosts()),
                        String.valueOf(room.getMaxNumberOfPeople()),
                        room.getTypeRent(),
                        room.getFreeService(),
                });
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //READ DATA TO FILE
    public static ArrayList<Room> readRoomCSVFile() {
        //check existion of file
        Path pathRoomFile = Paths.get(FILEROOM);
        if (!Files.exists(pathRoomFile)) {
            try {
                Writer writer = new FileWriter(FILEROOM);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        //get data and mapping with Collection<Type>
        ColumnPositionMappingStrategy<Room> roomStrategy = new ColumnPositionMappingStrategy<>();
        roomStrategy.setType(Room.class);
        roomStrategy.setColumnMapping(roomHEaderArr); // mapping data base on header

        CsvToBean<Room> csvToBean = null;
        try {
            //let csvToBean get data from file
            csvToBean = new CsvToBeanBuilder<Room>(new FileReader(FILEROOM))
                    .withMappingStrategy(roomStrategy)
                    .withSeparator(DEFAULT_SEPARATOR)
                    .withQuoteChar(DEFAULT_QUOTE)
                    .withSkipLines(NUMBER_LINE_SKIP)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        assert csvToBean != null;
        return (ArrayList<Room>) csvToBean.parse();
    }

    public static TreeSet<String> readRoomNotDuplicate(String pathFile) {
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
