package struts2.staff.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;
import struts2.staff.dto.StaffDTO;
import struts2.staff.service.StaffService;

import java.util.HashMap;
import java.util.List;

/**
 * List Staff Action
 *
 * @author longoc
 */

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store",params = {"operationMode","AUTOMATIC"})})
public class ListStaffAction extends BaseAction {

    @Autowired
    StaffService staffService;

    private Integer page;

    private Integer totalPage;

    private List<StaffDTO> staffList;

    private HashMap<String,String> searchTypes = new HashMap<>();

    @Override
    @Action(value = "list-staff", results = {@Result(name = "success", location = "/views/staff/list-staff.jsp")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String execute() throws Exception {

        logger.info("==========Staff action : View list staff =============");

        searchTypes.put("name",getText("global.name"));
        searchTypes.put("gender",getText("global.gender"));
        searchTypes.put("address",getText("global.address"));


        page = page == null ? 1 : page;

        totalPage = staffService.getTotalPage();

        staffList = staffService.getListStaff(page);

        return SUCCESS;
    }

    public List<StaffDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffDTO> bookList) {
        this.staffList = bookList;
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
