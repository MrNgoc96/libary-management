package struts2.common.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import struts2.common.entity.User;


import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean login(String username, String password) {
        return userRepository.login(username, password);
    }


    public List<User> getListUser(int page) {
        return userRepository.getListUser(page);
    }


    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public boolean saveOrUpdateUser(User user) {
        return userRepository.saveOrUpdateUser(user);
    }


    public boolean deleteUser(String username) {
        return userRepository.deleteUser(username);
    }


}

