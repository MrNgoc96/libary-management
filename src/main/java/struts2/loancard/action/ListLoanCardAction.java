package struts2.loancard.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.entity.LoanCard;
import struts2.common.users.UserRole;
import struts2.loancard.dto.LoanCardDTO;
import struts2.loancard.service.LoanCardService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List LoanCard Action
 *
 * @author longoc
 */
@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class ListLoanCardAction extends BaseAction {

    @Autowired
    LoanCardService loanCardService;

    private Integer page;

    private Integer totalPage;

    private List<LoanCard> loanCardList;

    private HashMap<String,String> searchTypes = new HashMap<>();


    @Override
    @Action(value = "list-loan-card", results = {@Result(name = "success", location = "/views/loan-card/list-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {
        logger.info("===LoanCard action : View list loan card =======");

        searchTypes.put("reader", getText("search.readerName"));
        searchTypes.put("book", getText("search.bookName"));
        searchTypes.put("staff", getText("search.staffName"));

        page = page == null ? 1 : page;

        totalPage = loanCardService.getTotalPage();

        loanCardList = loanCardService.getListLoanCard(page);

        return SUCCESS;
    }

    public HashMap<String, String> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(HashMap<String, String> searchTypes) {
        this.searchTypes = searchTypes;
    }

    public List<LoanCard> getLoanCardList() {
        return loanCardList;
    }

    public void setLoanCardList(List<LoanCard> loanCardList) {
        this.loanCardList = loanCardList;
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


}
