package dao;

import Entities.Bidder;
import Entities.Auction;
import Entities.Bid;
import Entities.Car;
import Entities.CarSize;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ItemDao {
    
    @PersistenceContext
    EntityManager em;
    
    private final Map<Class, String> getAllMap = new HashMap<>();
    private final Class[] acceptedClasses = {Car.class, Auction.class, Bidder.class, Bid.class};
    
    private final String USER_NAME = "SELECT e FROM User e WHERE e.name like :name";
    
    private final String CAR_SIZE = "SELECT c FROM Car c WHERE c.type = :size";
    private final String CAR_MODEL = "SELECT c FROM Car c WHERE c.model LIKE CONCAT('%',:model,'%')";
    private final String CAR_MANUFACTURER = "SELECT c FROM Car c WHERE c.manufacturer LIKE CONCAT('%',:manufacturer,'%')";
    private final String CAR_MANUFACTURE_YEAR_BETWEEN = "SELECT c FROM Car c WHERE c.manufactureYear BETWEEN :min AND :max";
    private final String CAR_HAS_AUCTION = "SELECT c FROM Car c, Auction a WHERE a.item = c";
    private final String CAR_HAS_NO_AUCTION = "SELECT c FROM Car c, Auction a WHERE NOT a.item = c";
    
    private final String AUCTION_HIGHEST_BID_BETWEEN = "SELECT a FROM Auction a, Bid b WHERE b.auction LIKE a AND b.amount BETWEEN :min AND :max GROUP BY MAX(b.amount)";
    private final String AUCTION_END_TIME_BETWEEN = "SELECT a FROM Auction a WHERE a.timeOfEnd BETWEEN :min AND :max";
    private final String AUCTION_NOT_SOLD = "SELECT a FROM Auction a WHERE a.timeOfEnd > :now";
    private final String AUCTION_SOLD = "SELECT a FROM Auction a WHERE a.timeOfEnd <= :now";
    
    
    public ItemDao() {
        for (Class c : acceptedClasses) {
            getAllMap.put(c, String.format("SELECT e FROM %s e", c.getSimpleName()));
        }
    }

    private void checkIfAccepted(Class c) {
        for (Class ac : acceptedClasses) {
            if (c.equals(ac))
                return;
        }
        throw new IllegalArgumentException(c+" is not an accepted class");
    }
    
    /** 
     * Add an object to database.
     * 
     * @param o Object to be created in DB
     * 
     * @exception IllegalArgumentException if o.class isn't an entity class.
     */
    public void create(Object o) {
        em.persist(o);
    }
    
    /** 
     * Retrieve an object from database.
     * 
     * @param oClass class to search for and return.
     * @param id id of object to search for and return.
     * @return entity of class T, with given id.
     * 
     * @exception IllegalArgumentException if oClass isn't an entity class.
     */
    public <T> T get(Class<T> oClass, Long id) {
        return em.find(oClass, id);
    }
    
    /**
     * Edit an object in database.
     * 
     * @param o object to be edited.
     * 
     * @exception IllegalArgumentException if o.class isn't an entity class.
     */
    public void update(Object o) {
        o = em.merge(o);
    }
    
    /**
     * remove an entity from database.
     * 
     * @param o entity to be removed.
     * 
     * @exception IllegalArgumentException if o.class isn't an entity class.
     */
    public void delete(Object o) {
        em.remove(o);
    }
    
    // getAll car, auction, user
    /**
     * Retrieve a List of every entity of class oClass from database.
     * 
     * @param oClass class to be searched for in database.
     * @return List of class oClass.
     * 
     * @exception IllegalArgumentException if oClass isn't an entity class.
     */
    public <T> List<T> getAll(Class<T> oClass) {
        checkIfAccepted(oClass);
        return em.createQuery(getAllMap.get(oClass))
                .getResultList();
    }
    
    
    
    // Non-generic
    
    
    /**
     * Searches through database for users with given name
     * 
     * @param name name to search for
     * @return list of all users with given name
     */
    public List<Bidder> getUserByName(String name) {
        return em.createQuery(USER_NAME)
                .setParameter("name", name)
                .getResultList();
    }
    
    /**
     * Searches through database for cars with given size
     * @param size size to search for
     * @return 
     */
    public List<Car> getCarBySize(CarSize size) {
        return em.createQuery(CAR_SIZE)
                .setParameter("size", size)
                .getResultList();
    }
    
    /**
     * Searches through database for cars with given model
     * @param model
     * @return 
     */
    public List<Car> getCarByModel(String model) {
        return em.createQuery(CAR_MODEL)
                .setParameter("model", model)
                .getResultList();
    }
    
    /**
     * Searches through database for cars with given manufacturer
     * @param manufacturer
     * @return 
     */
    public List<Car> getCarByManufacturer(String manufacturer) {
        return em.createQuery(CAR_MANUFACTURER)
                .setParameter("manufacturer", manufacturer)
                .getResultList();
    }
    
    /**
     * Searches through database for cars with given manufacture year between min and max
     * @param min
     * @param max
     * @return 
     */
    public List<Car> getCarByManufactureYear(int min, int max) {
        return em.createQuery(CAR_MANUFACTURE_YEAR_BETWEEN)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
    
    /**
     * Searches through database for cars with or without an Auction attached
     * @param hasAuction 
     * @return 
     */
    public List<Car> getCarByHasAuction(boolean hasAuction) {
        return em.createQuery((hasAuction) ? CAR_HAS_AUCTION : CAR_HAS_NO_AUCTION)
                .getResultList();
    }
    
    /**
     * Searches through database for sold/not sold auctions with a highest bid between min and max.
     * @param min
     * @param max
     * @param sold
     * @return 
     */
    public List<Auction> getAuctionByHighestBid(int min, int max, boolean sold) {
        return em.createQuery(AUCTION_HIGHEST_BID_BETWEEN)
                .getResultList();
    }
    
    /**
     * Searches through database for sold or not sold auctions
     * @param sold
     * @return 
     */
    public List<Auction> getAuctionBySold(boolean sold) {
        LocalDateTime now = LocalDateTime.now();
        
        return em.createQuery((sold) ? AUCTION_SOLD : AUCTION_NOT_SOLD)
                .setParameter("now", now)
                .getResultList();
    }
    
    /**
     * Searches through database for auctions with ending between given dates
     * @param min earliest date
     * @param max latest date
     * @return
     */
    public List<Auction> getAuctionByEndTime(LocalDateTime min, LocalDateTime max) {
        return em.createQuery(AUCTION_END_TIME_BETWEEN)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
}
