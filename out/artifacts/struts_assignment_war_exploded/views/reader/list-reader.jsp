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
                                <s:set var="actionSearch" value="'search-reader'"/>
                                <s:set var="actionList" value="'list-reader'"/>
                                <s:url var="addUrl" value="/create-reader-form"></s:url>
                                <s:include value="../common/head-page.jsp"/>
                                <div class="card-body" style="z-index: 10;">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead class="thead-light">
                                            <tr>
                                                <th>Id</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.dateOfBirth"/></th>
                                                <th><s:text name="global.gender"/></th>
                                                <th><s:text name="global.address"/></th>
                                                <th><s:text name="global.createDate"/></th>
                                                <th><s:text name="global.function"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="readerList">
                                                <tr>
                                                    <td class="font-weight-medium"><s:property value="id"/></td>
                                                    <td width="150px"><img style="border-radius: 0%"
                                                                           src="<s:property value="avatar" />"
                                                                           width="60%"></td>
                                                    <td><s:property value="name"/></td>
                                                    <td><s:date name="dateOfBirth" format="dd-MM-yyyy"/></td>
                                                    <td><s:property value="gender"/></td>
                                                    <td><s:property value="address"/></td>
                                                    <td><s:date name="createDate" format="dd-MM-yyyy"/></td>
                                                    <td>
                                                        <s:url namespace="/" var="updateReader" action="update-reader-form">
                                                            <s:param name="readerId" value="id"/>
                                                        </s:url>

                                                        <s:a href="%{updateReader}" class="btn btn-primary"><i
                                                                class="fas fa-edit"></i> <s:text
                                                                name="global.edit"/></s:a>
                                                        <button onclick="remove('remove-reader?readerId=${id}','${name}')" class="btn btn-danger"><i
                                                                class="fas fa-trash-alt"></i> <s:text
                                                                name="global.delete"/></button>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>
                                        </table>
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
<s:include value="../common/footer.jsp"/>
</html>