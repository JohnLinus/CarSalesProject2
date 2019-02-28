package Entities;

import Entities.Bid;
import Entities.Car;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-28T20:23:09")
@StaticMetamodel(Auction.class)
public class Auction_ { 

    public static volatile SingularAttribute<Auction, LocalDateTime> timeOfEnd;
    public static volatile SingularAttribute<Auction, Car> item;
    public static volatile SingularAttribute<Auction, Integer> reservationPrice;
    public static volatile ListAttribute<Auction, Bid> bids;
    public static volatile SingularAttribute<Auction, Long> id;
    public static volatile SingularAttribute<Auction, Integer> valuedPrice;

}