package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User implements Serializable {
    
    public User(){}

    public User(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(targetEntity = Auction.class, fetch = FetchType.LAZY)
    private Map<LocalDateTime, Auction> bids = new HashMap<>();
    
    
    private String name;
    private String phone;
    private String address;
    
    public void bid(LocalDateTime time, Auction auction) {
        bids.put(time, auction);
    }
    
    public Map<LocalDateTime, Auction> getBids() {
        return bids;
    }
    
    public Long getId() {
        return id;
    }
}
