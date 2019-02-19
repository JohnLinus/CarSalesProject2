package Controller;

import Entities.Car;
import Entities.CarSize;
import java.util.List;

public interface CarController {
//    Car get(Long id);
    List<Car> getAll();
    List<Car> getAllBySize(CarSize size);
    List<Car> getAllByManufacturer(String manufacturer);
    
    // Tries to place a bid, returns true/false depending on if the bid's gone through.
    boolean placeBid(Car car, int bid);
    
    void add(Car car);
    
    void delete(Car car);
}
