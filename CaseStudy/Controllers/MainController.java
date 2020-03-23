package CaseStudy.Controllers;

import java.util.Scanner;

import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

import static CaseStudy.Controllers.ServicesController.MainMenu1_AddNewService.addNewServices;
import static CaseStudy.Controllers.ServicesController.MainMenu2_ShowService.showServices;
import static CaseStudy.Controllers.ServicesController.MainMenu3_AddNewCustomer.addNewCustomer;
import static CaseStudy.Controllers.ServicesController.MainMenu4_ShowCustomerInformation.showCustomerInformation;
import static CaseStudy.Controllers.ServicesController.MainMenu5_AddNewBooking.addNewBookingRoom;
import static CaseStudy.Controllers.ServicesController.MainMenu6_ShowEmployeeInformation.showEmployeeInformation;
import static CaseStudy.Controllers.ServicesController.MainMenu7_BookingMovieTicket4D.bookingMovieTicket4D;
import static CaseStudy.Controllers.ServicesController.MainMenu8_FindEmployee.findEmployee;

public class MainController {
    public static Scanner input = new Scanner(System.in);

    public static void displayMainMenu() {
        System.out.print(
                "\n---MAIN MENU---\n" +
                        "1. Add new service\n" +
                        "2. Show service\n" +
                        "3. Add new customer\n" +
                        "4. Show information of customer\n" +
                        "5. Add new booking\n" +
                        "6. Show information of employee\n" +
                        "7. Booking movie ticket 4D\n" +
                        "8. Find Employee\n" +
                        "9. Exit\n" +
                        "Your select: "
        );

        switch (input.nextInt()) {
            case 1:
                addNewServices();
                break;
            case 2:
                showServices();
                break;
            case 3:
                addNewCustomer();
                break;
            case 4:
                showCustomerInformation();
                break;
            case 5:
                addNewBookingRoom();
                break;
            case 6:
                showEmployeeInformation();
                break;
            case 7:
                bookingMovieTicket4D();
            case 8:
                findEmployee();
                break;
            case 9:
                System.exit(-1);
                break;
            default:
                System.out.print("Input invalid ! Please try again: ");
                backPreviousMenu();
                break;
        }
    }
}
