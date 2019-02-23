package dao;

import Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {
    
    @PersistenceContext
    EntityManager em;
    
    public List<User> getByUserName(String username){
        return em.createQuery("SELECT u FROM User u WHERE u.name LIKE :name")
                .setParameter("name", username)
                .getResultList();
    }
    
    public void create(User user) {
        em.persist(user);
    }
    
    public void update(User user) {
        user = em.merge(user);
    }
}
