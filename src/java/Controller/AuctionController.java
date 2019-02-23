package Controller;

import Entities.Auction;
import Entities.User;
import dao.AuctionDao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class AuctionController {
    
    @Inject
    AuctionDao dao;
    
    public void add(Auction auction) {
        dao.create(auction);
    }
    
    public List<Auction> getAllAvaliable() {
        return dao.getAll()
                .stream()
                .filter(a -> LocalDateTime.now().isBefore(a.getEnd()))
                .collect(Collectors.toList());
    }
    
    public List<Auction> getAllSold() {
        return dao.getAll()
                .stream()
                .filter(a -> 
                        LocalDateTime.now().isAfter(a.getEnd()) && a.isBidsOverReservedPrice())
                .collect(Collectors.toList());
    }
    
    public boolean bid(Auction auction, User user, int bid) {
        LocalDateTime now = LocalDateTime.now();
        
        while (auction.getBids().containsKey(now) || user.getBids().containsKey(now))
            now = now.plusNanos(1);
        
        if (        now.isAfter(auction.getEnd()) // if auction has ended
                ||  auction.getHighestBid() >= bid  // if given bid is high enough
                ||  auction.getBids().get(auction.getLatestBid()).getKey().equals(user))  // if the auctions highest bidder is user
            return false;
        
        dao.addBid(now, auction, user, bid);
        return true;
    }
}
