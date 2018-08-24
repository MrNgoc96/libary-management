package struts2.reader.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;
import struts2.reader.dto.ReaderDTO;
import struts2.reader.service.ReaderService;


import java.util.HashMap;
import java.util.List;
/**
 * List Reader Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store",params = {"operationMode","AUTOMATIC"})})
public class ListReaderAction extends BaseAction {

    @Autowired
    ReaderService readerService;


    private Integer page;

    private Integer totalPage;

    private List<ReaderDTO> bookList;

    private HashMap<String,String> searchTypes = new HashMap<>();

    @Override
    @Action(value = "list-reader", results = {@Result(name = "success", location = "/views/reader/list-reader.jsp")})
    @UserPermission({UserRole.ADMIN,UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("==== Reader action : View list reader =======");

        searchTypes.put("name",getText("global.name"));
        searchTypes.put("createDate",getText("global.createDate"));
        searchTypes.put("gender",getText("global.gender"));
        searchTypes.put("address",getText("global.address"));

        page = page == null ? 1 : page;

        totalPage = readerService.getTotalPage();

        bookList = readerService.getListReader(page);

        return SUCCESS;
    }

    public List<ReaderDTO> getReaderList() {
        return bookList;
    }

    public void setReaderList(List<ReaderDTO> bookList) {
        this.bookList = bookList;
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
