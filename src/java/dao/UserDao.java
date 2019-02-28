package dao;

import Entities.Bidder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
    
    @PersistenceContext
    EntityManager em;
    
    public List<Bidder> getByUserName(String username){
        return em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name")
                .setParameter("name", username)
                .getResultList();
    }
    
    public Bidder get(Long id) {
        return em.find(Bidder.class, id);
    }
    
    public void create(Bidder user) {
        em.persist(user);
    }
    
    public void update(Bidder user) {
        user = em.merge(user);
    }
}
