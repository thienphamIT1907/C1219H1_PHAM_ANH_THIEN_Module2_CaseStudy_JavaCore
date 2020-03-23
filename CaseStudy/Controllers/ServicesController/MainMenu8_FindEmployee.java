package CaseStudy.Controllers.ServicesController;

import CaseStudy.Data.ContainerEmp.ContainerEmployee;
import CaseStudy.Models.Employee;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import static CaseStudy.Controllers.ServicesController.BackPreviousMenu.backPreviousMenu;

public class MainMenu8_FindEmployee {
    public static void findEmployee() {
        Scanner input = new Scanner(System.in);
        Stack<Employee> employeeStackToShow = ContainerEmployee.getAllEmployeeSaved();
        System.out.print("Find your employee by KEY: ");

        String keyInput = input.nextLine();

        try {
            while (true) {
                boolean findByID = employeeStackToShow.peek().getEmployeeID().contains(keyInput);
                if(!findByID) {
                    employeeStackToShow.pop();
                } else {
                    System.out.println("==========================LIST EMPLOYEE RESULT==========================");
                    System.out.println(employeeStackToShow.peek().toString());
                    break;
                }
            }
        } catch (EmptyStackException e) {
            e.getMessage();
            findEmployee();
        }
        backPreviousMenu();
    }
}
