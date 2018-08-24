package struts2.reader.repository;


import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import struts2.common.basic.BaseRepository;
import struts2.common.basic.SearchResult;
import struts2.common.entity.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;


@Repository
public class ReaderRepository extends BaseRepository<Reader> {

    final int PAGE_SIZE = 4;

    public SearchResult<Reader> searchReader(String key, String columnSearch, int page) {
        return search(Reader.class, key, columnSearch, page, PAGE_SIZE);
    }

    public List<Reader> getListReader(int page) {
        return getList(Reader.class, page, PAGE_SIZE);
    }

    public List<Reader> getAllReaders() {
        return super.getAll(Reader.class);
    }

    public Reader getReaderById(int id) {
        return getById(Reader.class, id);
    }

    public boolean saveOrUpdateReader(Reader Reader) {
        return saveOrUpdate(Reader);
    }


    public boolean deleteReader(int id) {
        return delete(Reader.class, id);
    }

    public int getTotalPage() {
        return super.getTotalPage("Reader", PAGE_SIZE);
    }

    public int getTotalPageSearch(String key, String columnSearch) {
        return super.getTotalPageSearch("Reader", key, columnSearch, PAGE_SIZE);
    }

    public int totalResultSearchReader(String key, String columnName) {
        return super.totalResultSearch("Reader", key, columnName);
    }

    public List<Reader> getTop5NewReader() {
        String hql = "SELECT r FROM Reader r Order by create_date desc";
        session = getSession();
        Query<Reader> query = session.createQuery(hql);
        query.setMaxResults(5);
        return query.list();
    }

    public HashMap<Reader, Integer> getTop5Reader() throws Exception {
        HashMap<Reader, Integer> top5 = new HashMap<>();
        String sql = "SELECT Top 5 Reader.id, COUNT(Reader.id) AS total FROM Reader INNER JOIN LoanCard ON Reader.id = LoanCard.Reader_id GROUP BY Reader.id";
        session = getSession();
        Connection connection = ((SessionImpl) session).connection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int total = rs.getInt("total");
            Reader reader = getReaderById(id);
            top5.put(reader, total);
        }

        return top5;
    }


}