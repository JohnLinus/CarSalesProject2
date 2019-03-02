package Entities;

import Entities.Auction;
import Entities.Bidder;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-02T19:46:31")
@StaticMetamodel(Bid.class)
public class Bid_ { 

    public static volatile SingularAttribute<Bid, Integer> amount;
    public static volatile SingularAttribute<Bid, Bidder> bidder;
    public static volatile SingularAttribute<Bid, Long> id;
    public static volatile SingularAttribute<Bid, LocalDateTime> timeOfBid;
    public static volatile SingularAttribute<Bid, Auction> auction;

}