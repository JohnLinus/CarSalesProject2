package REST;

import Entities.Bidder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BidderWrapper implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private List<Long> bids;

    public BidderWrapper() {
    }
    
    
    public BidderWrapper(Bidder b) {
        id = b.getId();
        name = b.getName();
        phone = b.getPhone();
        address = b.getAddress();
        if (b.getBids() != null)
            bids = b.getBids().stream()
                    .map(bid -> bid.getId())
                    .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getBids() {
        if(bids == null)
            return new ArrayList<>();
        return bids;
    }

    public void setBids(List<Long> bids) {
        this.bids = bids;
    }
    
    
}
