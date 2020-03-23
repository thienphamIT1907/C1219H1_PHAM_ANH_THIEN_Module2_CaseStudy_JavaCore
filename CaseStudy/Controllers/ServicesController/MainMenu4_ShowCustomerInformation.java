package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Customer_CSV.configCustomerCSV;
import CaseStudy.Models.Customer;

import java.util.ArrayList;

import static CaseStudy.Controllers.MainController.displayMainMenu;

public class MainMenu4_ShowCustomerInformation {
    public static void showCustomerInformation() {
        ArrayList<Customer> customerArrayListShow = configCustomerCSV.readCustomerCSVFile();
        customerArrayListShow.sort(new SortNameAndYear());
        System.out.println("=================[ALL CUSTOMER LIST]=================");
        customerArrayListShow.forEach(customer -> {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-");
            System.out.println(customer.showInfo() + "\n");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-");
        });
        displayMainMenu();
        System.out.println();
    }
}
