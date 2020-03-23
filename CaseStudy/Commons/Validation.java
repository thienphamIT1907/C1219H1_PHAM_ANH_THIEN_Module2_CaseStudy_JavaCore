package CaseStudy.Commons;

import CaseStudy.CustomerException.*;
import CaseStudy.Models.House;
import CaseStudy.Models.Room;
import CaseStudy.Models.Service;
import CaseStudy.Models.Villa;

import java.util.Calendar;
import java.util.Scanner;

public class Validation {
    private static Scanner input;

    public static boolean isValidNameService(String inputString) {
        String regex = "^([\\p{Lu}]|([\\p{Lu}][\\p{Ll}]{1,}))([\\s]([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,6}$";
        return inputString.matches(regex);
    }

    public static boolean isValidFreeService(String srv) {
        String regex = "^massage|karaoke|food|drink|car$";
        return srv.matches(regex);
    }

    public static boolean isValidIdService(Service service, String str) {
        String pattern = "";
        if (service instanceof Villa) {
            pattern = "^SVVL-\\d{4}$";
        } else if (service instanceof House) {
            pattern = "^SVHO-\\d{4}$";
        } else if (service instanceof Room) {
            pattern = "^SVRO-\\d{4}$";
        }
        return str.matches(pattern);
    }

    //service parameter in this method must be fill to processing if else statement in isValidIdService()
    public static String getValidIdService(Service service, String errorNofitication) {
        input = new Scanner(System.in);
        String result = input.nextLine();
        if (!isValidIdService(service, result)) {
            System.out.print(
                    errorNofitication +
                            "\n    + Villa: SVVL-XXXX\n" +
                            "    + House: SVHO-XXXX\n" +
                            "    + Room: SVRO-XXXX\n" +
                            "=>  Enter service id: "
            );
            result = getValidIdService(service, errorNofitication); // recall method if input is invalid
        }
        return result;
    }

    public static String getValidNameService(String errorNotification) {
        input = new Scanner(System.in);
        String result = input.nextLine();
        if (!isValidNameService(result)) {
            System.out.print(errorNotification);
            result = getValidNameService(errorNotification);
        }
        return result;
    }

    public static String getValidFreeService(String errorNotification) {
        input = new Scanner(System.in);
        String result = input.nextLine();
        if (!isValidFreeService(result)) {
            System.out.println(errorNotification);
            result = getValidFreeService(errorNotification);
        }
        return result;
    }

    public static double getValidDoubleNumber(Double minNumber, String errorNotification) {
        input = new Scanner(System.in);
        double result = 0.0;
        try {
            result = Double.parseDouble(input.nextLine());
            if (minNumber != null && result <= minNumber) {
                throw new Exception(errorNotification);
            }
        } catch (Exception e) {
            System.err.println(errorNotification);
            result = getValidDoubleNumber(minNumber, errorNotification);
        }
        return result;
    }

    public static Integer getValidNumberInteger(Integer minNumber, Integer maxNumber, String errorNotification) {
        int result = 0;
        try {
            result = Integer.parseInt(input.nextLine());
            if (minNumber != null && result <= minNumber) {
                throw new Exception(errorNotification);
            }
            if (maxNumber != null && result >= maxNumber) {
                throw new Exception(errorNotification);
            }
        } catch (Exception e) {
            System.out.println(errorNotification);
            result = getValidNumberInteger(minNumber, maxNumber, errorNotification);
        }
        return result;
    }


    // FOR CUSTOMER //
    public static String getValidCustomerName(String errorNotification) {
        String result = "";
        input = new Scanner(System.in);
        try {
            result = input.nextLine();
            if (!isValidNameService(result)) {
                throw new NameException(errorNotification);
            }
        } catch (NameException e) {
            System.out.println(errorNotification);
            result = getValidCustomerName(errorNotification);
        }
        return result;
    }

    public static boolean isValidBirthday(String string) {
        String regex = "^((0)[1-9]|[1-2][0-9]|(3)[0-1])(/)((0)[1-9]|((1)[0-2]))(/)\\d{4}$";
        return string.matches(regex);
    }

    public static String getValidBirthday(String errorNotification) {
        String result = "";
        input = new Scanner(System.in);
        try {
            result = input.nextLine();
            if (!isValidBirthday(result)) {
                throw new BirthdayException(errorNotification);
            }
            int year = Integer.parseInt(result.substring(6, 10));
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (year <= 1900 || currentYear - year <= 18) {
                throw new BirthdayException(errorNotification);
            }
        } catch (BirthdayException e) {
            System.out.println(errorNotification);
            return getValidBirthday(errorNotification);
        }
        return result;
    }

    public static boolean isValidGender(String string) {
        String regex = "^(?i:Male|Female|Unknown)$";
        return string.matches(regex);
    }

    public static String getValidGender(String errorNotification) {
        String result = "";
        input = new Scanner(System.in);
        try {
            result = input.nextLine();
            if(!isValidGender(result)) {
                throw new GenderException(errorNotification);
            }
        } catch (GenderException e) {
            System.out.println(errorNotification);
            result = getValidGender(errorNotification);
        }
        return result;
    }

    public static boolean isValidIDCard(String string) {
//        String regex = "^(\\d{3}\\s\\d{3}\\s\\d{3})$";
        String regex = "^(^\\d{3}\\s\\d{3}\\s\\d{3})$";
//        String regex = "^\\d{9}$";
        return string.matches(regex);
    }

    public static String getValidIDCard(String errorNotification) {
        String result = "";
        input = new Scanner(System.in);
        try {
            result = input.nextLine();
            if(!isValidIDCard(result)) {
                throw new IDCardException(errorNotification);
            }
        } catch (IDCardException e) {
            System.out.println(errorNotification);
            result = getValidIDCard(errorNotification);
        }
        return result;
    }

    public static boolean isValidEmail(String string) {
        String regex = "^[a-z0-9+_-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
        return string.matches(regex);
    }

    public static String getValidEmail(String errorNotification) {
        String result = "";
        input = new Scanner(System.in);
        try {
            result = input.nextLine();
            if(!isValidEmail(result)) {
                throw new EmailException(errorNotification);
            }
        } catch (EmailException e) {
            System.out.println(errorNotification);
            result = getValidEmail(errorNotification);
        }
        return result;
    }
}









































