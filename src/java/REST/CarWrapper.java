package REST;

import Entities.Car;
import Entities.CarSize;

public class CarWrapper {
    
    private String manufacturer;
    private String model;
    private Integer purchasePrice;
    private Integer manufactureYear;
    private CarSize size;
    private Long auction;

    public CarWrapper() {
    }

    public CarWrapper(Car car) {
        this.manufacturer = car.getManufacturer();
        this.model = car.getModel();
        this.purchasePrice = car.getPurchasePrice();
        this.manufactureYear = car.getManufactureYear();
        this.size = car.getSize();
        if (car.getAuction() != null)
            this.auction = car.getAuction().getId();
    }
    
    
    

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public CarSize getSize() {
        return size;
    }

    public void setSize(CarSize size) {
        this.size = size;
    }

    public Long getAuction() {
        return auction;
    }

    public void setAuction(Long auction) {
        this.auction = auction;
    }
    
    
}
