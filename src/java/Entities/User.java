package Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private Map<LocalDateTime, Auction> bids;
    
    private String name;
    private String phone;
    private String address;
}
