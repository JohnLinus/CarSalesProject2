package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
//    private User owner;
    private LocalDateTime timeOfEnd;
    private int reservationPrice;
    private int valuedPrice;
    
    @OneToOne(optional = false)
    private Car item;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Bid> bids;

    public Auction(LocalDateTime timeOfEnd, int reservationPrice, Car item) {
        this.timeOfEnd = timeOfEnd;
        this.reservationPrice = reservationPrice;
        this.item = item;
    }

    public Auction() {
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
    
    
    public boolean isBidsOverReservedPrice() {
        return reservationPrice <= getHighestBid();
    }
    
//    public LocalDateTime getLatestBid() {
//        Optional<LocalDateTime> temp = bids.stream()
//                .map(b -> b.getTimeOfBid())
//                .max((t1, t2) -> t1.compareTo(t2));
//        
//        return (temp.isPresent()) ? temp.get() : null;
//    }
    
    public int getHighestBid() {
        if (bids == null)
            return -1;
        OptionalInt max = bids.stream()
                        .mapToInt(b -> b.getAmount())
                        .max();
        
        return (max.isPresent()) ? max.getAsInt() : -1;
    }
//
//    public Map<LocalDateTime, Integer> getAnonBids() {
//        Map<LocalDateTime, Integer> map = new HashMap<>();
//        for (LocalDateTime key : bids.keySet()) {
//            map.put(key, bids.get(key).getValue());
//        }
//        return map;
//    }
    
    
    
    
    
    // Getters And Setters

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

    public Car getItem() {
        return item;
    }

    public void setItem(Car item) {
        this.item = item;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
