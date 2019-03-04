package Entities;

import REST.AuctionWrapper;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Auction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime timeOfEnd;
    private int reservationPrice;
    private int valuedPrice;
    
    @OneToOne
    private Car item;
    
    @OneToMany
    private List<Bid> bids;

    public Auction(LocalDateTime timeOfEnd, int reservationPrice) {
        this.timeOfEnd = timeOfEnd;
        this.reservationPrice = reservationPrice;
    }

    public Auction() {
    }
    
    public void setItem(Car item) {
        if (item.getAuction() != null)
            throw new IllegalArgumentException("Item is already connected to an auction");
        
        this.item = item;
        item.setAuction(this);
    }
    
    public void addBid(Bid bid) {
        if (bids == null)
            bids = new ArrayList<>();
        bids.add(bid);
        bid.setAuction(this);
    }
    
    public void removeBid(Bid bid) {
        if (bids == null)
            return;
        bids.remove(bid);
        bid.setAuction(null);
    }
    
    public LocalDateTime getLatestBid() {
        if (bids == null)
            return null;
        
        Optional<LocalDateTime> temp = bids.stream()
                .map(b -> b.getTimeOfBid())
                .max((t1, t2) -> t1.compareTo(t2));
        
        return (temp.isPresent()) ? temp.get() : null;
    }
    
    public Bidder getLatestBidder() {
        if (bids == null)
            return null;
        
        Optional<Bid> temp = bids.stream()
                .max((t1, t2) -> t1.getTimeOfBid().compareTo(t2.getTimeOfBid()));
        
        return (temp.isPresent()) ? temp.get().getBidder() : null;
    }
    
    public int getHighestBid() {
        if (bids == null)
            return 0;
        
        OptionalInt max = bids.stream()
                        .mapToInt(b -> b.getAmount())
                        .max();
        
        return (max.isPresent()) ? max.getAsInt() : -1;
    }
    
    public boolean isReservedPriceMet() {
        int x = getHighestBid();
        return x > 0 && reservationPrice <= x;
    }
    
    
    
    
    
    // Getters And Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id == null)
            throw new IllegalArgumentException();
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

    public Car getItem() {
        return item;
    }

    public List<Bid> getBids() {
        if(bids == null)
            bids = new ArrayList<>();
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
