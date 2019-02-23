package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;
import javafx.util.Pair;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private LocalDateTime end;
    
//    private final User owner;
    
    @OneToOne
    private final Sellable item;
    
    @ManyToMany(targetEntity = User.class)
    private HashMap<LocalDateTime, Pair<User, Integer>> bids;
    
    private int reservationPrice;
    private int valuedPrice;
    
    public Auction() {
        item = null;
    }
    
    public void bid(LocalDateTime time, User user, int bid) {
        bids.put(time, new Pair<>(user, bid));
    }
    
    public boolean isBidsOverReservedPrice() {
        return reservationPrice <= getHighestBid();
    }
    
    public LocalDateTime getLatestBid() {
        Optional<LocalDateTime> max = bids.keySet().stream().max((o1, o2) -> o1.compareTo(o2));
        
        return (max.isPresent()) ? max.get() : null;
    }
    
    public int getHighestBid() {
        OptionalInt max = bids.values().stream()
                        .mapToInt(p -> p.getValue())
                        .max();
        
        return (max.isPresent()) ? max.getAsInt() : 0;
    }
    
    public Long getId() {
        return id;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Sellable getItem() {
        return item;
    }

    public HashMap<LocalDateTime, Pair<User, Integer>> getBids() {
        return bids;
    }

    public int getValuedPrice() {
        return valuedPrice;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
