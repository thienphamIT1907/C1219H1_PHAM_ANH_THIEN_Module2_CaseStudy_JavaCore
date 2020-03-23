package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Services_CSV.configHouseCSV;
import CaseStudy.Commons.Config_Services_CSV.configRoomCSV;
import CaseStudy.Commons.Config_Services_CSV.configVillaCSV;
import CaseStudy.Models.House;
import CaseStudy.Models.Room;
import CaseStudy.Models.Villa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import static CaseStudy.Controllers.MainController.displayMainMenu;
import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu2_ShowService {
    private static Scanner input = new Scanner(System.in);

    public static void showServices() {
        System.out.print(
                "=========================\n" +
                        "\nMENU 2 - SHOW SERVICES:\n" +
                        "1. Show all Villa\n" +
                        "2. Show all House\n" +
                        "3. Show all Room\n" +
                        "4. Show all name Villa not duplicate\n" +
                        "5. Show all name House not duplicate\n" +
                        "6. Show all name Room not duplicate\n" +
                        "7. Back to menu\n" +
                        "8. Exit\n" +
                        "Your select: "
        );

        switch (input.nextInt()) {
            case 1:
                showAllVilla();
                break;
            case 2:
                showAllHouse();
                break;
            case 3:
                showAllRoom();
                break;
            case 4:
                showAllVillaNotDuplicate();
                displayMainMenu();
                break;
            case 5:
                showAllHouseNotDuplicate();
                displayMainMenu();
                break;
            case 6:
                showAllRoomNotDuplicate();
                displayMainMenu();
                break;
            case 7:
                displayMainMenu();
                break;
            case 8:
                System.exit(-1);
                break;
            default:
                System.out.print("Input invalid !");
                backPreviousMenu();
                break;
        }
    }

    private static void showAllVilla() {
        ArrayList<Villa> villaArrayListToShow = configVillaCSV.readVillaCSVFile();
        System.out.println("=================[ALL VILLA LIST]=================");
        villaArrayListToShow.forEach(villa -> {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
            System.out.println(villa.showInfo() + "\n");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
        });
        input.nextLine();
        displayMainMenu();
        System.out.println();
    }

    private static void showAllHouse() {
        ArrayList<House> houseArrayListShow = configHouseCSV.readHouseCSVFile();
        System.out.println("=================[ALL HOUSE LIST]=================");
        houseArrayListShow.forEach(house -> {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
            System.out.println(house.showInfo() + "\n");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
        });
        input.nextLine();
        displayMainMenu();
        System.out.println();
    }

    private static void showAllRoom() {
        ArrayList<Room> roomArrayListShow = configRoomCSV.readRoomCSVFile();
        System.out.println("=================[ALL ROOM LIST]=================");
        roomArrayListShow.forEach(room -> {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
            System.out.println(room.showInfo() + "\n");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
        });
        input.nextLine();
        displayMainMenu();
        System.out.println();
    }

    private static void showAllVillaNotDuplicate() {
        String villaPath = "src/CaseStudy/Data/Villa.csv";
        Path pathVillaFile = Paths.get(villaPath);
        if (!Files.exists(pathVillaFile)) {
            try {
                Writer writer = new FileWriter(villaPath);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            backPreviousMenu();
        }
        TreeSet<String> villaTreeSet = configVillaCSV.readVillaNotDuplicate(villaPath);
        System.out.println("=================[LIST VILLA NOT DUPLICATE]=================");
        for(String villa: villaTreeSet) {
            System.out.println("- " + villa);
        }
        System.out.println("============================================================");
    }

    private static void showAllHouseNotDuplicate() {
        String housePath = "src/CaseStudy/Data/House.csv";
        Path pathHouseFile = Paths.get(housePath);
        if (!Files.exists(pathHouseFile)) {
            try {
                Writer writer = new FileWriter(housePath);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            backPreviousMenu();
        }
        TreeSet<String> houseTreeSet = configHouseCSV.readHouseNotDuplicate(housePath);
        System.out.println("=================[LIST HOUSE NOT DUPLICATE]=================");
        for(String house: houseTreeSet) {
            System.out.println("- " + house);
        }
        System.out.println("============================================================");
    }

    private static void showAllRoomNotDuplicate() {
        String roomPath = "src/CaseStudy/Data/Room.csv";
        Path pathRoomFile = Paths.get(roomPath);
        if (!Files.exists(pathRoomFile)) {
            try {
                Writer writer = new FileWriter(roomPath);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            backPreviousMenu();
        }
        TreeSet<String> roomTreeset = configRoomCSV.readRoomNotDuplicate(roomPath);
        System.out.println("=================[LIST ROOM NOT DUPLICATE]=================");
        for(String room: roomTreeset) {
            System.out.println("- " + room);
        }
        System.out.println("============================================================");
    }
}
