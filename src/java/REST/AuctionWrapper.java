package REST;

import Entities.Auction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuctionWrapper {
    private Auction original;
    
    private Long id;
    private LocalDateTime timeOfEnd;
    private int reservationPrice;
    private int valuedPrice;
    private Long item;
    private List<Long> bids;

    public AuctionWrapper() {
    }

    public AuctionWrapper(Auction a) {
        original = a;
        this.id = a.getId();
        this.timeOfEnd = a.getTimeOfEnd();
        this.reservationPrice = a.getReservationPrice();
        this.valuedPrice = a.getValuedPrice();
        this.item = a.getItem().getId();
        if(a.getBids() != null)
            this.bids = a.getBids().stream().map(b -> b.getId()).collect(Collectors.toList());
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfEnd() {
        return timeOfEnd;
    }

    public void setTimeOfEnd(LocalDateTime timeOfEnd) {
        this.timeOfEnd = timeOfEnd;
    }

    public int getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(int reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public int getValuedPrice() {
        return valuedPrice;
    }

    public void setValuedPrice(int valuedPrice) {
        this.valuedPrice = valuedPrice;
    }

    public Long getItem() {
        return item;
    }

    public void setItem(Long item) {
        this.item = item;
    }

    public List<Long> getBids() {
        return bids;
    }

    public void setBids(List<Long> bids) {
        this.bids = bids;
    }
    
    
    
}
