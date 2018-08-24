package struts2.loancard.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import struts2.book.repository.BookRepository;
import struts2.common.basic.BaseUtils;
import struts2.common.basic.SearchResult;
import struts2.common.entity.Book;
import struts2.common.entity.LoanCard;
import struts2.common.entity.Reader;
import struts2.common.entity.Staff;
import struts2.loancard.dto.LoanCardDTO;
import struts2.loancard.repository.LoanCardRepository;
import struts2.loancard.service.LoanCardService;
import struts2.reader.repository.ReaderRepository;
import struts2.staff.repository.StaffRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanCardServiceImpl implements LoanCardService {

    @Autowired
    LoanCardRepository loanCardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ReaderRepository readerRepository;


    @Transactional(readOnly = true)
    @Override
    public SearchResult<LoanCard> searchLoanCard(String key, String columnSearch, int page) {

        key = "%" + key + "%";

        columnSearch = columnSearch.toLowerCase();

        return loanCardRepository.searchLoanCard(key, columnSearch, page);

    }

    @Override
    public SearchResult<LoanCard> searchLoanCardBy(String key, String joinColumn, String columnSearch, int page) {

        key = "%" + key + "%";
        columnSearch = columnSearch.toLowerCase();

        return loanCardRepository.searchBy(key, joinColumn, columnSearch, page);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoanCard> getListLoanCard(int page) {
        return loanCardRepository.getListLoanCard(page);
    }

    @Transactional(readOnly = true)
    @Override
    public LoanCard getLoanCardById(int id) {
        return loanCardRepository.getLoanCardById(id);
    }


    @Transactional
    @Override
    public boolean saveOrUpdateLoanCard(LoanCardDTO loanCardDTO) {

        Staff staff = staffRepository.getStaffById(loanCardDTO.getStaffId());
        Reader reader = readerRepository.getReaderById(loanCardDTO.getReaderId());
        Book book = bookRepository.getBookById(loanCardDTO.getBookId());

        LoanCard loanCard = new LoanCard(staff, reader, book, loanCardDTO.getCreateDate(), loanCardDTO.getReturnDate());
        return loanCardRepository.saveOrUpdateLoanCard(loanCard);

    }

    @Transactional
    @Override
    public boolean updateLoanCard(LoanCardDTO loanCardDTO,int id) {

        Staff staff = staffRepository.getStaffById(loanCardDTO.getStaffId());
        Reader reader = readerRepository.getReaderById(loanCardDTO.getReaderId());
        Book book = bookRepository.getBookById(loanCardDTO.getBookId());

        LoanCard loanCard = new LoanCard(staff, reader, book, loanCardDTO.getCreateDate(), loanCardDTO.getReturnDate());
        loanCard.setId(id);
        return loanCardRepository.saveOrUpdateLoanCard(loanCard);

    }

    @Transactional
    @Override
    public boolean deleteLoanCard(int id) {
        return loanCardRepository.deleteLoanCard(id);
    }

    @Override
    public int getTotalPage() {
        return loanCardRepository.getTotalPage();
    }

    @Override
    public int getTotalPageSearch(String key, String columnName) {

        key = "%" + key + "%";

        columnName = columnName.toLowerCase();

        return loanCardRepository.getTotalPageSearch(key, columnName);
    }

    @Override
    public int totalResultSearch(String key, String columnName) {
        key = "%" + key + "%";
        columnName = columnName.toLowerCase();
        return loanCardRepository.totalResultSearch(key, columnName);
    }

    @Override
    public Map<Integer, String> getMapBookSuggest() {
        List<Book> bookList = bookRepository.getAllBooks();
        Map<Integer, String> map = new HashMap<>();
        for (Book book : bookList) {
            map.put(book.getId(), book.getName());
        }
        return map;
    }

    @Override
    public Map<Integer, String> getMapStaffSuggest() {
        List<Staff> staffList = staffRepository.getAllStaffs();
        Map<Integer, String> map = new HashMap<>();
        for (Staff staff : staffList) {
            map.put(staff.getId(), staff.getName());
        }
        return map;
    }

    @Override
    public Map<Integer, String> getMapReaderSuggest() {
        List<Reader> readerList = readerRepository.getAllReaders();
        Map<Integer, String> map = new HashMap<>();
        for (Reader reader : readerList) {
            map.put(reader.getId(), reader.getName());
        }
        return map;
    }

    private List<LoanCardDTO> transformToListDTO(List<LoanCard> sourceList) {
        List<LoanCardDTO> bookDTOList = new ArrayList<>();

        for (LoanCard b : sourceList) {
            bookDTOList.add((LoanCardDTO) BaseUtils.transform(b, LoanCardDTO.class));
        }

        return bookDTOList;
    }

    private LoanCardDTO transformToDTO(LoanCard loanCard) {
        LoanCardDTO loanCardDTO = new LoanCardDTO();
        loanCardDTO.setId(loanCard.getId());
        loanCardDTO.setBookId(loanCard.getBook().getId());
        loanCardDTO.setReaderId(loanCard.getReader().getId());
        loanCardDTO.setStaffId(loanCard.getStaff().getId());
        loanCardDTO.setCreateDate(loanCard.getCreateDate());
        loanCardDTO.setReturnDate(loanCard.getReturnDate());
        return loanCardDTO;
    }

    private HashMap<LoanCardDTO, Integer> transformToMapDTO(HashMap<LoanCard, Integer> map) {

        HashMap<LoanCardDTO, Integer> top5 = new HashMap<>();

        for (LoanCard b : map.keySet()) {
            LoanCardDTO bookDTO = (LoanCardDTO) BaseUtils.transform(b, LoanCardDTO.class);
            top5.put(bookDTO, map.get(b));

        }
        return BaseUtils.sortDescByValues(top5);
    }


}
