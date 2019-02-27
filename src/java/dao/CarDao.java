package dao;

import Entities.Car;
import Entities.CarSize;
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
    
    public List<Car> getAllBySize(CarSize size){
        return em.createQuery("SELECT c FROM Car c WHERE c.type = :size")
                .setParameter("size", size)
                .getResultList();
    }
    public List<Car> getAllByModel(String model){
        return em.createQuery("SELECT c FROM Car c WHERE c.model LIKE CONCAT('%',:model,'%')")
                .setParameter("model", model)
                .getResultList();
    }
    public List<Car> getAllByManufacturer(String manufacturer){
        return em.createQuery("SELECT c FROM Car c WHERE c.manufacturer LIKE CONCAT('%',:manufacturer,'%')")
                .setParameter("manufacturer", manufacturer)
                .getResultList();
    }
    public List<Car> getAllBySalePrice(int min, int max){
        return em.createQuery("SELECT c FROM Car c WHERE c.salePrice BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
    public List<Car> getAllByCurrentBid(int min, int max){
        return em.createQuery("SELECT c FROM Car c WHERE c.currentBidPrice BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
    public List<Car> getAllByManufactureYear(int min, int max){
        return em.createQuery("SELECT c FROM Car c WHERE c.manufactureYear BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }
}
