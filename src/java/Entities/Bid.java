package Entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(BidPK.class)
public class Bid {
    
    private int amount;
    private LocalDateTime time;
    
    @Id
    @ManyToOne
    private Auction auction;
    
    @Id
    @ManyToOne
    private User user;

    public Bid() {
    }

    public Bid(int amount, LocalDateTime time) {
        this.amount = amount;
        this.time = time;
    }
    
    
    
    
    
    // Getters and Setters

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
