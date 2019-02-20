package Controller;

import Entities.Car;
import Entities.CarSize;
import dao.CarDao;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class CarController {
    
    @Inject
    CarDao dao;
    
//    Car get(Long id){}
    
    public List<Car> getAll() {
        return dao.retrieveAll();
    }
    public List<Car> getAllBySize(CarSize size){
        return dao.retrieveAll()
                .stream()
                .filter(c -> c.getType().equals(size))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByModel(String model){
        return dao.retrieveAll()
                .stream()
                .filter(c -> c.getModel().toLowerCase().contains(model.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByManufacturer(String manufacturer){
        return dao.retrieveAll()
                .stream()
                .filter(c -> c.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByPrice(int min, int max){
        return dao.retrieveAll()
                .stream()
                .filter(c -> c.getCurrentBidPrice() >= min && c.getCurrentBidPrice() <= max)
                .collect(Collectors.toList());
    }
    public List<Car> getAllByYear(int min, int max){
        return dao.retrieveAll()
                .stream()
                .filter(c -> c.getCurrentBidPrice() >= min && c.getCurrentBidPrice() <= max)
                .collect(Collectors.toList());
    }
    
    // Tries to place a bid, returns true/false depending on if the bid's gone through.
    public boolean placeBid(Car car, int bid){
        if (car.getCurrentBidPrice() < bid) {
            car.setCurrentBid(bid);
            dao.update(car);
            return true;
        }
        return false;
    }    
    public void add(Car car){
        dao.create(car);
    }
    
    public void delete(Car car){
        dao.delete(car);
    }
}
