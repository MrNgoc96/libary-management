package struts2.home;


import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.book.dto.BookDTO;
import struts2.book.service.BookService;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;

import java.util.HashMap;
import java.util.List;

@Namespace(value = "/")
@InterceptorRefs({
        @InterceptorRef(value = "store",params = {"operationMode","RETRIEVE"})
})
public class HomeAction extends BaseAction {

    @Autowired
    BookService bookService;

    @Autowired
    ReaderService readerService;

    private List<BookDTO> top5NewBooks;
    private HashMap<BookDTO,Integer> top5ReadBook;
    private List<ReaderDTO> top5NewReader;
    private HashMap<ReaderDTO,Integer> top5Reader;

    @Override
    @Action(value = "", results = {@Result(name = "success", location = "/views/login.jsp")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF,UserRole.UNKNOWN})
    public String execute() throws Exception {
        return SUCCESS;
    }


    @Action(value = "home", results = {@Result(name = "success", location = "/views/index.jsp")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String displayHome() throws Exception {
        top5NewBooks = bookService.getTop5NewBook();
        top5ReadBook = bookService.getTop5BookRead();
        top5NewReader = readerService.getTop5NewReader();
        top5Reader = readerService.getTop5Reader();
        return SUCCESS;
    }

    public List<BookDTO> getTop5NewBooks() {
        return top5NewBooks;
    }

    public void setTop5NewBooks(List<BookDTO> top5NewBooks) {
        this.top5NewBooks = top5NewBooks;
    }

    public HashMap<BookDTO, Integer> getTop5ReadBook() {
        return top5ReadBook;
    }

    public void setTop5ReadBook(HashMap<BookDTO, Integer> top5ReadBook) {
        this.top5ReadBook = top5ReadBook;
    }

    public List<ReaderDTO> getTop5NewReader() {
        return top5NewReader;
    }

    public void setTop5NewReader(List<ReaderDTO> top5NewReader) {
        this.top5NewReader = top5NewReader;
    }

    public HashMap<ReaderDTO, Integer> getTop5Reader() {
        return top5Reader;
    }

    public void setTop5Reader(HashMap<ReaderDTO, Integer> top5Reader) {
        this.top5Reader = top5Reader;
    }
}
