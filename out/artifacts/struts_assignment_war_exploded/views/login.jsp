<%@ page  contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title><s:text name="global.title"/></title>
    <link rel="icon" href="resources/images/logo.jpg">
    <link rel='stylesheet prefetch'
          href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
    <link rel="stylesheet" href="resources/css/login.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</head>

<body>

<div class="language">
    <s:url action="" var="localeEN"  >
        <s:param name="request_locale">en</s:param>
    </s:url>
    <s:url action="" var="localeVN">
        <s:param name="request_locale">vi</s:param>
    </s:url>
    <span><s:text name="global.language"/></span>
    <s:a href="%{localeVN}"><img
            width="100%"
            src="resources/images/Vietnam.png"></s:a>
    <s:a href="%{localeEN}"><img width="100%"
                                 src="resources/images/US.png"></s:a>
</div>
<div class="container">
    <div class="background"></div>
    <div class="form">
        <div class="thumbnail">
            <img src="resources/images/logo.jpg"/>
        </div>
        <s:form validate="true" class="login-form" action="login" method="post">
            <h6 class="error" style="display:block;" ><s:actionmessage/></h6>
            <div class="error" id="username-error"><s:text name="error.username" /></div>
            <div class="input-field">
                <input type="text" id="username" data-length="50" name="username"/>
                <label><s:text name="global.username" /></label>
            </div>
            <div class="error" id="password-error"><s:text name="error.password" /></div>
            <div class="input-field">
                <input type="password" id="password" data-length="50" name="password"/>
                <label><s:text name="global.password" /></label>
            </div>
            <button type="submit" class="btn waves-light"><s:text name="global.login" /></button>
            </s:form>
    </div>
</div>
<div class="carousel carousel-slider" id="background">
    <a class="carousel-item" href="#one!"><img
            src="resources/images/background-1.png"></a> <a
        class="carousel-item" href="#two!"><img
        src="resources/images/background-2.png"></a> <a
        class="carousel-item" href="#three!"><img
        src="resources/images/background-3.png"></a> <a
        class="carousel-item" href="#four!"><img
        src="resources/images/background-4.png"></a>
</div>

<script src="resources/js/login.js"></script>
</body>
</html>