package CaseStudy.Controllers.ServicesController;

import CaseStudy.Commons.Config_Employee_CSV.configEmployeeCSV;
import CaseStudy.Models.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenu6_ShowEmployeeInformation {
    public static void showEmployeeInformation() {
        ArrayList<Employee> employeeArrayList = configEmployeeCSV.readEmployeeCSVFile();
        Map<String, Employee> map = new HashMap<>();
        for (Employee emp: employeeArrayList) {
            map.put(emp.getEmployeeID(),emp);
        }

        for (Map.Entry m: map.entrySet()) {
            System.out.println("\n=================================================================================================================");
            System.out.println("- Key: " + m.getKey() + "\n" + "- Value: " + m.getValue().toString()) ;
            System.out.println("=================================================================================================================");
        }
    }
}
