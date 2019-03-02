package Controller;

import Entities.Auction;
import Entities.Bidder;
import dao.AuctionDao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuctionController {
    
    @Inject
    AuctionDao dao;
    
    public void add(Auction auction) {
        dao.create(auction);
    }
    
    public List<Auction> getAllAvaliable() {
        List<Auction> list = dao.getAll();
        if(list.isEmpty())
            return list;
        
        return list
                .stream()
                .filter(a -> LocalDateTime.now().isBefore(a.getTimeOfEnd()))
                .collect(Collectors.toList());
    }
    
    public List<Auction> getAllSold() {
        return dao.getAll()
                .stream()
                .filter(a -> 
                        LocalDateTime.now().isAfter(a.getTimeOfEnd()) && a.isBidsOverReservedPrice())
                .collect(Collectors.toList());
    }
    
    public boolean bid(Auction auction, Bidder bidder, int bid) {
        return false;
    }

    public Auction get(Long id) {
        return dao.get(id);
    }
}
