<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="book-form"
     style="width: 50%;margin:5% auto;background:#b6b6b6;padding: 3%;color: #0e0f15;border-radius: 5%">
    <s:form action="%{#action}" validate="true" enctype="multipart/form-data"
            method="POST" theme="bootstrap" cssClass="form-horizontal"
            label="Management Loan Card">

        <s:if test="%{#isCreate == false}">
            <s:hidden name="loanCardId" />
        </s:if>

        <s:select list="bookSuggestion" name="loanCard.bookId" key="global.book"/>
        <s:select list="readerSuggestion" name="loanCard.readerId" key="global.reader"/>

        <s:textfield key="global.returnDate" name="loanCard.returnDate" cssClass="date-picker"/>

        <s:select list="staffSuggestion" name="loanCard.staffId" key="global.staff"/>

        <s:if test="%{#isCreate == true}">
            <s:submit cssStyle="margin-left: 35%" cssClass="btn btn-primary"
                      value="%{getText('global.add')}"/>
        </s:if>
        <s:else>
            <s:submit cssStyle="margin-left: 35%" cssClass="btn btn-primary"
                      value="%{getText('global.update')}"/>
        </s:else>

    </s:form>
</div>
