package struts2.staff.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.SearchResult;
import struts2.common.users.UserRole;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

import java.util.HashMap;
import java.util.List;

/**
 * Search Staff Action
 *
 * @author longoc
 */
@InterceptorRef(value = "defaultStack")
public class SearchStaffAction extends BaseAction {

    @Autowired
    StaffService staffService;

    private String key;

    private Integer page;

    private Integer totalPage;

    private Integer totalResult;

    private String typeSearch;

    private HashMap<String, String> searchTypes = new HashMap<>();

    private List<StaffDTO> staffList;

    private final boolean isSearch = true;

    @Override
    @Action(value = "search-staff", results = {@Result(name = "success", location = "/views/staff/list-staff.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        searchTypes.put("name", getText("global.name"));
        searchTypes.put("category", getText("global.category"));
        searchTypes.put("author", getText("global.author"));
        searchTypes.put("publisher", getText("global.publisher"));

        page = page == null ? 1 : page;

        SearchResult<StaffDTO> searchResult = staffService.searchStaff(key, typeSearch, page);

        staffList = searchResult.getResults();

        totalResult = searchResult.getTotalResults();

        totalPage = searchResult.getTotalPages();

        typeSearch = typeSearch.toLowerCase();

        String[] info = {totalResult + "", getText("global." + typeSearch), key};

        addActionMessage(getText("staff.searchResult", info));

        return SUCCESS;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService bookService) {
        this.staffService = bookService;
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

    public List<StaffDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffDTO> bookList) {
        this.staffList = bookList;
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
