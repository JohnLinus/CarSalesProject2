package dao;

import Entities.Car;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public Car retrieve(Long id){
        return em.find(Car.class, id);
    }
    public List<Car> retrieveAll(){
        return em.createQuery("SELECT e FROM Car e").getResultList();
    }
}
