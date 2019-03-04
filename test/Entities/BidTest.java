package Entities;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BidTest {
    
    Bid b;
    
    public BidTest() {
    }
    @Before
    public void setup() {
        b = new Bid();
    }
    @Test
    public void addBidFromAuction() {
        b.setId(1L);
        Auction a = new Auction();
        a.setId(2L);
        
        a.addBid(b);
        
        assertEquals(a, b.getAuction());
        assertEquals(b, a.getBids().get(0));
    }
    @Test
    public void addBidFromBidder() {
        b.setId(1L);
        Bidder a = new Bidder();
        a.setId(2L);
        
        a.addBid(b);
        
        assertEquals(a.getId(), b.getBidder().getId());
        assertEquals(b.getId(), a.getBids().get(0).getId());
    }
    
}
