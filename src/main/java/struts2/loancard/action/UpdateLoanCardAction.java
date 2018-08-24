package struts2.loancard.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.entity.LoanCard;
import struts2.common.users.UserRole;
import struts2.loancard.dto.LoanCardDTO;
import struts2.loancard.service.LoanCardService;

import java.io.File;
import java.util.Map;

/**
 * Create LoanCard Action
 *
 * @author longoc
 */

@InterceptorRefs({
        @InterceptorRef(value = "fileUpload", params = {"allowedTypes", "image/jpeg,image/gif,image/png"}),
        @InterceptorRef(value = "store", params = {"operationMode", "STORE"})
})
public class UpdateLoanCardAction extends BaseAction {

    @Autowired
    LoanCardService loanCardService;

    private LoanCardDTO loanCard;

    private int loanCardId;

    private Map<Integer, String> bookSuggestion;
    private Map<Integer, String> readerSuggestion;
    private Map<Integer, String> staffSuggestion;


    @Override
    @Action(value = "update-loan-card", results = {@Result(name = "success", params = {"actionName", "list-loan-card"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/loan-card/edit-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        logger.info("===================Create LoanCard Action====" + loanCard.toString());

        loadListSuggestion();
        String[] info = {getText("global.loancard"), ""};

        LoanCard lc = loanCardService.getLoanCardById(loanCardId);

        if (lc != null) {

            loanCard.setCreateDate(lc.getCreateDate());

            if (loanCardService.updateLoanCard(loanCard, loanCardId)) {

                addActionMessage(getText("global.updateSuccess", info));

                return SUCCESS;
            } else {

                addActionMessage(getText("global.updateSuccess", info));

                return INPUT;
            }
        } else {
            addActionMessage(getText("global.updateSuccess", info));

            return INPUT;
        }


    }


    @SkipValidation
    @Action(value = "update-loan-card-form", results = {@Result(name = "success", location = "/views/loan-card/edit-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        loadListSuggestion();
        LoanCard lc = loanCardService.getLoanCardById(loanCardId);
        loanCard = new LoanCardDTO();
        loanCard.setId(lc.getId());
        loanCard.setReaderId(lc.getReader().getId());
        loanCard.setStaffId(lc.getStaff().getId());
        loanCard.setBookId(lc.getBook().getId());
        loanCard.setCreateDate(lc.getCreateDate());
        loanCard.setReturnDate(lc.getReturnDate());
        return SUCCESS;
    }

    private void loadListSuggestion() {
        bookSuggestion = loanCardService.getMapBookSuggest();
        readerSuggestion = loanCardService.getMapReaderSuggest();
        staffSuggestion = loanCardService.getMapStaffSuggest();
    }

    public Map<Integer, String> getBookSuggestion() {
        return bookSuggestion;
    }

    public Map<Integer, String> getReaderSuggestion() {
        return readerSuggestion;
    }

    public Map<Integer, String> getStaffSuggestion() {
        return staffSuggestion;
    }

    public LoanCardDTO getLoanCard() {
        return loanCard;
    }

    public void setLoanCard(LoanCardDTO loanCard) {
        this.loanCard = loanCard;
    }

    public int getLoanCardId() {
        return loanCardId;
    }

    public void setLoanCardId(int loanCardId) {
        this.loanCardId = loanCardId;
    }

}

