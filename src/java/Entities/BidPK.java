package Entities;

import java.io.Serializable;
import java.util.Objects;

public class BidPK implements Serializable {
    
    private Long auction;
    private Long user;
    
    public BidPK(){}

    public BidPK(Long auction, Long user) {
        this.auction = auction;
        this.user = user;
    }
    
    
    
    // Getters and Setters

    public Long getAuction() {
        return auction;
    }

    public void setAuction(Long auction) {
        this.auction = auction;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
    
    // Misc

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.auction);
        hash = 29 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BidPK other = (BidPK) obj;
        if (!Objects.equals(this.auction, other.auction)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }
    
    
    
}
