package REST;

import Controller.AuctionController;
import Entities.Auction;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("auction")
public class AuctionRest {
    
    @Inject
    AuctionController ac;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Auction> getAvaliableAuctions() {
        return ac.getAllAvaliable();
    }
    
    @GET
    @Path("/sold")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Auction> getSoldAuctions() {
        return ac.getAllSold();
    }
    
}
