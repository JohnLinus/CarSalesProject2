package Controller;

import Entities.Car;
import dao.CarDao;
import javax.inject.Inject;

public class CarController {
    
    @Inject
    CarDao dao;
    
    public void add(Car car) {
        dao.create(car);
    }
}
