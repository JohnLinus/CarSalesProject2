package REST;

import Controller.AuctionController;
import Entities.Auction;
import Entities.Bidder;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/auction")
public class AuctionRest {

    @Inject
    AuctionController ac;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAuction(Auction auction) {
        ac.add(auction);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Auction getAuction(@PathParam("id") Long id) {
        return ac.get(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auction> getAvaliableAuctions() {
        return ac.getAllAvaliable();
    }
    
    @GET
    @Path("/sold")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Auction> getSoldAuctions() {
        return ac.getAllSold();
    }
    
    @PUT
    @Path("/{auctionId}/{bid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean bid(Bidder user, @PathParam("auctionId") Long auctionId, @PathParam("bid") int bid) {
        return ac.bid(getAuction(auctionId), user, bid);
    }
    
}
