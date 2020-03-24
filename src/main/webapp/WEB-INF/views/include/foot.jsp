<%--
  Created by IntelliJ IDEA.
  User: MyHyem
  Date: 2020-01-20
  Time: 오후 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="/sendEmail.do">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="far fa-envelope fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="https://github.com/MyHyem?tab=repositories" target="_blank">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">Copyright &copy; MyHyem&#39;s Blog 2020</p>
            </div>
        </div>
    </div>
</footer>

</body>

<!-- user custom script -->
<script>
    window.onLoad = function () {
        CKEDITOR.replace('content');
    };

    function chkUser() {
        var id = document.getElementById("id").value;
        var password = document.getElementById("password").value;

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !id || !password ) {
            alert("<spring:message code="message.signIn.alert.empty"/>");
            return false;
        } else {
            return true;
        }
    }

    function chkPost() {
        var title = document.getElementById("title").value;
        var content = editor.getData();

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !title || !content ) {
            alert("<spring:message code="message.writePost.alert.empty"/>");
            return false;
        } else {
            return true;
        }
    }

    function chkEmail() {
        var from = document.getElementById("emailFrom").value;
        var title = document.getElementById("emailTitle").value;
        var content = document.getElementById("emailContent").value;
        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !from || !title || !content ) {
            alert("<spring:message code="message.sendEmail.alert.empty"/>");
            return false;
        } else if ( !reg_email.test(from) ) {
            alert("<spring:message code="message.sendEmail.alert.from"/>");
            return false;
        } else {
            return true;
        }
    }

    function chkReply() {
        var writer = document.getElementById("replyWriter").value;
        var content = document.getElementById("replyContent").value;

        /* Checking params is " '', null, undefined, 0, NaN" */
        if ( !writer || !content ) {
            alert("<spring:message code="message.detailPost.alert.empty"/>");
            return false;
        } else {
            return true;
        }
    }

    function chkMsg() {
        var receive = "${msg}";

        if (receive == "signSuc") {
            alert('<spring:message code="message.home.msg.signSuc"/>');
        } else if (receive == "signFail") {
            alert('<spring:message code="message.home.msg.signFail"/>');
        } else if (receive == "signOut") {
            alert('<spring:message code="message.home.msg.signOut"/>');
        } else if (receive == "pleaseSignIn") {
            alert('<spring:message code="message.home.msg.pleaseSignIn"/>');
        } else if (receive == "writeSuc") {
            alert('<spring:message code="message.home.msg.writeSuc"/>');
        } else if (receive == "emailSuc") {
            alert('<spring:message code="message.home.msg.emailSuc"/>');
        } else if (receive == "emailFail") {
            alert('<spring:message code="message.home.msg.emailFail"/>');
        }
    }

    function sandData(mode, idx) {
        if (mode == "doDeletePost") {
            if (!confirm('<spring:message code="message.detailPost.alert.delete"/>')) {
                return false;
            }
        }

        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", "/"+mode+".do");

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "idx");
        hiddenField.setAttribute("value", idx);
        form.appendChild(hiddenField);

        document.body.appendChild(form);

        form.submit();
    }

    function sandPage(mode, pageNo) {

        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("action", mode);

        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "pageNo");
        hiddenField.setAttribute("value", pageNo);
        form.appendChild(hiddenField);

        document.body.appendChild(form);

        form.submit();
    }
</script>

</html>