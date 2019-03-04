package Controller;

import Entities.Auction;
import Entities.Bid;
import Entities.Bidder;
import Entities.Car;
import Entities.CarSize;
import REST.BidderWrapper;
import dao.DaoFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BidderControllerTest {
    
    private BidderController bc;
    private MockDao mockDao;
    private Bidder b;
    
    public BidderControllerTest() {
    }
    
    @Before
    public void setup() {
        bc = new BidderController();
        mockDao = new MockDao();
        
        
        bc.setDao(mockDao);
        b = new Bidder();
        b.setId(1L);
    }

    @Test
    public void addBidderTest() {
        List<Bidder> list = mockDao.getBidders();
        
        bc.add(b);
        
        assertEquals("create(Bidder)", mockDao.getCalledMethod());
        assertEquals(1, list.size());
        assertEquals(b, list.get(0));
    }

    @Test
    public void getBidderTest() {
        mockDao.getBidders().add(b);
        
        Bidder result = bc.get(1L);
        
        assertEquals("get(Bidder,1)", mockDao.getCalledMethod());
        assertEquals(b, result);
    }

    @Test
    public void getBiddersByNameTest() {
        String name = "testName";
        
        bc.get(name);
        
        assertEquals("getBidderByName("+name+")", mockDao.getCalledMethod());
    }

    @Test
    public void getAllTest() {
        bc.getAll();
        
        assertEquals("getAll(Bidder)", mockDao.getCalledMethod());
    }

    @Test
    public void unwrapTest() {
        Bid bid = new Bid();
        bid.setId(2L);
        Long id = 1L;
        String address = "testAdress";
        String name = "testName";
        String phone = "testPhone";
        b.setId(id);
        b.setAddress(address);
        b.setPhone(phone);
        b.setName(name);
        b.addBid(bid);
        BidderWrapper bw = new BidderWrapper(b);
        
        Bidder result = bc.unwrap(bw);
        
        assertEquals(b, result);
        assertEquals(name, result.getName());
        assertEquals(phone, result.getPhone());
        assertEquals(address, result.getAddress());
        assertEquals(id, result.getId());
        assertEquals(1, result.getBids().size());
        assertEquals(bid, result.getBids().get(0));
    }
    
}

class MockDao implements DaoFacade {

    private String methodCalled;
    
    List<Bidder> bidderList = new ArrayList<>();
    List<Auction> auctionList = new ArrayList<>();
    List<Bid> bidList = new ArrayList<>();
    List<Car> carList = new ArrayList<>();

    public MockDao() {
    }

    public String getCalledMethod() {
        return methodCalled;
    }
    public List<Auction> getAuctions() {
        return auctionList;
    }
    public List<Bidder> getBidders() {
        return bidderList;
    }
    public List<Bid> getBids() {
        return bidList;
    }
    public List<Car> getCars() {
        return carList;
    }


    @Override
    public void create(Object o) {
        methodCalled = "create(" + o.getClass().getSimpleName() + ")";

        switch(o.getClass().getSimpleName()) {
            case "Auction":
                auctionList.add((Auction) o);
                break;
            case "Bidder":
                bidderList.add((Bidder) o);
                break;
            case "Bid":
                bidList.add((Bid) o);
                break;
            case "Car":
                carList.add((Car) o);
                break;
            default:
                throw new IllegalArgumentException("Class " + o.getClass() + " should not be handled in this Controller");
        }
    }

    @Override
    public <T> T get(Class<T> oClass, Long id) {
        methodCalled = "get(" + oClass.getSimpleName() + "," + id + ")";

        if (oClass.equals(Auction.class)) {
            for (Auction a : auctionList) {
                if (a.getId().equals(id)) {
                    return (T) a;
                }
            }
        } else if (oClass.equals(Bidder.class)) {
            for (Bidder b : bidderList) {
                if (b.getId().equals(id)) {
                    return (T) b;
                }
            }
        } else if (oClass.equals(Bid.class)) {
            for (Bid b : bidList) {
                if (b.getId().equals(id)) {
                    return (T) b;
                }
            }
        } else if (oClass.equals(Car.class)) {
            for (Car c : carList) {
                if (c.getId().equals(id)) {
                    return (T) c;
                }
            }
        } else {
            throw new IllegalArgumentException("Class " + oClass + " should not be handled in this Controller");
        }

        return null;
    }

    @Override
    public void update(Object o) {
        methodCalled = "update(" + o.getClass().getSimpleName() + ")";
    }

    @Override
    public void delete(Object o) {
        methodCalled = "delete(" + o.getClass().getSimpleName() + ")";
    }

    @Override
    public <T> List<T> getAll(Class<T> oClass) {
        methodCalled = "getAll(" + oClass.getSimpleName() + ")";

        if (oClass.equals(Auction.class)) {
            return (List<T>) auctionList;
        }else if (oClass.equals(Bidder.class)) {
            return (List<T>) bidderList;
        }else if (oClass.equals(Bid.class)) {
            return (List<T>) bidList;
        }else if (oClass.equals(Car.class)) {
            return (List<T>) carList;
        } else {
            throw new IllegalArgumentException("Class " + oClass + " should not be handled in this Controller");
        }
    }

    @Override
    public List<Bidder> getBidderByName(String name) {
        methodCalled = "getBidderByName("+name+")";
        return new ArrayList<>();
    }

    @Override
    public List<Car> getCarBySize(CarSize size) {
        methodCalled = "getCarBySize("+size+")";
        return new ArrayList<>();
    }

    @Override
    public List<Car> getCarByModel(String model) {
        methodCalled = "getCarByModel("+model+")";
        return new ArrayList<>();
    }

    @Override
    public List<Car> getCarByManufacturer(String manufacturer) {
        methodCalled = "getCarByManufacturer("+manufacturer+")";
        return new ArrayList<>();
    }

    @Override
    public List<Car> getCarByManufactureYear(int min, int max) {
        methodCalled = "getCarByManufactureYear("+min+","+max+")";
        return new ArrayList<>();
    }

    @Override
    public List<Car> getCarByHasAuction(boolean hasAuction) {
        methodCalled = "getCarByHasAuction("+hasAuction+")";
        return new ArrayList<>();
    }

    @Override
    public List<Auction> getAuctionBySold(boolean isSold) {
        methodCalled = "getCarBySold("+isSold+")";
        return new ArrayList<>();
    }

    @Override
    public List<Auction> getAuctionByEndTime(LocalDateTime min, LocalDateTime max) {
        methodCalled = "getCarByEndTime("+min+","+max+")";
        return new ArrayList<>();
    }
    
}