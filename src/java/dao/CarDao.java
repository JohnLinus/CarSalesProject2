package dao;

import Entities.Car;
import java.util.List;

public interface CarDao {
    void create(Car car);
    
    void update(Car car);
    
    void delete(Car car);
    
    Car retrieve(Long id);
    List<Car> retrieveAll(Long id);
}
