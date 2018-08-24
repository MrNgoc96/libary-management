<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item menu-left" style="background:#c9c8c8;">
            <div class="language"
                 style="grid-template-columns: 1fr 1fr;max-width: 100px;max-height:40px;margin-top: 10%;margin-left: 30%">
                <s:url var="localeEN" action="home">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:url var="localeVN" action="home">
                    <s:param name="request_locale">vi</s:param>
                </s:url>
                <%--<span><s:text name="global.language"/></span>--%>
                <s:a href="%{localeVN}"><img
                        width="100%"
                        src="resources/images/Vietnam.png"></s:a>
                <s:a href="%{localeEN}"><img width="100%"
                                             src="resources/images/US.png"></s:a>
            </div>
            <s:if test="#session.currentStaff != null">
                <div class="img-avata" style="width: 80%;margin:auto">
                    <img src="<s:property value="#session.currentStaff.avatar"/>" alt="" width="80%" class="img__avata">
                </div>
                <div class="info-user" style="text-align: center">
                    <h4><s:text name="global.staff"/></h4>
                    <h4><s:property value="#session.currentStaff.name"/></h4>
                </div>
            </s:if>
            <s:else>
                <div class="img-avata">
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOzScP-2J-RB0ucXWc7xcTxinaHZttGXqGkvlapamgXkCsLJymsw" width="70%"
                          class="img__avata">
                </div>
                <div class="info-user" style="text-align: center">
                    <h4 style="color:#521d1d;font-weight: bold"><s:property
                            value="#session.currentUser.username"/></h4>
                    <h4><s:text name="global.admin"/></h4>
                </div>
            </s:else>
            <s:a cssStyle="width: 50%;margin-left: 25%" action="logout" class="btn btn-danger"><i
                    class="fas fa-sign-out-alt"></i> <s:text name="global.logout"/></s:a>
            <div class="nav-items" style="margin-top: 10%">
                <s:a cssStyle="border: 1px solid black" class="nav-link" action="home"><span class="menu-title"><i
                        class="fas fa-home fa-2x"></i>          <s:text name="global.home"/></span></s:a>
                <s:a cssStyle="border: 1px solid black" class="nav-link" action="list-book"> <span class="menu-title"><i
                        class="fas fa-book fa-2x"></i>          <s:text name="global.book"/></span></s:a>
                <s:a cssStyle="border: 1px solid black" class="nav-link" action="list-reader"> <span class="menu-title"><i
                        class="fas fa-user fa-2x"></i>         <s:text name="global.reader"/></span></s:a>
                <s:a cssStyle="border: 1px solid black" class="nav-link" action="list-loan-card"> <span
                        class="menu-title"><i class="fas fa-id-card fa-2x"></i>   <s:text
                        name="global.loancard"/></span></s:a>
                <s:a cssStyle="border: 1px solid black" class="nav-link" action="list-staff"> <span
                        class="menu-title"><i class="fas fa-user-tie fa-2x"></i>   <s:text
                        name="global.staff"/></span></s:a>
            </div>
            <div class="background" style="z-index: -10;background: white"></div>
        </li>
    </ul>
    <input type="hidden" id="isVn" value="${requestScope.locale.language == 'vi'}">
    <input type="hidden" id="query-string" value="${pageContext.request.queryString}">
</nav>