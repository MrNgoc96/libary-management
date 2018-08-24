package struts2.book.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.book.dto.BookDTO;
import struts2.book.service.BookService;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;


import java.util.HashMap;
import java.util.List;

/**
 * List Book Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store",params = {"operationMode","AUTOMATIC"})})
public class ListBookAction extends BaseAction {

    @Autowired
    BookService bookService;


    private Integer page;

    private Integer totalPage;

    private List<BookDTO> bookList;

    private HashMap<String,String> searchTypes = new HashMap<>();

    @Override
    @Action(value = "list-book", results = {@Result(name = "success", location = "/views/book/list-book.jsp")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===Book action : View list book =======");

        searchTypes.put("name",getText("global.name"));
        searchTypes.put("category",getText("global.category"));
        searchTypes.put("author",getText("global.author"));
        searchTypes.put("publisher",getText("global.publisher"));

        page = page == null ? 1 : page;

        totalPage = bookService.getTotalPage();

        bookList = bookService.getListBook(page);

        return SUCCESS;
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public HashMap<String, String> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(HashMap<String, String> searchTypes) {
        this.searchTypes = searchTypes;
    }
}
