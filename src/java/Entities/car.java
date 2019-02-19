package Entities;

import java.time.LocalDateTime;

public class car {

    
    private String manufacturer;
    private int manufactureYear;  
    private String type;
    final private Integer purchasePrice;
    private int salesPrice;
    private int currentBidPrice;
    private LocalDateTime currentBidTime;

    public car() {
        purchasePrice =null;
    }
    
    public void setCurrentBid(int bid) {
        currentBidPrice = bid;
        currentBidTime = LocalDateTime.now();
    }
    
    
}