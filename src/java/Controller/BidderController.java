package Controller;

import Entities.Bid;
import Entities.Bidder;
import REST.BidderWrapper;
import dao.ItemDao;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BidderController {
    
    @Inject
    ItemDao dao;
    
    public Bidder unwrap(BidderWrapper bw) {
        Bidder b = new Bidder();
        b.setId(bw.getId());
        b.setName(bw.getName());
        b.setPhone(bw.getPhone());
        b.setAddress(bw.getAddress());
        b.setBids(bw.getBids().stream().map(bid -> dao.get(Bid.class, bid)).collect(Collectors.toList()));
        
        return b;
    }
    
    public void add(Bidder bidder) {
        dao.create(bidder);
    }
    
    public List<Bidder> get(String username) {
        return dao.getBidderByName(username);
    }

    public Bidder get(Long id) {
        return dao.get(Bidder.class, id);
    }

    public List<Bidder> getAll() {
        return dao.getAll(Bidder.class);
    }
}
