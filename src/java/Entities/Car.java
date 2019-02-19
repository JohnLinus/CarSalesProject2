package Entities;

public class Car {

    
    private String manufacturer;
    private int manufactureYear;  
    private String type;
    final private Integer purchasePrice;
    private int salesPrice;
    private int currentBid;
    private int currentBid2;

    public Car() {
        purchasePrice =null;
    }

  public int b(int b){
      return b;
  }

    
    
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }
    
    
}