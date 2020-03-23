//package CaseStudy.Commons;
//
//import CaseStudy.Models.House;
//import CaseStudy.Models.Room;
//import CaseStudy.Models.Villa;
//import com.opencsv.CSVWriter;
//import com.opencsv.bean.ColumnPositionMappingStrategy;
//import com.opencsv.bean.CsvToBean;
//import com.opencsv.bean.CsvToBeanBuilder;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//public class configCSV {
//    private static final String FILEVILLA = "src/CaseStudy/Data/Villa.csv";
//    private static final String FILEROOM = "src/CaseStudy/Data/Room.csv";
//    private static final String FILEHOUSE = "src/CaseStudy/Data/House.csv";
//    private static final String FILECUSTOMER = "src/CaseStudy/Data/Customer.csv";
//
//    private static final char DEFAULT_SEPARATOR = ',';
//    private static final char DEFAULT_QUOTE = '"';
//    private static final int NUMBER_LINE_SKIP = 1;
//
//    private static String[] villaHeaderArr = new String[]{
//            "id", "nameService", "areaUsed", "rentalCosts",
//            "maxNumberOfPeople", "typeRent", "roomStandard",
//            "convenientDescription", "areaPool", "numberOfFloors"
//    };
//
//    private static String[] houseHeaderArr = new String[]{
//            "id", "nameService", "areaUsed", "rentalCosts",
//            "maxNumberOfPeople", "typeRent", "roomStandard",
//            "convenientDescription", "numberOfFloors"
//    };
//
//    private static String[] roomHEaderArr = new String[]{
//            "id", "nameService", "areaUsed", "rentalCosts",
//            "maxNumberOfPeople", "typeRent", "freeService"
//    };
//
//    private static String[] customerHeaderArr = new String[]{};
//
//    //CONFIG CSV FILE : VILLA
//    public static void writeVillaCSVFile(ArrayList<Villa> villaArrayList) {
//        try (Writer writer = new FileWriter(FILEVILLA);
//             CSVWriter csvWriter = new CSVWriter(
//                     writer,
//                     CSVWriter.DEFAULT_SEPARATOR, // ','
//                     CSVWriter.NO_QUOTE_CHARACTER, // '\u0000'
//                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,// '"'
//                     CSVWriter.DEFAULT_LINE_END// "\n"
//             )) {
//            //write line by line
//            csvWriter.writeNext(villaHeaderArr);
//
//            //loop array list to write header at new another array
//            for (Villa villa : villaArrayList) {
//                csvWriter.writeNext(new String[]{
//                        villa.getId(),
//                        villa.getNameService(),
//                        String.valueOf(villa.getAreaUsed()),
//                        String.valueOf(villa.getRentalCosts()),
//                        String.valueOf(villa.getMaxNumberOfPeople()),
//                        villa.getTypeRent(),
//                        villa.getRoomStandard(),
//                        villa.getConvenientDescription(),
//                        String.valueOf(villa.getAreaPool()),
//                        String.valueOf(villa.getNumberOfFloors())
//                });
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public static ArrayList<Villa> readVillaCSVFile() {
//        //check existion of file
//        Path path_VillaFile = Paths.get(FILEVILLA);
//
//        if (!Files.exists(path_VillaFile)) {
//            try {
//                Writer writer = new FileWriter(FILEVILLA);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        ColumnPositionMappingStrategy<Villa> villaStrategy = new ColumnPositionMappingStrategy<>();
//        villaStrategy.setType(Villa.class);
//        villaStrategy.setColumnMapping(villaHeaderArr);
//
//        CsvToBean<Villa> csvToBean = null;
//        try {
//            //let csvToBean get data from file
//            csvToBean = new CsvToBeanBuilder<Villa>(new FileReader(FILEVILLA))
//                    .withMappingStrategy(villaStrategy)
//                    .withSeparator(DEFAULT_SEPARATOR)
//                    .withQuoteChar(DEFAULT_QUOTE)
//                    .withSkipLines(NUMBER_LINE_SKIP)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//
////        return ((ArrayList<Villa>)csvToBean.parse()); -> this way maybe produce "NullPointerException"
//        assert csvToBean != null;
//        return ((ArrayList<Villa>) csvToBean.parse());
//    }
//
//    //CONFIG CSV FILE : ROOM
//    public static void writeRoomCSVFile(ArrayList<Room> roomArrayList) {
//        String[] roomHeaderArr = new String[]{
//                "id", "nameService", "areaUsed", "rentalCosts",
//                "maxNumberOfPeople", "typeRent", "freeService"
//        };
//
//        try (Writer writer = new FileWriter(FILEROOM);
//             CSVWriter csvWriter = new CSVWriter(
//                     writer,
//                     CSVWriter.DEFAULT_SEPARATOR,
//                     CSVWriter.NO_QUOTE_CHARACTER,
//                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
//                     CSVWriter.DEFAULT_LINE_END
//             )) {
//            csvWriter.writeNext(roomHeaderArr);
//
//            //draw header
//            for (Room room : roomArrayList) {
//                csvWriter.writeNext(new String[]{
//                        room.getId(),
//                        room.getNameService(),
//                        String.valueOf(room.getAreaUsed()),
//                        String.valueOf(room.getRentalCosts()),
//                        String.valueOf(room.getMaxNumberOfPeople()),
//                        room.getTypeRent(),
//                        room.getFreeService()
//                });
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static ArrayList<Room> readRoomCSVFile() {
//        Path path_RoomFile = Paths.get(FILEROOM);
//
//        if (!Files.exists(path_RoomFile)) {
//            try {
//                Writer writer = new FileWriter(FILEROOM);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        ColumnPositionMappingStrategy<Room> roomStrategy = new ColumnPositionMappingStrategy<>();
//        roomStrategy.setType(Room.class);
//        roomStrategy.setColumnMapping(roomHEaderArr);
//
//        CsvToBean<Room> csvToBean = null;
//        try {
//            //let csvToBean get data from file
//            csvToBean = new CsvToBeanBuilder<Room>(new FileReader(FILEROOM))
//                    .withMappingStrategy(roomStrategy)
//                    .withSeparator(DEFAULT_SEPARATOR)
//                    .withQuoteChar(DEFAULT_QUOTE)
//                    .withSkipLines(NUMBER_LINE_SKIP)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        assert csvToBean != null;
//        return ((ArrayList<Room>) csvToBean.parse());
//    }
//
//    //CONFIG CSV FILE : HOUSE
//    public static void writeHouseCSVFile(ArrayList<House> houseArrayList) {
//        String[] houseHeaderArr = new String[]{
//                "id", "nameService", "areaUsed", "rentalCosts",
//                "maxNumberOfPeople", "typeRent", "freeService"
//        };
//
//        try (Writer writer = new FileWriter(FILEHOUSE);
//             CSVWriter csvWriter = new CSVWriter(
//                     writer,
//                     CSVWriter.DEFAULT_SEPARATOR,
//                     CSVWriter.NO_QUOTE_CHARACTER,
//                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
//                     CSVWriter.DEFAULT_LINE_END
//             )) {
//            csvWriter.writeNext(houseHeaderArr);
//
//            //draw header
//            for (House house : houseArrayList) {
//                csvWriter.writeNext(new String[]{
//                        house.getId(),
//                        house.getNameService(),
//                        String.valueOf(house.getAreaUsed()),
//                        String.valueOf(house.getRentalCosts()),
//                        String.valueOf(house.getMaxNumberOfPeople()),
//                        house.getTypeRent(),
//                        house.getRoomStandard(),
//                        house.getConvenientDescription(),
//                        String.valueOf(house.getNumberOfFloors())
//                });
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static ArrayList<House> readHouseCSVFile() {
//        Path path_HouseFile = Paths.get(FILEHOUSE);
//
//        if (!Files.exists(path_HouseFile)) {
//            try {
//                Writer writer = new FileWriter(FILEHOUSE);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        ColumnPositionMappingStrategy<House> houseStrategy = new ColumnPositionMappingStrategy<>();
//        houseStrategy.setType(House.class);
//        houseStrategy.setColumnMapping(houseHeaderArr);
//
//        CsvToBean<House> csvToBean = null;
//        try {
//            //let csvToBean get data from file
//            csvToBean = new CsvToBeanBuilder<House>(new FileReader(FILEHOUSE))
//                    .withMappingStrategy(houseStrategy)
//                    .withSeparator(DEFAULT_SEPARATOR)
//                    .withQuoteChar(DEFAULT_QUOTE)
//                    .withSkipLines(NUMBER_LINE_SKIP)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        assert csvToBean != null;
//        return ((ArrayList<House>) csvToBean.parse());
//    }
//}
//
////    private static final String FILE_HEADER = "id,nameService,areaUsed,rentalCosts,maxNumberOfPeople,typeRent,roomStandard,convenientDescription,numberOfFloors,areaPool";
////
////    public static void writeVillaCSVFile(ArrayList<Villa> villaArrayList) {
////
//////            fileWriter.append(FILE_HEADER) ;
//////        fileWriter.append(NEW_LINE_SEPARATOR);
////
////        for (Villa villa : villaArrayList) {
////            fileWriter.append(villa.getId());
////            fileWriter.append(COMMA_DELIMITER);
////
////            fileWriter.append(villa.getNameService());
////            fileWriter.append(COMMA_DELIMITER);
////
////            fileWriter.append((char) villa.getAreaUsed());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append((char) villa.getRentalCosts());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append((char) villa.getMaxNumberOfPeople());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append(villa.getTypeRent());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append(villa.getRoomStandard());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append(villa.getConvenientDescription());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append((char) villa.getNumberOfFloors());
////            fileWriter.append(NEW_LINE_SEPARATOR);
////
////            fileWriter.append((char) villa.getAreaPool());
////        }
////
////    } catch(
////    Exception e)
////
////    {
////        System.out.println("We have some problem in Villa CSV file...");
////    } finally
////
////    {
////        try {
////            assert false;
////            fileWriter.flush();
////            fileWriter.close();
////        } catch (Exception e) {
////            System.out.println("Error flushing or closing!");
////        }
////    }
////
////}
////
////    public static ArrayList<Villa> getVillaCSVFile() {
////        BufferedReader bufferedReader = null;
////        ArrayList<Villa> villaArrayList = new ArrayList<>();
////        Path path = Paths.get(FILEVILLA);
////        if (!Files.exists(path)) {
////            try {
////                File file;
////                Writer writer = new FileWriter(FILEVILLA);
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
////        }
////
////        try {
////            String line;
////            bufferedReader = new BufferedReader(new FileReader(FILEVILLA));
////            while ((line = bufferedReader.readLine()) != null) {
////                String[] splitData = line.split(",");
////
////                if (splitData[0].equals("id")) {
////                    continue;
////                }
////
////                Villa villa = new Villa();
////                villa.setId(splitData[0]);
////                villa.setNameService(splitData[1]);
////                villa.setAreaUsed(Double.parseDouble(splitData[2]));
////                villa.setRentalCosts(Double.parseDouble(splitData[3]));
////                villa.setMaxNumberOfPeople(Integer.parseInt(splitData[4]));
////                villa.setTypeRent(splitData[5]);
////                villa.setRoomStandard(splitData[6]);
////                villa.setConvenientDescription(splitData[7]);
////                villa.setNumberOfFloors(Integer.parseInt(splitData[8]));
////                villa.setAreaPool(Double.parseDouble(splitData[9]));
////
////                villaArrayList.add(villa);
////            }
////
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        } finally {
////            try {
////                bufferedReader.close();
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
////        }
////        return villaArrayList;
////    }
