package Entities;

import Entities.Auction;
import Entities.CarSize;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-02T14:46:31")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile SingularAttribute<Car, CarSize> size;
    public static volatile SingularAttribute<Car, String> model;
    public static volatile SingularAttribute<Car, Long> id;
    public static volatile SingularAttribute<Car, Integer> purchasePrice;
    public static volatile SingularAttribute<Car, Integer> manufactureYear;
    public static volatile SingularAttribute<Car, String> manufacturer;
    public static volatile SingularAttribute<Car, Auction> auction;

}