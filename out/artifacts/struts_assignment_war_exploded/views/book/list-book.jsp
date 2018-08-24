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
                                <s:set var="actionSearch" value="'search-book'"/>
                                <s:set var="actionList" value="'list-book'"/>
                                <s:url var="addUrl" value="/create-book-form"></s:url>
                                <s:include value="../common/head-page.jsp" />
                                <div class="card-body" style="z-index: 10;">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead class="thead-light">
                                            <tr>
                                                <th>Id</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.category"/></th>
                                                <th><s:text name="global.author"/></th>
                                                <th><s:text name="global.publisher"/></th>
                                                <th><s:text name="global.releaseDate"/></th>
                                                <th><s:text name="global.function"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="bookList">
                                                <tr>
                                                    <td class="font-weight-medium"><s:property value="id"/></td>
                                                    <td width="150px"><img style="border-radius: 0%"
                                                                           src="<s:property value="avatar" />"
                                                                           width="60%"></td>
                                                    <td><s:property value="name"/></td>
                                                    <td><s:property value="category"/></td>
                                                    <td><s:property value="author"/></td>
                                                    <td><s:property value="publisher"/></td>
                                                    <td><s:date name="releaseDate" format="dd-MM-yyyy" /></td>
                                                    <td>
                                                        <s:url namespace="/" var="updateBook" action="update-book-form">
                                                            <s:param name="bookId" value="id" />
                                                        </s:url>
                                                        <s:url namespace="/" var="removeBook" action="remove-book">
                                                            <s:param name="bookId" value="id" />
                                                        </s:url>
                                                        <s:a href="%{updateBook}" class="btn btn-primary"><i class="fas fa-edit"></i>  <s:text name="global.edit"/></s:a>
                                                        <button onclick="remove('remove-book?bookId=${id}','${name}')" class="btn btn-danger"><i class="fas fa-trash-alt"></i> <s:text name="global.delete"/></button>
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
<s:include value="../common/footer.jsp" />
</html>