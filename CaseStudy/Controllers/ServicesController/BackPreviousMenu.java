package CaseStudy.Controllers.ServicesController;

import java.util.Scanner;

import static CaseStudy.Controllers.MainController.displayMainMenu;

public class BackPreviousMenu {
    public static void backPreviousMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Press any key to continue...");
        input.nextLine();
        System.out.println("\n=========================");
        displayMainMenu();
    }
}
