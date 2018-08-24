package struts2.book.repository;


import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;import org.springframework.stereotype.Repository;
import struts2.common.basic.BaseRepository;
import struts2.common.basic.SearchResult;
import struts2.common.entity.Book;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;


@Repository
public class BookRepository extends BaseRepository<Book> {

    final int PAGE_SIZE = 4;


    public SearchResult<Book> searchBook(String key, String columnSearch, int page) {
        return search(Book.class, key, columnSearch, page,PAGE_SIZE);
    }

    public List<Book> getListBook(int page) {
        return getList(Book.class, page, PAGE_SIZE);
    }

    public List<Book> getAllBooks(){
        return super.getAll(Book.class);
    }

    public Book getBookById(int id) {
        return getById(Book.class, id);
    }

    public boolean saveOrUpdateBook(Book book) {
        return saveOrUpdate(book);
    }


    public boolean deleteBook(int id) {
        return delete(Book.class, id);
    }

    public int getTotalPage() {
        return super.getTotalPage("Book",PAGE_SIZE);
    }

    public int getTotalPageSearch(String key, String columnSearch) {
        return super.getTotalPageSearch("Book", key, columnSearch,PAGE_SIZE);
    }

    public List<Book> getTop5NewBook() {

        String hql = "SELECT b FROM Book b Order by release_date desc";

        session = getSession();

        Query<Book> query = session.createQuery(hql);

        query.setMaxResults(5);

        return query.list();
    }

    public HashMap<Book,Integer> getTop5BookRead() throws Exception {
        HashMap<Book,Integer> top5 = new HashMap<>();

        String sql = "SELECT Top 5 book.id, COUNT(book.id) AS total FROM Book INNER JOIN LoanCard ON Book.id = LoanCard.book_id GROUP BY book.id";

        session = getSession();

        Connection connection = ((SessionImpl) session).connection();

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            int id = rs.getInt("id");
            int total = rs.getInt("total");
            Book book = getBookById(id);
            top5.put(book,total);
        }

        return top5;
    }

    public int totalResultSearch(String key,String columnName){
        return super.totalResultSearch("Book",key,columnName);
    }


}