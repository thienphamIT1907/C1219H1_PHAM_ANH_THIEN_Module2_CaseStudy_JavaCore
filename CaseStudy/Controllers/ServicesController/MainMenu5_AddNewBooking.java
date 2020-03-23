package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Customer_CSV.configCustomerCSV;
import CaseStudy.Commons.Config_Services_CSV.configBookingCSV;
import CaseStudy.Commons.Config_Services_CSV.configHouseCSV;
import CaseStudy.Commons.Config_Services_CSV.configRoomCSV;
import CaseStudy.Commons.Config_Services_CSV.configVillaCSV;
import CaseStudy.Models.Customer;
import CaseStudy.Models.House;
import CaseStudy.Models.Room;
import CaseStudy.Models.Villa;

import java.util.ArrayList;
import java.util.Scanner;

import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu5_AddNewBooking {
    private static Scanner input = new Scanner(System.in);

    public static void addNewBookingRoom() {
        //load all customer with numerical order from list
        ArrayList<Customer> customerArrayList = configCustomerCSV.readCustomerCSVFile();
        customerArrayList.sort(new SortNameAndYear());
        int i = 1; //stt
        for (Customer customer : customerArrayList) {
            System.out.println("=-=-=-=-=-=-=-=-=-=-= CUSTOMER: [" + i + "] =-=-=-=-=-=-=-=-=-=-=");
            System.out.println("ID card: " + customer.getCustomerIDCard());
            System.out.println("Name: " + customer.getCustomerName());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=");
            i++;
        }

        System.out.print("Choose your customer to booking: ");
        Customer customerWasChosen = customerArrayList.get(input.nextInt() - 1);

        System.out.print(
                "=========================\n" +
                        "\nMENU 5 - ADD NEW BOOKING:\n" +
                        "1. Booking Villa\n" +
                        "2. Booking House\n" +
                        "3. Booking Room\n" +
                        "4. Back to previous menu\n" +
                        "5. Exit\n" +
                        "Your select: "
        );
        switch (input.nextInt()) {
            case 1:
                i = 1;
                ArrayList<Villa> villaArrayListShow = configVillaCSV.readVillaCSVFile();
                for (Villa villa : villaArrayListShow) {
                    System.out.println("=-=-=-=-=-=-=-=-=-=-= VILLA: [" + i + "] =-=-=-=-=-=-=-=-=-=-=");
                    System.out.println(villa.showInfo() + "\n");
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-");
                    i++;
                }
                System.out.print("Choose villa you like to booking: ");
                Villa villaWasChosen = villaArrayListShow.get(input.nextInt() - 1);
                customerWasChosen.setCustomerUseService(villaWasChosen);
                break;

            case 2:
                i = 1;
                ArrayList<House> houseArrayListShow = configHouseCSV.readHouseCSVFile();
                for (House house : houseArrayListShow) {
                    System.out.println("=-=-=-=-=-=-=-=-=-=-= HOUSE: [" + i + "] =-=-=-=-=-=-=-=-=-=-=");
                    System.out.println(house.showInfo() + "\n");
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-");
                    i++;
                }
                System.out.print("Choose house you like to booking: ");
                House houseWasChosen = houseArrayListShow.get(input.nextInt() - 1);
                customerWasChosen.setCustomerUseService(houseWasChosen);
                break;

            case 3:
                i = 1;
                ArrayList<Room> roomArrayList = configRoomCSV.readRoomCSVFile();
                for (Room room : roomArrayList) {
                    System.out.println("=-=-=-=-=-=-=-=-=-=-= ROOM: [" + i + "] =-=-=-=-=-=-=-=-=-=-=");
                    System.out.println(room.showInfo() + "\n");
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-");
                    i++;
                }
                System.out.print("Choose room you like to booking: ");
                Room roomWasChosen = roomArrayList.get(input.nextInt() - 1);
                customerWasChosen.setCustomerUseService(roomWasChosen);
                break;

            case 4:
                backPreviousMenu();
                break;

            case 5:
                System.exit(-1);

            default:
                System.out.println("Input invalid, please try again! ");
                backPreviousMenu();
                break;
        }
        ArrayList<Customer> listBooking = configBookingCSV.readBookingCSVFile();
        listBooking.add(customerWasChosen);
        configBookingCSV.writeBookingCSVFile(listBooking);

        System.out.println("Create booking for CUSTOMER [" + customerWasChosen.getCustomerName().toUpperCase() +"] has completed! " );
        input.nextLine();
        backPreviousMenu();
    }
}
