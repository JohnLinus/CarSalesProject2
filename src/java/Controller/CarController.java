package Controller;

import Entities.Car;

public class CarController {
    
    Car car;
    
    public void placeBid(int bid){
        car.setCurrentBid(bid);
    } 
    
    
}