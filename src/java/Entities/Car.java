package Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Car implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String manufacturer;
    private String model;
    private Integer purchasePrice;
    private Integer manufactureYear;
    private CarSize size;

    @OneToOne
    private Auction auction;
    
    public Car() {}

    public Car(String model, Integer purchasePrice, String manufacturer, Integer manufactureYear, CarSize size) {
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.size = size;
    }

    
    
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    
    // Misc

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", purchasePrice=" + purchasePrice + ", manufactureYear=" + manufactureYear + ", size=" + size + '}';
    }
    
    
}
