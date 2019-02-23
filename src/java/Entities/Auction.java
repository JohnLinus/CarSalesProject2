package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import javafx.util.Pair;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private final LocalDateTime start;
    private LocalDateTime end;
    
//    private final User owner;
    private final Sellable item;
    
    private Map<LocalDateTime, Pair<User, Integer>> bids;
    
    private int reservationPrice;
    private int valuedPrice;
    
    public Auction() {
        item = null;
        start = null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
