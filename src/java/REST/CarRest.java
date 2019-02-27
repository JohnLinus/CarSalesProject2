package REST;

import Controller.CarController;
import Entities.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/car")
public class CarRest {
    
    @Inject
    CarController cc;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars() {
        return cc.getCars();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        cc.add(car);
    }
}
