package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    final private String manufacturer;
    final private String model;
    final private Integer purchasePrice;
    final private Integer manufactureYear;
    final private CarSize type;
    
    
    private int valuation;
    private int currentBidPrice;
    private LocalDateTime currentBidTime;

    public Car() {
        purchasePrice = null;
        manufacturer = null;
        model = null;
        manufactureYear = null;
        type = null;
    }
    
    public Car(String model, Integer purchasePrice, String manufacturer, Integer manufactureYear, CarSize type) {
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.type = type;
    }
    
    public void setCurrentBid(int bid) {
        currentBidPrice = bid;
        currentBidTime = LocalDateTime.now();
    }
    
    
    
    
    public String getModel() {
        return model;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public CarSize getType() {
        return type;
    }

    public int getValuation() {
        return valuation;
    }

    public int getCurrentBidPrice() {
        return currentBidPrice;
    }

    public LocalDateTime getCurrentBidTime() {
        return currentBidTime;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", purchasePrice=" + purchasePrice + ", manufactureYear=" + manufactureYear + ", type=" + type + ", salePrice=" + valuation + ", currentBidPrice=" + currentBidPrice + ", currentBidTime=" + currentBidTime + '}';
    }
    
}
