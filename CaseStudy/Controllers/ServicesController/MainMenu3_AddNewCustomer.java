package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Customer_CSV.configCustomerCSV;
import CaseStudy.Commons.Validation;
import CaseStudy.Models.Customer;

import java.util.ArrayList;
import java.util.Scanner;

import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu3_AddNewCustomer {
    public static void addNewCustomer() {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customerArrayListAdd = configCustomerCSV.readCustomerCSVFile();
        Customer customer = new Customer();

        System.out.print("Enter customer name: ");
        customer.setCustomerName(Validation.getValidCustomerName(
                "First character in your name must be uppercase and no unused space, please try again: "
        ));

        System.out.print("Enter customer birthday: ");
        customer.setCustomerBirthday(Validation.getValidBirthday(
                "Your birthday must be following (dd/MM/yyyy) and year > 1900 and little than current year is 18, please try again: "
        ));

        System.out.print("Enter customer gender: ");
        customer.setCustomerGender(Validation.getValidGender(
                "Your gender is one of (Male/Female/Unknown), please try again: "
        ));

        System.out.print("Enter customer ID card: ");
        customer.setCustomerIDCard(Validation.getValidIDCard(
                "Your ID card must be following (XXX XXX XXX), please try again: "
        ));

        System.out.print("Enter customer number phone: ");
        customer.setCustomerNumberPhone(input.nextLine());

        System.out.print("Enter customer email: ");
        customer.setCustomerEmail(Validation.getValidEmail(
                "Your email must be following (abc@abc.abc), please try again: "
        ));

        System.out.print("Enter customer type: ");
        customer.setCustomerType(input.nextLine());

        System.out.print("Enter customer address: ");
        customer.setCustomerAddress(input.nextLine());

        customerArrayListAdd.add(customer);

        configCustomerCSV.writeCustomerCSVFile(customerArrayListAdd);

        System.out.println("--------------------------------------------------------------");
        System.out.println("Add new CUSTOMER has name " + "["+ customer.getCustomerName().toUpperCase() + "]" + " completed!");
        backPreviousMenu();
    }
}
