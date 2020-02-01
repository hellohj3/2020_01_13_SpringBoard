<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-02-01
  Time: 오후 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <form action="/doWritePost.do" method="post" onsubmit="return chkPost()">
                    <div class="mb-3">
                        <label for="title"><spring:message code="message.writePost.label.title"/></label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="<spring:message code="message.writePost.label.title.placeholder"/>" required>
                    </div>
                    <div class="mb-3">
                        <label for="content"><spring:message code="message.writePost.label.content"/></label>
                        <textarea class="ckeditor form-control" id="content" name="content" placeholder="<spring:message code="message.writePost.label.content.placeholder"/>"></textarea>
                    </div>
                    <div class="clearfix">
                        <input type="submit" class="btn btn-primary float-right" value="<spring:message code="message.home.write.btn"/>">
                    </div>
                </form>
            </div>
            <!-- Pager -->
        </div>
    </div>
</div>

<hr>

<script>
    function chkPost() {
        var title = document.getElementById("title").value;
        var content = editor.getData();

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !title || !content ) {
            alert("<spring:message code="message.writePost.empty.alert"/>");
            return false;
        } else {
            return true;
        }
    }
</script>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>