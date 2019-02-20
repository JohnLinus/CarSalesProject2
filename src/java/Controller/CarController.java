package Controller;

import Entities.Car;
import Entities.CarSize;
import dao.CarDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Persistence;

@RequestScoped
public class CarController {
    
    @Inject
    CarDao dao;
//    CarDao dao = new MockCarDao();
    
//    Car get(Long id){}
    
    public List<Car> getAll() {
        return dao.getAll();
    }
    public List<Car> getAllBySize(CarSize size){
        return dao.getAll()
                .stream()
                .filter(c ->  c.getType().equals(size))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByModel(String model){
        return dao.getAll()
                .stream()
                .filter(c -> c.getModel().toLowerCase().contains(model.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByManufacturer(String manufacturer){
        return dao.getAll()
                .stream()
                .filter(c -> c.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Car> getAllByPrice(int min, int max){
        return dao.getAll()
                .stream()
                .filter(c -> c.getCurrentBidPrice() >= min && c.getCurrentBidPrice() <= max)
                .collect(Collectors.toList());
    }
    public List<Car> getAllByManufactureYear(int min, int max){
        return dao.getAll()
                .stream()
                .filter(c -> c.getManufactureYear() >= min && c.getManufactureYear() <= max)
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

class MockCarDao extends CarDao {
    
    private ArrayList<Car> list = new ArrayList<>();
    
    public void create(Car car){
        list.add(car);
    }
    
    public void update(Car car){
        //TODO
    }
    
    public void delete(Car car){
        list.remove(car);
    }
    
    public Car get(Long id){
        for (Car car : list) {
            if (car.getId().equals(id))
                return car;
        }
        return null;
    }
    public List<Car> getAll(){
        return list;
    }
}
