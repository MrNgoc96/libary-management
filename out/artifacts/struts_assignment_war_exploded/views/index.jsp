<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<s:include value="common/head.jsp"/>
<body>
<div class="wrapper">
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper">
            <s:include value="common/menu.jsp"/>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="background"></div>
                    <div class="row">
                        <div class="col-lg-12 grid-margin">
                            <div class="card">
                                <h1 style="margin:10px auto;color: snow"><s:text name="global.title"/></h1>
                                <div class="card-body">

                                    <div class="row">
                                        <h4 class="col-md-6" style="text-align: center"><s:text
                                                name="global.top5newBook"/></h4>
                                        <h4 class="col-md-6" style="text-align: center"><s:text
                                                name="global.top5readBook"/></h4>
                                    </div>

                                    <div class="row">

                                        <table class="table table-dark table-hover col-md-5" style="margin-left: 5%">
                                            <thead style="background: black">
                                            <tr style="text-align: center;">
                                                <th>#</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.releaseDate"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="top5NewBooks" status="i">
                                                <tr style="text-align: center">
                                                    <td><s:property value="%{#i.count}"/></td>
                                                    <td width="50px"><img style="border-radius: 0" width="60%"
                                                                          src="<s:property value="avatar"  />"></td>
                                                    <td><s:property value="name"/></td>
                                                    <td><s:date format="dd-MM-YYYY" name="releaseDate"/></td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>

                                        </table>
                                        <div class="col-md-1"></div>
                                        <table class="table table-dark table-hover col-md-5">
                                            <thead style="background: black">
                                            <tr style="text-align: center">
                                                <th>#</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.total"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="top5ReadBook" status="i">
                                                <tr style="text-align: center">
                                                    <td><s:property value="%{#i.count}"/></td>
                                                    <td width="50px"><img style="border-radius: 0" width="60%"
                                                                          src="<s:property value="key.avatar"  />"></td>
                                                    <td><s:property value="key.name"/></td>
                                                    <td><s:property value="value"/></td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="row">
                                        <h4 class="col-md-6" style="text-align: center"><s:text
                                                name="global.top5newReader"/></h4>
                                        <h4 class="col-md-6" style="text-align: center"><s:text
                                                name="global.top5Reader"/></h4>
                                    </div>

                                    <div class="row">
                                        <table class="table table-dark table-hover col-md-5" style="margin-left: 5%">
                                            <thead style="background: black">
                                            <tr style="text-align: center">
                                                <th>#</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.createDate"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="top5NewReader" status="i">
                                                <tr style="text-align: center">
                                                    <td><s:property value="%{#i.count}"/></td>
                                                    <td width="50px"><img style="border-radius: 0" width="60%"
                                                                          src="<s:property value="avatar"  />"></td>
                                                    <td><s:property value="name"/></td>
                                                    <td><s:date format="dd-MM-yyyy" name="createDate"/></td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>
                                        </table>
                                        <div class="col-md-1"></div>
                                        <table class="table table-dark table-hover col-md-5">
                                            <thead style="background: black">
                                            <tr style="text-align: center">
                                                <th>#</th>
                                                <th><s:text name="global.image"/></th>
                                                <th><s:text name="global.name"/></th>
                                                <th><s:text name="global.total"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="top5Reader" status="i">
                                                <tr style="text-align: center">
                                                    <td><s:property value="%{#i.count}"/></td>
                                                    <td width="50px"><img style="border-radius: 0" width="60%"
                                                                          src="<s:property value="key.avatar"  />"></td>
                                                    <td><s:property value="key.name"/></td>
                                                    <td><s:property value="value"/></td>
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
</div>
</body>
</html>