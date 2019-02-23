package Controller;

import Entities.User;
import dao.UserDao;
import java.util.List;
import javax.inject.Inject;

public class UserController {
    
    @Inject
    UserDao dao;
    
    public void add(User user) {
        dao.create(user);
    }
    
    public List<User> get(String username) {
        return dao.getByUserName(username);
    }
    
}
