<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2020-02-04
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include header -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Include ctnHead -->
<%@ include file="/WEB-INF/views/include/ctnHead.jsp"%>

<!-- Post Content -->
<article>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <c:out value="${post.content}" escapeXml="false"/>
                <c:if test="${user.id eq 'admin'}">
                    <div class="clearfix float-right">
                        <a class="btn btn-primary" href="javascript:sandData('modifyPost', ${post.idx})"><spring:message code="message.detailPost.btn.modify"/></a>
                        <a class="btn btn-primary" href="javascript:sandData('doDeletePost', ${post.idx})"><spring:message code="message.detailPost.btn.delete"/></a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</article>

<hr>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>