<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2020-02-15
  Time: 오후 5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Include header -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Include pageHead -->
<%@ include file="/WEB-INF/views/include/ctnHead.jsp"%>

<!-- Main Content -->
<div class="container">
    <div class="col-lg-8 col-md-10 mx-auto">
        <c:out value="${info.content}" escapeXml="false"/>
    </div>
</div>
<hr>

<script>
    $(".skillImage").each(function (idx) {
        $(this).mouseover(function () {
            $(".viewThis").css("display", "block");
        });
        $(this).mouseout(function () {
            $(".viewThis").css("display", "none");
        });
    })
</script>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>