package CaseStudy.Models;

public class Room extends Service{
    private String freeService;

    @Override
    public String showInfo() {
        return (
            "\n - Id Service: " + super.getId() +
            "\n - Name Service: " + super.getNameService() +
            "\n - Area used: " + super.getAreaUsed() +
            "\n - Rental Costs: " + super.getRentalCosts() +
            "\n - Maximum number of people: " + super.getMaxNumberOfPeople() +
            "\n - Type rent: " + super.getTypeRent() +
            "\n - Free service: " + this.getFreeService()
        );
    }

    public Room() {
    }

    public Room(String id, String nameService, double areUsed, double rentalCosts, int maxNumberOfPeople, String typeRent, String freeService) {
        super(id, nameService, areUsed, rentalCosts, maxNumberOfPeople, typeRent);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }
}
