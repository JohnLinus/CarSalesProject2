package Main;

import Entities.Car;

public class NewMain {

    public static void main(String[] args) {
        
        Car car = new Car();
        
        car.setCurrentBid(23);

        System.out.println(car);
    }
}