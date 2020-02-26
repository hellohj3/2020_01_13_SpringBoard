<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-01-20
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .cus-sub { font-style: italic; }
</style>

<!-- Page Header -->
<header class="masthead" style="background-image: url('/img/bg/${bgNm}-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <c:choose>
                    <c:when test="${pageNm eq 'exception'}">
                        <div class="site-heading">
                            <h1>${exception.STATUS_CODE}</h1>
                            <span class="subheading"><spring:message code="message.${exception.MESSAGE}.header.subheading"/></span>
                        </div>
                    </c:when>
                    <c:when test="${pageNm eq 'detailPost'}">
                        <div class="post-heading">
                            <h1>${post.title}</h1>
                            <h2 class="subheading">
                                <c:out value="${fn:substring(post.subTitle,0,65)}" escapeXml="false"/>
                                <c:if test="${fn:length(post.subTitle) > 65}">
                                    ....
                                </c:if>
                            </h2>
                            <span class="cus-sub">Posted by ${post.writer} on <fmt:formatDate value="${post.regDate}" pattern="yyyy.MM.dd"/></span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="site-heading">
                            <h1><spring:message code="message.${pageNm}.header.heading"/></h1>
                            <span class="subheading"><spring:message code="message.${pageNm}.header.subheading"/></span>
                        </div>
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
        </div>
    </div>
</header>