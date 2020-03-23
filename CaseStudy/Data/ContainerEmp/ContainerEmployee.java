package CaseStudy.Data.ContainerEmp;

import CaseStudy.Commons.Config_Employee_CSV.configEmployeeCSV;
import CaseStudy.Models.Employee;

import java.util.ArrayList;
import java.util.Stack;

public class ContainerEmployee {
    public static Stack<Employee> getAllEmployeeSaved() {
        Stack<Employee> employeeStackToSave = new Stack<>();
        ArrayList<Employee> employeeArrayList = configEmployeeCSV.readEmployeeCSVFile();
        for(Employee emp: employeeArrayList) {
            employeeStackToSave.push(emp);
        }
        return employeeStackToSave;
    }
}
