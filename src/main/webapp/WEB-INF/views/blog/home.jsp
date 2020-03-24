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

            <!-- Post pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination pg-blue justify-content-center" id="paginationArea">
                    <c:if test="${pageMaker.prev > 0}">
                        <li class="page-item"><a class="page-link" href="javascript:sandPage('/',${pageMaker.prev})"><</a></li>
                    </c:if>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="status">
                        <li class="page-item <c:out value='${pageMaker.criteria.page == status ? "active" : ""}'/>"><a class="page-link" href="javascript:sandPage('/',${status})">${status}</a></li>
                    </c:forEach>
                    <c:if test="${pageMaker.next > 0}">
                    <li class="page-item"><a class="page-link" href="javascript:sandPage('/',${pageMaker.next})">></a></li>
                    </c:if>
                </ul>
            </nav>

            <c:if test="${signIn ne 'false'}">
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