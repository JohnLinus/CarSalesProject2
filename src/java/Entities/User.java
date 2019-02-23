package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(targetEntity = Auction.class)
    private HashMap<LocalDateTime, Auction> bids;
    
    private String name;
    private String phone;
    private String address;
    
    public void bid(LocalDateTime time, Auction auction) {
        bids.put(time, auction);
    }
    
    public HashMap<LocalDateTime, Auction> getBids() {
        return bids;
    }
    
    public Long getId() {
        return id;
    }
}
