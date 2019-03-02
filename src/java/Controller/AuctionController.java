package Controller;

import Entities.Auction;
import Entities.Bid;
import Entities.Bidder;
import Entities.Car;
import REST.AuctionWrapper;
import REST.BidderWrapper;
import dao.ItemDao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuctionController {
    
    @Inject
    ItemDao dao;
    
    public Auction unwrap(AuctionWrapper aw) {
        Auction a = new Auction();
        a.setId(aw.getId());
        a.setReservationPrice(aw.getReservationPrice());
        a.setValuedPrice(aw.getValuedPrice());
        a.setTimeOfEnd(aw.getTimeOfEnd());
        if (aw.getItem() != null)
            a.setItem(dao.get(Car.class, aw.getItem()));
        if (aw.getBids() != null)
            a.setBids(aw.getBids().stream().map(b -> dao.get(Bid.class, b)).collect(Collectors.toList()));
        
        return a;
    }
    
    public Bidder unwrap(BidderWrapper bw) {
        Bidder b = new Bidder();
        b.setId(bw.getId());
        b.setName(bw.getName());
        b.setPhone(bw.getPhone());
        b.setAddress(bw.getAddress());
        if (bw.getBids() != null)
            b.setBids(bw.getBids().stream().map(bid -> dao.get(Bid.class, bid)).collect(Collectors.toList()));
        
        return b;
    }
    
    public void add(Auction auction) {
        dao.create(auction);
    }
    
    public List<Auction> getAllAvaliable() {
        return dao.getAuctionBySold(false);
    }
    
    public List<Auction> getAllSold() {
        return dao.getAuctionBySold(true);
    }
    
//    public List<Auction> getByHighestBid(int min, int max, boolean sold) {
//        return dao.getAuctionByHighestBid(min, max, sold);
//    }
    
    public List<Auction> getByEndTime(LocalDateTime min, LocalDateTime max) {
        return dao.getAuctionByEndTime(min, max);
    }
    
    public boolean bid(Auction auction, Bidder bidder, int bid) {
        if(
                auction.getTimeOfEnd().isBefore(LocalDateTime.now()) 
                || auction.getHighestBid() > bid
                || (auction.getLatestBidder() != null) ? 
                auction.getLatestBidder().equals(bidder) : false)
            return false;
        
        Bid b = new Bid(bid);
        
        dao.get(Auction.class, auction.getId()).addBid(b);
        dao.get(Bidder.class, bidder.getId()).addBid(b);
        
        dao.create(b);
        return true;
    }

    public Auction get(Long id) {
        return dao.get(Auction.class, id);
    }

    public List<Auction> getAll() {
        return dao.getAll(Auction.class);
    }
}
