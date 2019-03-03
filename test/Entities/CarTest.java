package Entities;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CarTest {

    Car c;
    
    public CarTest() {
    }
    
    @Before
    public void setup() {
        c = new Car();
    }
    
    @Test
    public void addingCarToAuctionTest() {
        Auction a = new Auction();
        a.setId(2L);
        
        a.setItem(c);
        
        assertEquals(a.getId(), c.getAuction().getId());
    }

}
