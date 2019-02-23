package Controller;

import Entities.Car;
import dao.CarDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CarController {
    
    public CarController() {}
    
    @Inject
    CarDao dao;
    
    public void add(Car car) {
        dao.create(car);
    }
    
    public List<Car> getCars() {
        return dao.getAll();
    }
}
