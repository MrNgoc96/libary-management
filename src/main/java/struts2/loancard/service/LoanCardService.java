package struts2.loancard.service;

import struts2.common.basic.BaseService;
import struts2.common.basic.SearchResult;
import struts2.common.entity.LoanCard;
import struts2.loancard.dto.LoanCardDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface LoanCardService extends BaseService {


    SearchResult<LoanCard> searchLoanCard(String key, String columnSearch, int page);

    SearchResult<LoanCard> searchLoanCardBy(String key,String joinColumn, String columnSearch, int page);

    List<LoanCard> getListLoanCard(int page);

    LoanCard getLoanCardById(int id);

    boolean saveOrUpdateLoanCard(LoanCardDTO loanCard);

    public boolean updateLoanCard(LoanCardDTO loanCardDTO,int id);

    boolean deleteLoanCard(int id);

    int getTotalPage();

    int getTotalPageSearch(String key, String columnName);

    int totalResultSearch(String key, String columnName);

    Map<Integer,String> getMapBookSuggest();

    Map<Integer,String> getMapReaderSuggest();

    Map<Integer,String> getMapStaffSuggest();




}
