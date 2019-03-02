package REST;

import Entities.Auction;
import Entities.Bid;
import Entities.Bidder;
import Entities.Car;
import Entities.CarSize;
import dao.ItemDao;
import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.rowset.serial.SerialArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/test")
public class TestRest {
    
    @Inject
    ItemDao dao;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces(MediaType.APPLICATION_XML)
//    @Consumes(MediaType.APPLICATION_JSON)
    public CarWrapper test() {
        
        dao.create(new Car("test", 9000, "test2", 1999, CarSize.MINI));
        dao.create(new Bidder("test1", "test2", "test3"));
        dao.create(new Auction(LocalDateTime.MIN, 1000));
        
        Auction a = dao.get(Auction.class, 3L);
        Bidder b = dao.get(Bidder.class, 2L);
        Car c = dao.get(Car.class, 1L);
        a.setItem(c);
        
        bid(a, b, 100);
        
//        Bid bid = new Bid(200);
//        dao.create(bid);
//        a.addBid(bid);
//        b.addBid(bid);
        
        return new CarWrapper(dao.get(Car.class, 1L));
//        return b.toString();
    }
    
    private void bid(Auction a, Bidder b, int amount) {
        Bid bid = new Bid(amount);
        a.addBid(bid);
        b.addBid(bid);
        
        dao.create(bid);
    }
}
