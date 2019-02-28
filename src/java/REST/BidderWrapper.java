package REST;

import Entities.Bid;
import Entities.Bidder;
import java.io.Serializable;

public class BidderWrapper implements Serializable {
    Long id;
    String name;
    String phone;
    String address;
    Bid[] bids;
    
    public BidderWrapper(Bidder b) {
        id = b.getId();
        name = b.getName();
        phone = b.getPhone();
        address = b.getAddress();
        bids = b.getBids().toArray(new Bid[b.getBids().size()]);
    }
}
