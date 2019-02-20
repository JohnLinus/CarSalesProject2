package dao;

import Entities.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CarDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public void create(Car car){
        em.persist(car);
    }
    
    public void update(Car car){
        car = em.merge(car);
    }
    
    public void delete(Car car){
        em.remove(car);
    }
    
    public Car get(Long id){
        return em.find(Car.class, id);
    }
    public List<Car> getAll(){
        return em.createQuery("SELECT e FROM Car e").getResultList();
    }
}
