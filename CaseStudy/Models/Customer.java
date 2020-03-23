package CaseStudy.Models;

public class Customer {
    private String customerName;
    private String customerBirthday;
    private String customerGender;
    private String customerIDCard;
    private String customerNumberPhone;
    private String customerEmail;
    private String customerType;
    private String customerAddress;
    private Service customerUseService;

    public Customer() {
    }

    public Customer(
            String customerName,
            String customerBirthday,
            String customerGender,
            String customerIDCard,
            String customerNumberPhone,
            String customerEmail,
            String customerType,
            String customerAddress,
            Service customerUseService) {
        this.customerName = customerName;
        this.customerBirthday = customerBirthday;
        this.customerGender = customerGender;
        this.customerIDCard = customerIDCard;
        this.customerNumberPhone = customerNumberPhone;
        this.customerEmail = customerEmail;
        this.customerType = customerType;
        this.customerAddress = customerAddress;
        this.customerUseService = customerUseService;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerIDCard() {
        return customerIDCard;
    }

    public void setCustomerIDCard(String customerIDCard) {
        this.customerIDCard = customerIDCard;
    }

    public String getCustomerNumberPhone() {
        return customerNumberPhone;
    }

    public void setCustomerNumberPhone(String customerNumberPhone) {
        this.customerNumberPhone = customerNumberPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Service getCustomerUseService() {
        return customerUseService;
    }

    public void setCustomerUseService(Service customerUseService) {
        this.customerUseService = customerUseService;
    }

    public String showInfo() {
        return (
                "\n - Customer name: " + this.getCustomerName() +
                        "\n - Customer birthday: " + this.getCustomerBirthday() +
                        "\n - Customer gender: " + this.getCustomerGender() +
                        "\n - Customer Id card: " + this.getCustomerIDCard() +
                        "\n - Customer number phone: " + this.getCustomerNumberPhone() +
                        "\n - Customer email: " + this.getCustomerEmail() +
                        "\n - Customer type: " + this.getCustomerType() +
                        "\n - Customer address: " + this.getCustomerAddress()
        );
    }
}
