package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Services_CSV.configHouseCSV;
import CaseStudy.Commons.Config_Services_CSV.configRoomCSV;
import CaseStudy.Commons.Config_Services_CSV.configVillaCSV;
import CaseStudy.Commons.Validation;
import CaseStudy.Models.House;
import CaseStudy.Models.Room;
import CaseStudy.Models.Service;
import CaseStudy.Models.Villa;

import java.util.ArrayList;
import java.util.Scanner;


import static CaseStudy.Controllers.MainController.displayMainMenu;
import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu1_AddNewService {
    private static Scanner input = new Scanner(System.in);
    public static void addNewServices() {
        System.out.print(
                "=========================\n" +
                        "\nMENU 1 - ADD NEW SERVICES:\n" +
                        "1. Add new Villa\n" +
                        "2. Add new House\n" +
                        "3. Add new Room\n" +
                        "4. Back to previous menu\n" +
                        "5. Exit\n" +
                        "Your select: "
        );

        switch (input.nextInt()) {
            case 1:
                addNewVilla();//done
                break;
            case 2:
                addNewHouse();//done
                break;
            case 3:
                addNewRoom();//done
                break;
            case 4:
                System.out.println("\n=========================");
                displayMainMenu();//done
                break;
            case 5:
                System.exit(-1);//done
                break;
            default:
                System.out.print("Input invalid !");
                backPreviousMenu();//done
                break;
        }
    }


//  REFACTOR METHOD LATER...
    private static void createService(Service service) {
//        service.setId(UUID.randomUUID().toString().replace("-", ""));
        input.nextLine();

        System.out.print("Enter service id: ");
        service.setId(Validation.getValidIdService(
                service,
                "ID service invalid, please try following format below: "
        ));

        System.out.print("Enter name service: ");
        service.setNameService(Validation.getValidNameService(
                "First character must be uppercase, please try again: "
        ));

        System.out.print("Enter area used: ");
        service.setAreaUsed(Validation.getValidDoubleNumber(
                30.0,
                "Min area used is 30, please try again: "
        ));

        System.out.print("Enter rental costs: ");
        service.setRentalCosts(Validation.getValidDoubleNumber(
                0.0,
                "Min rental costs is 0, please try again: "
        ));

        System.out.print("Enter max number of people: ");
        service.setMaxNumberOfPeople(Validation.getValidNumberInteger(
                0,
                20,
                "0 < number < 20, please try again: "
        ));

        System.out.print("Enter type rent: ");
        service.setTypeRent(Validation.getValidNameService(
                "First character must be uppercase, please try again: "
        ));
    }

    private static void addNewVilla() {
        ArrayList<Villa> villaArrayListAdd = configVillaCSV.readVillaCSVFile(); // get all data from csv file before add another record to there
        Villa villa = new Villa();
        createService(villa); //downcasting

        System.out.print("Enter room standard: ");
        villa.setRoomStandard(input.nextLine());

        System.out.print("Enter convenient description: ");
        villa.setConvenientDescription(input.nextLine());

        System.out.print("Enter area pool: ");
        villa.setAreaPool(input.nextDouble());

        System.out.print("Enter number of floors: ");
        villa.setNumberOfFloors(input.nextInt());

        villaArrayListAdd.add(villa);

        configVillaCSV.writeVillaCSVFile(villaArrayListAdd);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Add new VILLA has name " + "[" + villa.getNameService().toUpperCase() + "]" + " completed!");
        input.nextLine();
        backPreviousMenu();
    }

    private static void addNewHouse() {
        ArrayList<House> houseArrayListAdd = configHouseCSV.readHouseCSVFile();
        House house = new House();
        createService(house);

        System.out.print("Enter room standard: ");
        house.setRoomStandard(Validation.getValidNameService(
                "First character must be uppercase, please try again: "
        ));

        System.out.print("Enter convenient description: ");
        house.setConvenientDescription(input.nextLine());

        System.out.print("Enter number of floors: ");
        house.setNumberOfFloors((int) Validation.getValidDoubleNumber(
                1.0,
                "Minimize number of floors is 1, please try again: "
        ));

        houseArrayListAdd.add(house);

        configHouseCSV.writeHouseCSVFile(houseArrayListAdd);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Add new HOUSE has name " + "[" + house.getNameService().toUpperCase() + "]" + " completed!");
        backPreviousMenu();
    }

    private static void addNewRoom() {
        ArrayList<Room> roomArrayListAdd = configRoomCSV.readRoomCSVFile(); // get all data from csv file before add another record to there
        Room room = new Room();
        createService(room);

        System.out.print("Enter room standard: ");
        room.setFreeService(Validation.getValidFreeService(
            "massage|karaoke|food|drink|buffet|bbq, please choose again: "
        ));

        roomArrayListAdd.add(room);

        configRoomCSV.writeRoomCSVFile(roomArrayListAdd);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Add new ROOM has name " + "[" + room.getNameService().toUpperCase() + "]" + " completed!");
        backPreviousMenu();
    }
}
