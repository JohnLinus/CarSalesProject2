package REST;

import Controller.BidderController;
import Entities.Bidder;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/bidder")
public class BidderRest {

    @Inject
    BidderController bc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBidder(BidderWrapper bidder) {
        bc.add(bc.unwrap(bidder));
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BidderWrapper getBidder(@PathParam("id") Long id) {
        Bidder b = bc.get(id);
        if (b == null)
            return null;
        return new BidderWrapper(b);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BidderWrapper> getBidders() {
        return bc.getAll()
                .stream()
                .map(b -> new BidderWrapper(b))
                .collect(Collectors.toList());
    }
    
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BidderWrapper> getBiddersByName(@PathParam("name") String name) {
        return bc.get(name)
                .stream()
                .map(b -> new BidderWrapper(b))
                .collect(Collectors.toList());
    }

}
