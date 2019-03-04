package REST;

import Controller.CarController;
import Entities.Car;
import Entities.CarSize;
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
@Path("/car")
public class CarRest {
    
    @Inject
    CarController cc;
    
    private List<CarWrapper> wrapList(List<Car> list) {
        return list.stream()
                .map(c -> new CarWrapper(c))
                .collect(Collectors.toList());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCar(Car car) {
        cc.add(car);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCars() {
        return wrapList(cc.getCars());
    }
    
    @GET
    @Path("/claimed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCarsWithAuctions() {
        return wrapList(cc.getByHasAuction(true));
    }
    
    @GET
    @Path("/unclaimed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCarsWithoutAuctions() {
        return wrapList(cc.getByHasAuction(false));
    }
    
    @GET
    @Path("/year/{min}/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCarsByManufactureYear(@PathParam("min") int min, @PathParam("max") int max) {
        return wrapList(cc.getByManufactureYear(min, max));
    }
    
    @GET
    @Path("/manufacturer/{manu}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCarsByManufacturer(@PathParam("manu") String manufacturer) {
        return wrapList(cc.getByManufacturer(manufacturer));
    }
    
    @GET
    @Path("/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCarsByModel(@PathParam("model") String model) {
        return wrapList(cc.getByModel(model));
    }
    
    @GET
    @Path("/size/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarWrapper> getCars(@PathParam("size") int size) {
        return wrapList(cc.getbySize(CarSize.values()[size]));
    }
}
