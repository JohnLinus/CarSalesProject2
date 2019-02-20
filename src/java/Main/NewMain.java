package Main;

import Controller.CarController;
import Entities.Car;
import Entities.CarSize;

public class NewMain {

    public static void main(String[] args) {
        CarController cc = new CarController();
        
        cc.add(new Car("f4", 50000, "Volvo", 2008, CarSize.MINI));
        System.out.println(cc.getAll());
        
    }
}