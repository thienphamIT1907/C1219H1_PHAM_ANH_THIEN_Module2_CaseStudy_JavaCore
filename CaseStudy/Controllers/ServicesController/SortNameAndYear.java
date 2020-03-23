package CaseStudy.Controllers.ServicesController;

import CaseStudy.Models.Customer;

import java.util.Comparator;

public class SortNameAndYear implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        int resultCompareName = o1.getCustomerName().compareTo(o2.getCustomerName());
        if(resultCompareName == 0) {
            return compareYear(o1, o2);
        }
        return resultCompareName;
    }

    private int compareYear(Customer o1, Customer o2) {
        int yearCustomer1 = Integer.parseInt(o1.getCustomerBirthday().substring(6,10));
        int yearCustomer2 = Integer.parseInt(o2.getCustomerBirthday().substring(6,10));
        if(yearCustomer1 == yearCustomer2) {
            return 0;
        } else if(yearCustomer1 > yearCustomer2) {
            return 1;
        }
        return -1;
    }
}
