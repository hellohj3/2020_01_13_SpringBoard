<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-02-01
  Time: 오후 2:32
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
                <form action="${(pageNm eq 'writePost') ? '/doWritePost.do' : '/doModifyPost.do'}" method="post" onsubmit="return chkPost()" enctype="multipart/form-data">
                    <c:if test="${pageNm eq 'modifyPost'}">
                        <input type="hidden" name="idx" value="${post.idx}">
                    </c:if>
                    <div class="mb-3">
                        <label class="col-form-label" for="title"><spring:message code="message.writePost.label.title"/></label>
                        <input type="text" class="form-control" id="title" name="title" value="${(post.title eq '') ? '' : post.title}" placeholder="<spring:message code="message.writePost.label.placeholder.title"/>" required>
                    </div>
                    <div class="mb-3">
                        <label class="col-form-label" for="subTitle"><spring:message code="message.writePost.label.subTitle"/></label>
                        <textarea class="form-control" id="subTitle" name="subTitle" rows="3" placeholder="<spring:message code="message.writePost.label.placeholder.subTitle"/>" required>${(post.subTitle eq '') ? '' : post.subTitle}</textarea>
                    </div>
                    <div class="mb-3">
                        <label class="col-form-label" for="content"><spring:message code="message.writePost.label.content"/></label>
                        <textarea class="ckeditor form-control" id="content" name="content" placeholder="<spring:message code="message.writePost.label.placeholder.content"/>">${(post.content eq '') ? '' : post.content}</textarea>
                    </div>
                    <div class="clearfix float-right">
                        <c:choose>
                            <c:when test="${pageNm eq 'writePost'}">
                                <input type="submit" class="btn btn-primary" value="<spring:message code="message.writePost.btn.write"/>">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" class="btn btn-primary" value="<spring:message code="message.modifyPost.btn.modify"/>">
                            </c:otherwise>
                        </c:choose>
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