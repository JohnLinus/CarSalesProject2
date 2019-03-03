package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Bidder implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String phone;
    private String address;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<Bid> bids;
    
    public Bidder(){}

    public Bidder(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    
    
    
    public void addBid(Bid bid) {
        if (bids == null)
            bids = new ArrayList<>();
        bids.add(bid);
        bid.setBidder(this);
    }
    
    public void removeBid(Bid bid) {
        if (bids == null)
            return;
        bids.remove(bid);
        bid.setBidder(null);
    }
    
    
    
    
    
    
    
    
    
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Bid> getBids() {
        if (bids == null)
            bids = new ArrayList<>();
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @Override
    public String toString() {
        return "Bidder{" + "name=" + name + ", phone=" + phone + ", address=" + address + ", bids=" + bids + '}';
    }
    
    
    
    
}
