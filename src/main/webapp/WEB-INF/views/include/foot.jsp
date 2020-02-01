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
                        <a href="#">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="far fa-envelope fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                            <span class="fa-stack fa-lg">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">Copyright &copy; MyHyem-Blog 2020</p>
            </div>
        </div>
    </div>
</footer>

</body>

<script>
    ClassicEditor
        .create( document.querySelector( '#content' ) )
        .then( newEditor => {
            editor = newEditor;
        } )
        .catch(function (error) {
            console.log( error );
        } );

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
        }
    }
</script>

</html>