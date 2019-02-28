package Controller;

import Entities.Bidder;
import dao.UserDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserController {
    
    @Inject
    UserDao dao;
    
    public void add(Bidder user) {
        dao.create(user);
    }
    
    public List<Bidder> get(String username) {
        return dao.getByUserName(username);
    }

    public Bidder get(Long id) {
        return dao.get(id);
    }
}
