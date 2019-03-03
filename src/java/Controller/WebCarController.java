package Controller;

import Entities.Car;
import Entities.CarSize;
import dao.DaoFacade;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "webCarController")
@Stateless
public class WebCarController {
    
    private List<Car> cars;
    private String manufacturer;
    private String model;
    private Integer purchasePrice;
    private Integer manufactureYear;
    private CarSize type;
    

    @Inject
    DaoFacade dao;
    
    
    public void submit() {
        for (Object o : Arrays.asList(manufacturer, model, purchasePrice, manufactureYear, type)) {
            if (o == null)
                return;
        }
        
        add(new Car(model, purchasePrice, manufacturer, manufactureYear, type));
        updateList();
    }
    
    @PostConstruct
    void updateList() {
        cars = getAll();
    }
    
//    Car get(Long id){}

    public List<Car> getAll() {
        return dao.getAll(Car.class);
    }
    
    public void add(Car car){
        dao.create(car);
    }
    
    public void delete(Car car){
        dao.delete(car);
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public CarSize getType() {
        return type;
    }

    public void setType(CarSize type) {
        this.type = type;
    }
}