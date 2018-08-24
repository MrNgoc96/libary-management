package struts2.loancard.repository;


import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import struts2.common.basic.BaseRepository;
import struts2.common.basic.SearchResult;
import struts2.common.entity.LoanCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;


@Repository
public class LoanCardRepository extends BaseRepository<LoanCard> {

    final int PAGE_SIZE = 10;


    public SearchResult<LoanCard> searchLoanCard(String key, String columnSearch, int page) {
        return search(LoanCard.class, key, columnSearch, page, PAGE_SIZE);
    }

    public SearchResult<LoanCard> searchBy(String key, String joinColumn, String columnSearch, int page) {
        return super.searchByJoinColumn(LoanCard.class, joinColumn, key, columnSearch, page, PAGE_SIZE);
    }

    public List<LoanCard> getListLoanCard(int page) {
        return getList(LoanCard.class, page, PAGE_SIZE);
    }


    public LoanCard getLoanCardById(int id) {
        return getById(LoanCard.class, id);
    }

    public boolean saveOrUpdateLoanCard(LoanCard loanCard) {
        return saveOrUpdate(loanCard);
    }


    public boolean deleteLoanCard(int id) {
        return delete(LoanCard.class, id);
    }

    public int getTotalPage() {
        return super.getTotalPage("LoanCard", PAGE_SIZE);
    }

    public int getTotalPageSearch(String key, String columnSearch) {
        return super.getTotalPageSearch("LoanCard", key, columnSearch, PAGE_SIZE);
    }


    public int totalResultSearch(String key, String columnName) {
        return super.totalResultSearch("LoanCard", key, columnName);
    }


}