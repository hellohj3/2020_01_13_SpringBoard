<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-01-07
  Time: 오후 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include header -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Include ctnHead -->
<%@ include file="/WEB-INF/views/include/ctnHead.jsp"%>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <c:choose>
                <c:when test="${empty postList}">
                    <div class="post-preview">
                        <h3 class="post-subtitle">
                            <spring:message code="message.home.msg.post.empty"/>
                        </h3>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="post" items="${postList}" varStatus="status">
                        <div class="post-preview">
                            <a href="javascript:sandData('detailePost', ${post.idx})">
                                <h2 class="post-title">
                                    ${post.title}
                                </h2>
                                <h3 class="post-subtitle">
                                    <c:out value="${fn:substring(post.subTitle,0,65)}" escapeXml="false"/>
                                    <c:if test="${fn:length(post.subTitle) > 65}">
                                    ....
                                    </c:if>
                                </h3>
                            </a>
                            <p class="post-meta">Posted by ${post.writer} on <fmt:formatDate value="${post.regDate}" pattern="yyyy.MM.dd"/></p>
                        </div>
                        <hr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <c:if test="${signIn eq 'true'}">
                <div class="clearfix float-right">
                    <a class="btn btn-primary" href="/writePost.do"><spring:message code="message.home.btn.write"/></a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<hr>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>