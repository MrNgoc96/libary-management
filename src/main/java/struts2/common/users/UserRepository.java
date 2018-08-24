package struts2.common.users;



import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import struts2.common.basic.BaseRepository;
import struts2.common.entity.User;


import java.util.List;

@Repository
public class UserRepository extends BaseRepository<User> {

    final int PAGE_SIZE = 4;

    public boolean login(String username, String password) {
        String hql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
        Query<User> query = getSession().createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.list().size() >= 1;
    }


    public List<User> getListUser(int page) {
        return getList(User.class, page, PAGE_SIZE);
    }


    public User getUser(String username) {
        return getById(User.class,username);
    }


    public boolean saveOrUpdateUser(User User) {
        return saveOrUpdate(User);
    }


    public boolean deleteUser(String username) {
        return delete(User.class,username);
    }

}

