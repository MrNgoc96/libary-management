package struts2.loancard.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import struts2.common.annotations.UserPermission;
import struts2.common.basic.BaseAction;
import struts2.common.users.UserRole;
import struts2.loancard.dto.LoanCardDTO;
import struts2.loancard.service.LoanCardService;

import java.io.File;
import java.time.Instant;
import java.util.Date;
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
public class CreateLoanCardAction extends BaseAction {

    @Autowired
    LoanCardService loanCardService;

    private LoanCardDTO loanCard;

    private Map<Integer, String> bookSuggestion;
    private Map<Integer, String> readerSuggestion;
    private Map<Integer, String> staffSuggestion;


    @Override
    @Action(value = "create-loan-card", results = {@Result(name = "success", params = {"actionName", "list-loan-card"}, type = "redirectAction"),
            @Result(name = "input", location = "/views/loan-card/create-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String execute() throws Exception {

        logger.info("===================Create LoanCard Action====" + loanCard.toString());

        loadListSuggestion();


        loanCard.setCreateDate(Date.from(Instant.now()));

        if (loanCardService.saveOrUpdateLoanCard(loanCard)) {
            String[] info = {getText("global.loancard"), ""};
            addActionMessage(getText("global.insertSuccess", info));

            return SUCCESS;
        } else {
            String[] info = {getText("global.loancard"),""};
            addActionMessage(getText("global.insertFail", info));

            return INPUT;
        }

    }

    @SkipValidation
    @Action(value = "create-loan-card-form", results = {@Result(name = "success", location = "/views/loan-card/create-loan-card.jsp")})
    @UserPermission({UserRole.ADMIN, UserRole.STAFF})
    public String display() {
        loadListSuggestion();
        return SUCCESS;
    }

    private void loadListSuggestion() {
        bookSuggestion = loanCardService.getMapBookSuggest();
        readerSuggestion = loanCardService.getMapReaderSuggest();
        staffSuggestion = loanCardService.getMapStaffSuggest();
    }


    public LoanCardDTO getLoanCard() {
        return loanCard;
    }

    public void setLoanCard(LoanCardDTO book) {
        this.loanCard = book;
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

}

