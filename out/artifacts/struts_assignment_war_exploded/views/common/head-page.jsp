<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<h1 style="margin:10px auto;color: snow"><s:text name="global.title"/></h1>
<div class="row" style="margin-left: 10px">
    <s:form namespace="/" method="GET" action="%{#actionSearch}" class="col-md-5 row" theme="simple">
        <s:select cssClass="form-control col-md-3" cssStyle="height: 30px;" list="searchTypes" name="typeSearch" />
        <s:textfield class="form-control col-md-5"  cssStyle="height: 30px" type="text" name="key" />
        <button class="btn btn-primary col-md-3" style="height: 30px;display: inline-block" type="submit"><i class="fas fa-search"></i> <s:text name="global.search"/></button>
    </s:form>
    <div class="col-md-5 row">
        <div class="col-md-2"></div>
        <s:form action="%{#actionList}" method="GET" class="col-md-4" theme="simple">
            <select name="page" id="selected-page" class="form-control">
                <s:iterator begin="1" end="totalPage" status="i">
                    <option value="<s:property/>" ${(i.index + 1) ==page? 'selected':''} ><s:text name="global.page"/> <s:property/></option>
                </s:iterator>
            </select>
        </s:form>
    </div>
    <div class="col-md-2" style="margin-bottom: 10px">
            <s:a href="%{addUrl}" class="btn btn-success"><i class="fas fa-plus-circle"></i> <s:text name="global.add"/></s:a>
    </div>
    <br>
    <s:actionmessage cssStyle="display: none"/>
</div>

