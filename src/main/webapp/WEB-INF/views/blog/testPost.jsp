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
                <%--<c:out value="${post.content}" escapeXml="false"/>--%>

                <!-- content -->


                <c:if test="${user.name eq post.writer}">
                    <div class="clearfix">
                        <a class="btn btn-primary" href="javascript:sandData('modifyPost', ${post.idx})"><spring:message code="message.detailPost.btn.modify"/></a>
                        <a class="btn btn-primary" href="javascript:sandData('doDeletePost', ${post.idx})"><spring:message code="message.detailPost.btn.delete"/></a>
                    </div>
                </c:if>
                <hr>

                <!-- Reply Area -->
                <div id="rePlyArea">
                    <!-- Reply List -->
                    <div class="my-3 p-3 bg-white rounded shadow-sm">
                        <h6 class="border-bottom pb-2 mb-0 col-12"><spring:message code="message.detailPost.label.comments"/></h6>
                        <div id="replyList"></div>
                    </div>

                    <!-- Reply Input -->
                    <div class="my-3 p-3 bg-white rounded shadow-sm">
                        <form id="replyForm" method="post" onsubmit="return chkReply()">
                            <div class="row">
                                <div class="col-sm-9">
                                    <textarea class="form-control" id="replyContent" name="content" rows="5" placeholder="<spring:message code="message.detailPost.label.placeholder.comments"/>" required></textarea>
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="replyWriter" name="writer" value="" placeholder="<spring:message code="message.detailPost.label.placeholder.name"/>" required>
                                    <input type="hidden" id="targetIdx" name="targetIdx" value="${post.idx}"/>
                                    <input type="hidden" id="pageNo" name="pageNo" value=""/>
                                    <div class="clearfix">
                                        <a class="btn btn-sm btn-primary" id="writeReplyBtn" href="javascript:doWriteReply()"><spring:message code="message.detailPost.btn.comments"/></a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Reply pagination -->
                <nav aria-label="Page navigation">
                    <ul class="pagination pg-blue justify-content-center" id="paginationArea"></ul>
                </nav>

            </div>
        </div>
    </div>
</article>
<hr>

<script>
    var totalReplyCnt = ${post.replyCnt};
    var totalReplyPage = Math.ceil( totalReplyCnt / 7 );

    $(function(){
        $("#pageNo").val(1);
        doListReply();
    });

    function doListReply() {
        var replyListArea = document.getElementById("replyList");
        var replyWriter = "${user.id}";

        $.ajax({
            type:'GET',
            url : "/doListReply.do",
            dataType : "json",
            data:$("#replyForm").serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success : function(data){
                var replyList = "";
                var replyCnt = data.length;
                var pageNo =  $("#pageNo").val();

                if (replyCnt > 0) {
                    for (i=0; i<replyCnt; i++) {
                        replyList += '<div class="media text-muted pt-3">';
                        replyList += '<p id="writerArea" class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                        replyList += '<span class="d-block col-12">';
                        replyList += '<strong class="text-gray-dark">'+data[i].writer+'</strong>';

                        if (replyWriter == "admin") {
                            replyList += '<span id="btnArea">';
                            replyList += '<a class="btn-link" id="deleteBtn" href="javascript:doDeleteReply('+data[i].idx+')">delete</a>';
                            replyList += '</span>';
                        }

                        replyList += '</span>';
                        replyList += '<span class="col-12">'+data[i].content+'</span>';
                        replyList += '</p>';
                        replyList += '</div>';
                    }
                }

                replyListArea.innerHTML = replyList;
                doReplyPagePrint(pageNo);
            },
            error:function(request,status,error){
                alert("code:"+request.status+"\n"+"error:"+error);
            }
        });
    }

    function doWriteReply() {
        $.ajax({
            type:'POST',
            url : "/doWriteReply.do",
            data:$("#replyForm").serialize(),
            success : function(data) {
                if(data == "success") {
                    doListReply();
                    $("#replyContent").val("");
                    $("#replyWriter").val("");
                }
            },
            error:function(request,status,error) {
                alert("code:"+request.status+"\n"+"error:"+error);
            }
        });
    }

    function doDeleteReply(idx) {
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");
        form.setAttribute("id", "deleteForm");

        var hiddenField_1 = document.createElement("input");
        hiddenField_1.setAttribute("type", "hidden");
        hiddenField_1.setAttribute("id", "idx");
        hiddenField_1.setAttribute("name", "idx");
        hiddenField_1.setAttribute("value", idx);
        form.appendChild(hiddenField_1);

        var hiddenField_2 = document.createElement("input");
        hiddenField_2.setAttribute("type", "hidden");
        hiddenField_2.setAttribute("name", "targetIdx");
        hiddenField_2.setAttribute("value", $("#targetIdx").val());
        form.appendChild(hiddenField_2);

        document.body.appendChild(form);

        $.ajax({
            type:'POST',
            url : "/doDeleteReply.do",
            data:$("#deleteForm").serialize(),
            success : function(data) {
                if(data == "success") {
                    doListReply(data);
                    form.remove();
                } else if (data == "fail") {
                    alert("관리자만 가능");
                }
            },
            error:function(request,status,error) {
                alert("code:"+request.status+"\n"+"error:"+error);
            }
        });
    }

    function doReplyPagePrint(pageNo) {
        var lastDivPage = Math.floor(totalReplyPage/5);
        var prevPage = 0;
        var nextPage = 0;
        var pageHtml = "";

        if (pageNo > 5) {
            prevPage += (Math.floor((pageNo-1)/5)-1)*5+1;
            pageHtml += '<li class="page-item"><a class="page-link" href="javascript:doReplyPaging('+prevPage+')"><</a></li>';
        }

        for (var i=1; i<=5; i++) {
            var calPage = (Math.floor((pageNo-1)/5)*5)+i;

            if (calPage == pageNo) {
                pageHtml += '<li class="page-item active"><a class="page-link">' + calPage + '</a></li>';
            } else if (calPage > totalReplyPage) {
                break;
            } else {
                pageHtml += '<li class="page-item"><a class="page-link" href="javascript:doReplyPaging('+calPage+')">'+calPage+'</a></li>';
            }
        }

        if ( (totalReplyPage > 5) && pageNo <= (lastDivPage*5) && (lastDivPage*5) != totalReplyPage ) {
            nextPage += (Math.floor((pageNo-1)/5)+1)*5+1;
            pageHtml += '<li class="page-item"><a class="page-link" href="javascript:doReplyPaging('+nextPage+')">></a></li>';
        }

        document.getElementById("paginationArea").innerHTML = pageHtml;
    }

    function doReplyPaging(pageNo) {
        document.getElementById("pageNo").value = pageNo;
        doListReply();
    }
</script>

<!-- Include Footer -->
<%@ include file="/WEB-INF/views/include/foot.jsp"%>