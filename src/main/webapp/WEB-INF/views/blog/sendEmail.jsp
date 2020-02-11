<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2020-02-08
  Time: 오후 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include header -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Include ctnHead -->
<%@ include file="/WEB-INF/views/include/ctnHead.jsp"%>

<style>
    form {
        font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
    }
</style>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <div class="post-preview">
                <form action="/doSendEmail.do" method="post" onsubmit="return chkEmail()">
                    <div class="mb-3">
                        <label class="col-form-label" for="emailFrom"><spring:message code="message.sendEmail.label.from"/></label>
                        <input type="text" class="form-control" id="emailFrom" name="emailFrom" value="" placeholder="<spring:message code="message.sendEmail.label.placeholder.from"/>" required>
                    </div>
                    <div class="mb-3">
                        <label class="col-form-label" for="emailTitle"><spring:message code="message.sendEmail.label.title"/></label>
                        <input type="text" class="form-control" id="emailTitle" name="emailTitle" value="" placeholder="<spring:message code="message.sendEmail.label.placeholder.title"/>" required>
                    </div>
                    <div class="mb-3">
                        <label class="col-form-label" for="emailContent"><spring:message code="message.sendEmail.label.content"/></label>
                        <textarea class="form-control" id="emailContent" name="emailContent" rows="5" placeholder="<spring:message code="message.sendEmail.label.placeholder.content"/>" required></textarea>
                    </div>
                    <div class="clearfix float-right">
                        <input type="submit" class="btn btn-primary" value="<spring:message code="message.sendEmail.btn.send"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<hr>

<script>

</script>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>