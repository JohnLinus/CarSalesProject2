package Entities;

import Entities.Bid;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-01T16:23:30")
@StaticMetamodel(Bidder.class)
public class Bidder_ { 

    public static volatile SingularAttribute<Bidder, String> address;
    public static volatile SingularAttribute<Bidder, String> phone;
    public static volatile SingularAttribute<Bidder, String> name;
    public static volatile ListAttribute<Bidder, Bid> bids;
    public static volatile SingularAttribute<Bidder, Long> id;

}