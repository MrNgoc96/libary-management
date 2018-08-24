<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<s:include value="../common/head.jsp"/>
<body>
<div class="wrapper">
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper">
            <s:include value="../common/menu.jsp"/>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="background"></div>
                    <div class="row">
                        <div class="col-lg-12 grid-margin">
                            <div class="card">
                                <h1 style="margin:10px auto;color: snow"><s:text name="global.title"/></h1>
                                <div class="card-body">
                                    <s:set var="isCreate" value="false"/>
                                    <s:set var="action" value="'update-book'"/>
                                    <s:include value="book-form.jsp" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<s:include value="../common/footer.jsp" />
</html>