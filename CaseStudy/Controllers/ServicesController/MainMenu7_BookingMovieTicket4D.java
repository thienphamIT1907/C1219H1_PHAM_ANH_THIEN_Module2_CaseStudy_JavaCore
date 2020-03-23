package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Customer_CSV.configCustomerCSV;
import CaseStudy.Models.Customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static CaseStudy.Controllers.MainController.displayMainMenu;
import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu7_BookingMovieTicket4D {
    static Scanner input = new Scanner(System.in);
    public static void bookingMovieTicket4D() {
        System.out.print(
                "=========================\n" +
                        "\nMENU 7 - BOOKING MOVIE TICKET 4D:\n" +
                        "1. Booking movie ticket\n" +
                        "2. Show customer booking movie ticket\n" +
                        "3. Exit\n" +
                        "Your select: "
        );

        switch (input.nextInt()) {
            case 1:
                addBookingMovieTicket();
                break;
            case 2:
                showCustomerBookingMovieTicket();
                break;
            case 3:
                System.exit(-1);
                break;
            default:
                System.out.println("Input invalid, please try again!");
                displayMainMenu();
        }
    }

    private static Queue<Customer> customerQueueBookingTicket = new LinkedList<>();
    private static void addBookingMovieTicket() {
        try {
            ArrayList<Customer> customerArrayList = configCustomerCSV.readCustomerCSVFile();
            int i = 1;
            for (Customer cus : customerArrayList) {
                System.out.println("Customer [" + i + "]: " + cus.getCustomerName());
                i++;
            }
            System.out.print("Choose customer to booking movie ticket 4D: ");
            Customer customerWasChosen = customerArrayList.get(input.nextInt() - 1);
            customerQueueBookingTicket.add(customerWasChosen);
            System.out.println("Booking ticket movie for customer [" + customerWasChosen.getCustomerName().toUpperCase() + "] has completed!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Customer not exist, please try again!");
            addBookingMovieTicket();
        }
        System.out.println("Queue booking: "+ customerQueueBookingTicket);
        backPreviousMenu();
    }

    public static void showCustomerBookingMovieTicket() {
        if(customerQueueBookingTicket.isEmpty()) {
            System.out.println("This Queue is empty, please create new booking to add this !");
            backPreviousMenu();
        } else {
            int i=1;
            System.out.println("==================CUSTOMER BOOKING LIST==================");
            for (Customer customer : customerQueueBookingTicket) {
                System.out.println("Customer[" + i + "]: " + customer.getCustomerName().toUpperCase());
                System.out.println("==============================");
                i++;
            }
        }
    }
}
