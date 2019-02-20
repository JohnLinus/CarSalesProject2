package Entities;

import Enum.CarEnum;
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
    final private CarEnum type;
    
    private int salePrice;
    private int currentBidPrice;
    private LocalDateTime currentBidTime;

    public Car() {
        purchasePrice = null;
        manufacturer = null;
        manufactureYear = null;
        model = null;
        type = null;
    }
    
    public Car(Integer purchasePrice, String manufacturer, Integer manufactureYear, CarEnum type, String model) {
        this.purchasePrice = purchasePrice;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.type = type;
    }
    
    public void setCurrentBid(int bid) {
        currentBidPrice = bid;
        currentBidTime = LocalDateTime.now();
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

    public CarEnum getType() {
        return type;
    }

    public int getSalePrice() {
        return salePrice;
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
        return "Entities.Car[ id=" + id + " ]";
    }
    
}
