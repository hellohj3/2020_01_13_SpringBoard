<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-01-20
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Page Header -->
<header class="masthead" style="background-image: url('/img/${bgNm}-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <c:if test="${pageNm eq 'exception'}">
                    <h1>${exception.STATUS_CODE}</h1>
                    <span class="subheading"><spring:message code="message.${exception.MESSAGE}.header.subheading"/></span>
                    </c:if>
                    <c:if test="${pageNm ne 'exception'}">
                    <h1><spring:message code="message.${pageNm}.header.heading"/></h1>
                    <span class="subheading"><spring:message code="message.${pageNm}.header.subheading"/></span>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</header>