<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="staff-form"
     style="width: 50%;margin:5% auto;background:#b6b6b6;padding: 3%;color: #0e0f15;border-radius: 5%">

    <div class="img">
        <s:if test="%{#isCreate == false}">
            <img src="<s:property value="staff.avatar" />" width="50%" align="center"
                 style="margin-left:35%;max-width: 150px;max-height: 150px"/>
        </s:if>
    </div>

    <s:form action="%{#action}" validate="true" enctype="multipart/form-data"
            method="POST" theme="bootstrap" cssClass="form-horizontal"
            label="Management staff">

        <s:if test="%{#isCreate == false}">
            <s:hidden name="staffId" />
        </s:if>

        <s:textfield key="global.username" name="staff.username" readonly="%{#isCreate == false}"/>

        <s:textfield theme="bootstrap"
                     key="global.name"
                     name="staff.name"/>

        <s:textfield
                key="global.dateOfBirth"
                name="staff.dateOfBirth" cssClass="date-picker"/>
        <s:radio list="{'Nam','Nữ'}"  name="staff.gender"  key="global.gender"   />

        <s:textfield key="global.address" name="staff.address"/>

        <s:textfield key="global.phone" name="staff.phone"/>

        <s:file size="1"
                key="global.image"
                name="image" cssClass="image"
        />

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
