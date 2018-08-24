package struts2.book.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import struts2.book.dto.BookDTO;
import struts2.book.repository.BookRepository;
import struts2.book.service.BookService;
import struts2.common.basic.BaseUtils;
import struts2.common.basic.SearchResult;
import struts2.common.basic.TransformUtils;
import struts2.common.entity.Book;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransformUtils<Book, BookDTO> transformUtils;


    @Transactional(readOnly = true)
    @Override
    public SearchResult<BookDTO> searchBook(String key, String columnSearch, int page) {
        SearchResult<BookDTO> searchResult = new SearchResult<>();
        key = "%" + key + "%";

        columnSearch = columnSearch.toLowerCase();

        SearchResult<Book> search = bookRepository.searchBook(key, columnSearch, page);

        List<BookDTO> bookDTOList = transformUtils.transformList(search.getResults(), BookDTO.class);

        searchResult.setResults(bookDTOList);
        searchResult.setTotalPages(search.getTotalPages());
        searchResult.setTotalResults(search.getTotalResults());

        return searchResult;

    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDTO> getListBook(int page) {

        return transformToListDTO(bookRepository.getListBook(page));
    }

    @Transactional(readOnly = true)
    @Override
    public BookDTO getBookById(int id) {
        return (BookDTO) BaseUtils.transform(bookRepository.getBookById(id), BookDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDTO> getTop5NewBook() {
        return transformToListDTO(bookRepository.getTop5NewBook());
    }

    @Transactional(readOnly = true)
    @Override
    public HashMap<BookDTO, Integer> getTop5BookRead() throws Exception {

        HashMap<Book, Integer> map = bookRepository.getTop5BookRead();

        return transformToMapDTO(map);
    }


    @Transactional
    @Override
    public boolean saveOrUpdateBook(BookDTO bookDTO) {

        Book book = (Book) BaseUtils.transform(bookDTO, Book.class);

        return bookRepository.saveOrUpdateBook(book);
    }

    @Transactional
    @Override
    public boolean deleteBook(int id) {
        return bookRepository.deleteBook(id);
    }

    @Override
    public int getTotalPage() {
        return bookRepository.getTotalPage();
    }

    @Override
    public int getTotalPageSearch(String key, String columnName) {

        key = "%" + key + "%";

        columnName = columnName.toLowerCase();

        return bookRepository.getTotalPageSearch(key, columnName);
    }

    @Override
    public int totalResultSearch(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return bookRepository.totalResultSearch(key, columnName);
    }


    private List<BookDTO> transformToListDTO(List<Book> sourceList) {
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book b : sourceList) {
            bookDTOList.add((BookDTO) BaseUtils.transform(b, BookDTO.class));
        }

        return bookDTOList;
    }

    private HashMap<BookDTO, Integer> transformToMapDTO(HashMap<Book, Integer> map) {

        HashMap<BookDTO, Integer> top5 = new HashMap<>();

        for (Book b : map.keySet()) {
            BookDTO bookDTO = (BookDTO) BaseUtils.transform(b, BookDTO.class);
            top5.put(bookDTO, map.get(b));

        }
        return BaseUtils.sortDescByValues(top5);
    }


}
