<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2020-01-20
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Page Header -->
<header class="masthead" style="background-image: url('/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>${exception.STATUS_CODE}</h1>
                    <span class="subheading"><spring:message code="message.${exception.MESSAGE}.header.subheading"/></span>
                </div>
            </div>
        </div>
    </div>
</header>