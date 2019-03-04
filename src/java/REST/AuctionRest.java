package REST;

import Controller.AuctionController;
import Entities.Auction;
import Entities.Bidder;
import java.util.List;
import java.util.stream.Collectors;
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
    public void createAuction(AuctionWrapper auction) {
        ac.add(ac.unwrap(auction));
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AuctionWrapper getAuction(@PathParam("id") Long id) {
        Auction a = ac.get(id);
        if (a == null)
            return null;
        return new AuctionWrapper(a);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AuctionWrapper> getAvaliableAuctions() {
        return ac.getAll().stream()
                .map(a -> new AuctionWrapper(a))
                .collect(Collectors.toList());
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AuctionWrapper> getAvaliableAuctions() {
//        return ac.getAllAvaliable().stream()
//                .map(a -> new AuctionWrapper(a))
//                .collect(Collectors.toList());
//    }
    
//    @GET
//    @Path("/sold")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AuctionWrapper> getSoldAuctions() {
//        return ac.getAllSold()
//                .stream()
//                .map(a -> new AuctionWrapper(a))
//                .collect(Collectors.toList());
//    }
    
//    @GET
//    @Path("/end")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AuctionWrapper> getByEndTime(List<LocalDateTime> arr) {
//        if (arr.size() != 2)
//            return null;
//        
//        return ac.getByEndTime(arr.get(0), arr.get(1))
//                .stream()
//                .map(a -> new AuctionWrapper(a))
//                .collect(Collectors.toList());
//    }
    
//    @GET
//    @Path("/{min}/{max}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AuctionWrapper> getByHighestBid(@PathParam("min") int min, @PathParam("max") int max) {
//        return ac.getByHighestBid(min, max, false)
//                .stream()
//                .map(a -> new AuctionWrapper(a))
//                .collect(Collectors.toList());
//    }
    
//    @GET
//    @Path("/sold/{min}/{max}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<AuctionWrapper> getSoldByHighestBid(@PathParam("min") int min, @PathParam("max") int max) {
//        return ac.getByHighestBid(min, max, true)
//                .stream()
//                .map(a -> new AuctionWrapper(a))
//                .collect(Collectors.toList());
//    }
    
    @PUT
    @Path("/{auctionId}/{bid}")
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public boolean bid(BidderWrapper bidder, @PathParam("auctionId") Long auctionId, @PathParam("bid") int bid) {
        return ac.bid(ac.get(auctionId), ac.unwrap(bidder), bid);
    }
    
}
