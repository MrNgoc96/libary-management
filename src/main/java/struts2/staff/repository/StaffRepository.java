package struts2.staff.repository;


import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import struts2.common.basic.BaseRepository;
import struts2.common.basic.SearchResult;
import struts2.common.entity.Staff;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;


@Repository
public class StaffRepository extends BaseRepository<Staff> {

    final int PAGE_SIZE = 4;

    public SearchResult<Staff> searchStaff(String key, String columnSearch, int page) {
        return search(Staff.class, key, columnSearch, page, PAGE_SIZE);
    }

    public List<Staff> getListStaff(int page) {
        return getList(Staff.class, page, PAGE_SIZE);
    }

    public List<Staff> getAllStaffs() {
        return super.getAll(Staff.class);
    }

    public int totalResultSearch(String key, String columnName) {
        return super.totalResultSearch("Staff", key, columnName);
    }

    public int getTotalPageSearch(String key, String columnSearch) {
        return super.getTotalPageSearch("Staff", key, columnSearch, PAGE_SIZE);
    }

    public Staff getStaffById(int id) {
        return getById(Staff.class, id);
    }

    public Staff getStaffByUsername(String username) {
        String hql = "SELECT s FROM Staff s WHERE s.username = :username ";
        Query<Staff> query = getSession().createQuery(hql);
        query.setParameter("username", username);
        if(query.list().size() <= 0) return null;
        return query.getSingleResult();
    }


    public boolean saveOrUpdateStaff(Staff Staff) {
        return super.saveOrUpdate(Staff);
    }


    public boolean deleteStaff(int id) {
        return delete(Staff.class, id);
    }


    public int getTotalPage() {
        return super.getTotalPage("Staff", PAGE_SIZE);
    }
}
