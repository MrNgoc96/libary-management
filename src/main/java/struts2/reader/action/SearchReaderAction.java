package struts2.reader.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.SearchResult;
import struts2.common.users.UserRole;
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;

import java.util.HashMap;
import java.util.List;

/**
 * Search Reader Action
 *
 * @author longoc
 */
public class SearchReaderAction extends BaseAction {

    @Autowired
    ReaderService readerService;

    private String key;

    private Integer page;

    private Integer totalPage;

    private Integer totalResult;

    private String typeSearch;

    private HashMap<String, String> searchTypes = new HashMap<>();

    private List<ReaderDTO> readerList;

    private final boolean isSearch = true;

    @Override
    @Action(value = "search-reader", results = {@Result(name = "success", location = "/views/reader/list-reader.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        searchTypes.put("name", getText("global.name"));
        searchTypes.put("category", getText("global.category"));
        searchTypes.put("author", getText("global.author"));
        searchTypes.put("publisher", getText("global.publisher"));

        page = page == null ? 1 : page;

        SearchResult<ReaderDTO> searchResult = readerService.searchReader(key, typeSearch, page);

        readerList = searchResult.getResults();

        totalResult = searchResult.getTotalResults();

        totalPage = searchResult.getTotalPages();

        typeSearch = typeSearch.toLowerCase();
        String[] info = {totalResult + "", getText("global." + typeSearch), key};
        addActionMessage(getText("reader.searchResult", info));
        return SUCCESS;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public ReaderService getReaderService() {
        return readerService;
    }

    public void setReaderService(ReaderService bookService) {
        this.readerService = bookService;
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

    public List<ReaderDTO> getReaderList() {
        return readerList;
    }

    public void setReaderList(List<ReaderDTO> bookList) {
        this.readerList = bookList;
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
