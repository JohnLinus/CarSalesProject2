package dao;

import Entities.Auction;
import Entities.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuctionDao {
    
    @PersistenceContext
    EntityManager em;
    
    public List<Auction> getAll(){
        return em.createQuery("SELECT a FROM Auction a")
                .getResultList();
    }
    
    public void update(Auction auction) {
        auction = em.merge(auction);
    }
    
    public void create(Auction auction) {
        em.persist(auction);
    }
    
    public void addBid(LocalDateTime time, Auction auction, User user, int bid) {
        em.find(Auction.class, auction.getId()).bid(time, user, bid);
        em.find(User.class, user.getId()).bid(time, auction);
    }
    
}
