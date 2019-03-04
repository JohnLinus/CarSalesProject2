package Controller;

import Entities.Auction;
import Entities.Bid;
import Entities.Bidder;
import Entities.Car;
import Entities.CarSize;
import REST.AuctionWrapper;
import REST.BidderWrapper;
import dao.DaoFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AuctionControllerTest {

    public AuctionControllerTest() {
    }

    AuctionController ac;

    @Before
    public void setup() {
        ac = new AuctionController();
        ac.setDao(new MockDao());
    }

    @Test
    public void addAuctionTest() {
        ac.add(new Auction());
        
        
        assertEquals("create(Auction)", MockDao.getCalledMethod());
    }

    @Test
    public void getAuctionTest() {
        ac.get(1L);
        
        assertEquals("get(Auction,1)", MockDao.getCalledMethod());
    }

    @Test
    public void getAllTest() {
        ac.getAll();
        
        assertEquals("getAll(Auction)", MockDao.getCalledMethod());
    }

    @Test
    public void getAllAvaliableTest() {
        ac.getAllAvaliable();
        
        assertEquals("getAuctionBySold(false)", MockDao.getCalledMethod());
    }

    @Test
    public void getAllSoldTest() {
        ac.getAllSold();
                
        assertEquals("getAuctionBySold(true)", MockDao.getCalledMethod());
    }

    @Test
    public void getByEndTimeTest() {
        LocalDateTime min = LocalDateTime.now().minusDays(5L);
        LocalDateTime max = LocalDateTime.now().plusDays(5L);
        
        ac.getByEndTime(min, max);
        
        assertEquals("getAuctionByEndTime("+min+","+max+")", MockDao.getCalledMethod());
    }
    @Test
    public void getByEndTimeReversedTest() {
        LocalDateTime min = LocalDateTime.now().minusDays(5L);
        LocalDateTime max = LocalDateTime.now().plusDays(5L);
        
        ac.getByEndTime(max, min);
        
        assertEquals("getAuctionByEndTime("+min+","+max+")", MockDao.getCalledMethod());
    }

    @Test
    public void unwrapAuctionTest() {
        Bid[] bids = MockDao.getBids(11L,12L);
        Car c = MockDao.getCar(13L);
        
        Auction a1 = new Auction();
        a1.setBids(Arrays.asList(bids));
        a1.setId(1L);
        a1.setItem(c);
        a1.setReservationPrice(1);
        a1.setTimeOfEnd(LocalDateTime.now());
        
        AuctionWrapper aw = new AuctionWrapper(a1);
        c.setAuction(null);
        
        
        Auction a2 = ac.unwrap(aw);
        
//        assertEquals(a1, a2);
        assertEquals(a1.getId(), a2.getId());
        assertEquals(a1.getReservationPrice(), a2.getReservationPrice());
        assertEquals(a1.getValuedPrice(), a2.getValuedPrice());
        assertEquals(a1.getTimeOfEnd(), a2.getTimeOfEnd());
        assertEquals(a1.getBids().get(0).getId(), a2.getBids().get(0).getId());
        assertEquals(a1.getBids().get(1).getId(), a2.getBids().get(1).getId());
        assertEquals(a1.getItem().getId(), a2.getItem().getId());
        
    }

    @Test
    public void unwrapBidderTest() {
        Bid[] bids = MockDao.getBids(11L, 12L);
        
        Bidder b1 = new Bidder();
        b1.setBids(Arrays.asList(bids));
        b1.setId(1L);
        b1.setAddress("testAddress");
        b1.setName("testName");
        b1.setPhone("testPhone");
        
        Bidder b2 = ac.unwrap(new BidderWrapper(b1));
        
        
//        assertEquals(b1, b2);
        assertEquals(b1.getId(), b2.getId());
        assertEquals(b1.getName(), b2.getName());
        assertEquals(b1.getPhone(), b2.getPhone());
        assertEquals(b1.getAddress(), b2.getAddress());
        assertEquals(b1.getBids().get(0).getId(), b2.getBids().get(0).getId());
        assertEquals(b1.getBids().get(1).getId(), b2.getBids().get(1).getId());
    }

    @Test
    public void legalBidTest() {
        Auction a = new Auction();
        a.setId(1L);
        a.setTimeOfEnd(LocalDateTime.MAX);
        
        Bidder b = MockDao.getBidder(2L);
        ac.add(a);
        
        int amount = 1;
        LocalDateTime now = LocalDateTime.now();
        
        boolean bidAdded = ac.bid(a, b, amount);
        
        
        assertTrue(bidAdded);
        
        Bid bid = MockDao.getCreatedBid();
        assertEquals(a, bid.getAuction());
        assertEquals(b, bid.getBidder());
        
        assertTrue(bid.getTimeOfBid().isAfter(now));
        assertTrue(bid.getTimeOfBid().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertEquals(amount, bid.getAmount());
    }

    @Test
    public void tooLateBidTest() {
        Auction a = new Auction();
        a.setId(1L);
        a.setTimeOfEnd(LocalDateTime.MIN);
        
        Bidder b = MockDao.getBidder(2L);
        ac.add(a);
        
        int amount = 1;
        
        boolean bidAdded = ac.bid(a, b, amount);
        
        assertFalse(bidAdded);
    }
    
    @Test
    public void tooLowBidTest() {
        Auction a = new Auction();
        a.setId(1L);
        a.setTimeOfEnd(LocalDateTime.MIN);
        
        Bidder b = MockDao.getBidder(2L);
        ac.add(a);
        
        int amount = -1;
        
        boolean bidAdded = ac.bid(a, b, amount);
        
        assertFalse(bidAdded);
    }
    
    @Test
    public void sameBidderBidTest() {
        Auction a = new Auction();
        a.setId(1L);
        a.setTimeOfEnd(LocalDateTime.MIN);
        
        Bidder b = MockDao.getBidder(2L);
        ac.add(a);
        
        int amount = 1;
        
        
        ac.bid(a, b, amount);
        boolean bidAdded = ac.bid(a, b, amount+1);
        
        assertFalse(bidAdded);
    }
    
    @Test
    public void severalBidsTest() {
        //TODO
    }
}







class MockDao implements DaoFacade {

    private static String methodsCalled;
    private static Bid[] bids = {new Bid(), new Bid()};
    private static Car car = new Car();
    private static Bid bid;
    private static Bidder bidder = new Bidder();
    
    public static String getCalledMethod() {
        return methodsCalled;
    }
    public static Bid[] getBids(long id1, long id2) {
        bids[0].setId(id1);
        bids[1].setId(id2);
        return bids;
    }
    public static Bid getCreatedBid() {
        return bid;
    }
    public static Bidder getBidder(long id) {
        bidder.setId(id);
        return bidder;
    }
    public static Car getCar(long id) {
        car.setId(id);
        return car;
    }

    List<Auction> aList = new ArrayList<>();

    public MockDao() {
    }

    @Override
    public void create(Object o) {
        methodsCalled = "create("+o.getClass().getSimpleName()+")";

        if (o instanceof Auction) {
            aList.add((Auction) o);
        } else if (o instanceof Bid) {
            bid = (Bid) o;
        } else {
            throw new IllegalArgumentException("Class " + o.getClass() + " should not be handled in this Controller");
        }
    }

    @Override
    public <T> T get(Class<T> oClass, Long id) {
        methodsCalled = "get("+oClass.getSimpleName()+","+id+")";

        if (oClass.equals(Auction.class)) {
            for (Auction a : aList) {
                if (a.getId().equals(id)) {
                    return (T) a;
                }
            }
        } else if (oClass.equals(Bid.class)) {
            for (Bid b : bids) {
                if (b.getId().equals(id)) {
                    return (T) b;
                }
            }
        } else if (oClass.equals(Bidder.class)) {
            if (bidder.getId().equals(id))
                return (T) bidder;
        } else if (oClass.equals(Car.class)) {
                if (car.getId().equals(id)) {
                    return (T) car;
                }
        } else {
            throw new IllegalArgumentException("Class " + oClass + " should not be handled in this Controller");
        }

        return null;
    }

    @Override
    public void update(Object o) {
        methodsCalled = "update("+o.getClass().getSimpleName()+")";
    }

    @Override
    public void delete(Object o) {
        methodsCalled = "delete("+o.getClass().getSimpleName()+")";

//        if (o instanceof Auction) {
//            aList.remove((Auction) o);
//        } else if (o instanceof Bidder) {
//            cList.remove((Bidder) o);
//        } else if (o instanceof Bid) {
//            bList.remove((Bid) o);
//        } else {
//            throw new IllegalArgumentException("Class " + o.getClass() + " should not be handled in this Controller");
//        }
    }

    @Override
    public <T> List<T> getAll(Class<T> oClass) {
        methodsCalled = "getAll("+oClass.getSimpleName()+")";

        if (oClass.equals(Auction.class)) {
            return (List<T>) aList;
        } else {
            throw new IllegalArgumentException("Class " + oClass + " should not be handled in this Controller");
        }
    }

    @Override
    public List<Auction> getAuctionBySold(boolean isSold) {
        methodsCalled = "getAuctionBySold("+isSold+")";
        return new ArrayList<>();
    }

    @Override
    public List<Auction> getAuctionByEndTime(LocalDateTime min, LocalDateTime max) {
        methodsCalled = "getAuctionByEndTime("+min+","+max+")";
        return new ArrayList<>();
    }

    @Override
    public List<Bidder> getBidderByName(String name) {
        throw new NoSuchMethodError("This method should be called");
    }

    @Override
    public List<Car> getCarBySize(CarSize size) {
        throw new NoSuchMethodError("This method should be called");
    }

    @Override
    public List<Car> getCarByModel(String model) {
        throw new NoSuchMethodError("This method should be called");
    }

    @Override
    public List<Car> getCarByManufacturer(String manufacturer) {
        throw new NoSuchMethodError("This method should be called");
    }

    @Override
    public List<Car> getCarByManufactureYear(int min, int max) {
        throw new NoSuchMethodError("This method should be called");
    }

    @Override
    public List<Car> getCarByHasAuction(boolean hasAuction) {
        throw new NoSuchMethodError("This method should be called");
    }
}
