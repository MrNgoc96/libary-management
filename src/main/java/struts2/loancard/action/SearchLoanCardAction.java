package struts2.loancard.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.SearchResult;
import struts2.common.entity.LoanCard;
import struts2.common.users.UserRole;
import struts2.loancard.service.LoanCardService;

import java.util.HashMap;
import java.util.List;

/**
 * Search LoanCard Action
 *
 * @author longoc
 */
public class SearchLoanCardAction extends BaseAction {

    @Autowired
    LoanCardService loanCardService;

    private String key;

    private Integer page;

    private Integer totalPage;

    private Integer totalResult;

    private String typeSearch;

    private HashMap<String, String> searchTypes = new HashMap<>();

    private List<LoanCard> loanCardList;

    private final boolean isSearch = true;

    @Override
    @Action(value = "search-loan-card", results = {@Result(name = "success", location = "/views/loan-card/list-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        searchTypes.put("reader", getText("search.readerName"));
        searchTypes.put("book", getText("search.bookName"));
        searchTypes.put("staff", getText("search.staffName"));

        page = page == null ? 1 : page;

        SearchResult searchResult = loanCardService.searchLoanCardBy(key, typeSearch, "name", page);

        loanCardList = searchResult.getResults();

        totalResult = searchResult.getTotalResults();

        totalPage = searchResult.getTotalPages();

        typeSearch = typeSearch.toLowerCase();

        String[] info = {totalResult + "", getText("search."+ typeSearch+"Name"), key};

        addActionMessage(getText("loancard.searchResult", info));

        return SUCCESS;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public LoanCardService getLoanCardService() {
        return loanCardService;
    }

    public void setLoanCardService(LoanCardService bookService) {
        this.loanCardService = bookService;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public List<LoanCard> getLoanCardList() {
        return loanCardList;
    }

    public void setLoanCardList(List<LoanCard> loanCardList) {
        this.loanCardList = loanCardList;
    }

    public String getTypeSearch() {
        return typeSearch;
    }

    public HashMap<String, String> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(HashMap<String, String> searchTypes) {
        this.searchTypes = searchTypes;
    }

    public void setTypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    public boolean isSearch() {
        return isSearch;
    }
}
