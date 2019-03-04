package REST;

import Entities.Auction;
import Entities.Bid;
import Entities.Bidder;
import Entities.Car;
import Entities.CarSize;
import dao.DaoFacade;
import dao.ItemDao;
import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.rowset.serial.SerialArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/test")
public class TestRest {

    @Inject
    DaoFacade dao;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces(MediaType.APPLICATION_XML)
//    @Consumes(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
    public Bidder test() {
        
        dao.create(new Car("test", 9000, "test2", 1999, CarSize.MINI));
        dao.create(new Bidder("test1", "test2", "test3"));
        dao.create(new Auction(LocalDateTime.now().plusDays(7), 1000, dao.get(Car.class, 1L)));
        
        Auction a = dao.get(Auction.class, 3L);
        Bidder b = dao.get(Bidder.class, 2L);
        Car c = dao.get(Car.class, 1L);
        bid(a, b, 100);
//        b.setBids(null);
        return b;
=======
    public List<AuctionWrapper> test() {
        
        fillDB();

//        dao.create(new Car("test", 9000, "test2", 1999, CarSize.MINI));
//        dao.create(new Bidder("test1", "test2", "test3"));
//        dao.create(new Auction(LocalDateTime.MIN, 1000));
//
//        Auction a = dao.get(Auction.class, 3L);
//        Bidder b = dao.get(Bidder.class, 2L);
//        Car c = dao.get(Car.class, 1L);
//        a.setItem(c);
//
//        bid(a, b, 100);

//        Bid bid = new Bid(200);
//        dao.create(bid);
//        a.addBid(bid);
//        b.addBid(bid);
//        return new CarWrapper(dao.get(Car.class, 1L));
//        return b.toString();
        return dao.getAll(Auction.class).stream().map(a -> new AuctionWrapper(a)).collect(Collectors.toList());
>>>>>>> 98a9086bf092eaf1263ffb9360fcc871358eeb9e
    }

    private void bid(Auction a, Bidder b, int amount) {
        Bid bid = new Bid(amount);
        a.addBid(bid);
        b.addBid(bid);

        dao.create(bid);
    }

    private void fillDB() {
        Random rand = new Random();

        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'};
        String[] carModel = new String[20];

        for (int i = 0; i < carModel.length; i++) {
            carModel[i] = "" + letters[rand.nextInt(letters.length)] + (rand.nextInt(990) + 10);
        }

        int carPriceMin = 9000;
        int carPriceMax = 30000;
        String[] carManu = {"Volvo", "BMW", "Toyota", "Honda", "Hundai", "Tesla", "SAAB", "Ferarri", "Ford", "Audi"};
        int carManuYearMin = 1990;
        int carManuYearMax = 2018;
        CarSize[] carSize = CarSize.values();

        for (String model : carModel) {
            dao.create(new Car(
                    model,
                    rand.nextInt(carPriceMax - carPriceMin) + carPriceMin,
                    carManu[rand.nextInt(carManu.length)],
                    rand.nextInt(carManuYearMax - carManuYearMin) + carManuYearMin,
                    carSize[rand.nextInt(carSize.length)]));
        }

        for (String s : carModel) {
            dao.create(new Auction(LocalDateTime.now().plusDays(rand.nextInt(130) - 120), 0));
        }

        List<Auction> aList = dao.getAll(Auction.class);
        List<Car> cList = dao.getAll(Car.class);

        for (int i = 0; i < aList.size(); i++) {
            aList.get(i).setItem(cList.get(i));
            aList.get(i).setReservationPrice(cList.get(i).getPurchasePrice() * 3);
        }

        dao.create(new Car(
                "Test123",
                rand.nextInt(carPriceMax - carPriceMin) + carPriceMin,
                carManu[rand.nextInt(carManu.length)],
                rand.nextInt(carManuYearMax - carManuYearMin) + carManuYearMin,
                carSize[rand.nextInt(carSize.length)]));

    }
}
