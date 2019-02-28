package dao;

import Entities.Auction;
import Entities.Bidder;
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
    
    public void addBid(LocalDateTime time, Auction auction, Bidder user, int bid) {
    }

    public Auction get(Long id) {
        return em.find(Auction.class, id);
    }
}
