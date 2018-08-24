package struts2.book.service;

import struts2.book.dto.BookDTO;
import struts2.common.basic.BaseService;
import struts2.common.basic.SearchResult;

import java.io.File;
import java.util.HashMap;
import java.util.List;


public interface BookService extends BaseService {


    SearchResult<BookDTO> searchBook(String key, String columnSearch, int page);

    List<BookDTO> getListBook(int page);

    BookDTO getBookById(int id);

    List<BookDTO> getTop5NewBook();

    HashMap<BookDTO, Integer> getTop5BookRead() throws  Exception;

    boolean saveOrUpdateBook(BookDTO book);

    boolean deleteBook(int id);

    int getTotalPage();

    int getTotalPageSearch(String key, String columnName);

    int totalResultSearch(String key,String columnName);




}
