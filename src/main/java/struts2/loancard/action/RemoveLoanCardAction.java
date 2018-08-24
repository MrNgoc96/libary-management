package struts2.loancard.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.basic.BaseUtils;
import struts2.common.entity.LoanCard;
import struts2.common.users.UserRole;
import struts2.loancard.dto.LoanCardDTO;
import struts2.loancard.service.LoanCardService;

/**
 * Remove LoanCard Action
 *
 * @author longoc
 */

@InterceptorRefs({@InterceptorRef(value = "defaultStack"),
        @InterceptorRef(value = "store", params = {"operationMode", "AUTOMATIC"})})
public class RemoveLoanCardAction extends BaseAction {

    @Autowired
    LoanCardService loanCardService;

    private int loanCardId;

    @Override
    @Action(value = "remove-loan-card", results = {@Result(name = "success", params = {"actionName", "list-loan-card"}, type = "redirectAction")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        LoanCard loanCard = loanCardService.getLoanCardById(loanCardId);

        String[] info = {getText("global.book"), loanCard.getId() + ""};

        if (loanCardService.deleteLoanCard(loanCardId)) {

            addActionMessage(getText("global.removeSuccess", info));

        } else {
            addActionMessage(getText("global.removeFail", info));
        }

        return SUCCESS;
    }


    public int getLoanCardId() {
        return loanCardId;
    }

    public void setLoanCardId(int loanCardId) {
        this.loanCardId = loanCardId;
    }
}
