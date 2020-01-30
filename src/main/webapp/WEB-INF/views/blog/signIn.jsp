<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 2020-01-27
  Time: 오후 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Include header -->
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Include ctnHead -->
<%@ include file="/WEB-INF/views/include/ctnHead.jsp"%>

<style>
    .card-container.card {
        max-width: 350px;
        padding: 40px 40px;
    }

    .btn {
        font-weight: 700;
        height: 36px;
        -moz-user-select: none;
        -webkit-user-select: none;
        user-select: none;
        cursor: default;
    }

    /*
     * Card component
     */
    .card {
        background-color: #F7F7F7;
        /* just in case there no content*/
        padding: 20px 25px 30px;
        margin: 0 auto 25px;
        margin-top: 50px;
        /* shadows and rounded borders */
        -moz-border-radius: 2px;
        -webkit-border-radius: 2px;
        border-radius: 2px;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    }

    .profile-img-card {
        width: 96px;
        height: 96px;
        margin: 0 auto 10px;
        display: block;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
    }

    /*
     * Form styles
     */
    .profile-name-card {
        font-size: 16px;
        font-weight: bold;
        text-align: center;
        margin: 10px 0 0;
        min-height: 1em;
    }

    .reauth-email {
        display: block;
        color: #404040;
        line-height: 2;
        margin-bottom: 10px;
        font-size: 14px;
        text-align: center;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    .form-signin #id,
    .form-signin #password {
        direction: ltr;
        height: 44px;
        font-size: 16px;
        font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;
    }

    .form-signin input[type=email],
    .form-signin input[type=password],
    .form-signin input[type=text],
    .form-signin button {
        width: 100%;
        display: block;
        margin-bottom: 10px;
        z-index: 1;
        position: relative;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }

    .form-signin .form-control:focus {
        border-color: rgb(104, 145, 162);
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
    }

    .btn.btn-signin {
        /*background-color: #4d90fe; */
        background-color: rgb(104, 145, 162);
        /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
        padding: 0px;
        font-weight: 700;
        font-size: 14px;
        height: 36px;
        -moz-border-radius: 3px;
        -webkit-border-radius: 3px;
        border-radius: 3px;
        border: none;
        -o-transition: all 0.218s;
        -moz-transition: all 0.218s;
        -webkit-transition: all 0.218s;
        transition: all 0.218s;
    }

    .btn.btn-signin:hover,
    .btn.btn-signin:active,
    .btn.btn-signin:focus {
        background-color: rgb(12, 97, 33);
    }

    .forgot-password {
        color: rgb(104, 145, 162);
    }

    .forgot-password:hover,
    .forgot-password:active,
    .forgot-password:focus{
        color: rgb(12, 97, 33);
    }
</style>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <%--<div class="col-lg-8 col-md-10 mx-auto">
            <!-- User data -->
            <form action="/doSignIn.do" method="post" onsubmit="return chkUser()">
                <input type="text" class="" id="id" name="id" value=""/>
                <input type="password" class="" id="password" name="password" value=""/>
                <input type="submit" value="<spring:message code="message.signIn.btn"/>"/>
            </form>
        </div>--%>
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="/doSignIn.do" method="post" onsubmit="return chkUser()">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="text" name="id" id="id" class="form-control" placeholder="<spring:message code="message.signIn.placeholder.id"/>" required autofocus>
                <input type="password" name="password" id="password" class="form-control" placeholder="<spring:message code="message.signIn.placeholder.password"/>" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><spring:message code="message.signIn.btn"/></button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div>
</div>

<script>
    function chkUser() {
        var id = document.getElementById("id").value;
        var password = document.getElementById("password").value;

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !id || !password ) {
            alert("<spring:message code="message.signIn.empty.alert"/>");
            return false;
        } else {
            return true;
        }
    }
</script>

<hr>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>