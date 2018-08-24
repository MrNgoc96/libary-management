<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<s:include value="head.jsp"/>
<style>

    .access-denny {
        height:100vh;
        background: url('https://s3.amazonaws.com/shopify-app-store/a22f8f572b2c8a8701a3cfec4deea012/screenshot/25127/original.png') center;
        background-size: cover;
        text-align: center;
    }
</style>
<body>
<div class="access-denny">
        <s:if test="#session.currentUser == null">
            <s:a cssStyle="margin-top: 10%" cssClass="btn btn-primary btn-lg" action=""><i
                    class="fas fa-home"></i> Go Back</s:a>
        </s:if>
        <s:else>
            <s:a cssStyle="margin-top: 10%" cssClass="btn btn-primary btn-lg" action="home"><i
                    class="fas fa-home"></i> Go Back</s:a>
        </s:else>
</div>
</body>

</html><html>
<s:include value="head.jsp"/>
<style>

    .access-denny {
        height:100vh;
        background: url('https://s3.amazonaws.com/shopify-app-store/a22f8f572b2c8a8701a3cfec4deea012/screenshot/25127/original.png') center;
        background-size: cover;
        text-align: center;
    }
</style>
<body>
<div class="access-denny">
    <s:if test="#session.currentUser == null">
        <s:a cssStyle="margin-top: 10%" cssClass="btn btn-primary btn-lg" action=""><i
                class="fas fa-home"></i> Go Back</s:a>
    </s:if>
    <s:else>
        <s:a cssStyle="margin-top: 10%" cssClass="btn btn-primary btn-lg" action="home"><i
                class="fas fa-home"></i> Go Back</s:a>
    </s:else>
</div>
</body>

</html>