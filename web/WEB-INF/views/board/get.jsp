<%--
  Created by IntelliJ IDEA.
  User: bongchangyun
  Date: 2022/02/17
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/header.jsp"%>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Register</h1>
    </div>
    <!-- /.col-lg-12 -->

</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Read Page</div>

            <!-- /.panel-body-->
            <div class="panel-body">

                    <div class="form-group">
                        <label>Bno</label><input class="form-control" name="bno"
                    value='<c:out value="${board.bno}" />' readonly>
                    </div>

                    <div class="form-group">
                        <label>Title</label><input class="form-control" name="title"
                    value='<c:out value="${board.title}"/>' readonly>
                    </div>

                    <div class="form-group">
                        <label>Text Area</label>
                        <textarea class="form-control" rows="3" name="content" readonly><c:out value="${board.content}" /></textarea>
                    </div>

                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly>
                    </div>

                    <button data-oper="modify" class="btn btn-default">Modify Button</button>
                    <button data-oper="list" class="btn-info btn">List</button>

                    <form id="operForm" action="/board/modify" method="get">
                        <input type="hidden" id="bno" name="bno" value="<c:out value='${board.bno}' />">
                        <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}" />'>
                        <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'/>
                        <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'/>
                    </form>
            </div>
            <!-- end panel-body -->
        </div>
    <%--        end panel-bpdy--%>
    </div>
    <%--    end panel--%>
</div>
<%--/.row--%>

<script type="text/javascript">
    $(document).ready(function (){
        let operForm = $("#operForm");

        $("button[data-oper='modify']").on("click", function (e){
            // console.log('Modify Clicked!');
            operForm.attr("action", "/board/modify").submit();
        });

        $("button[data-oper='list']").on("click" , function (e){
            operForm.find("#bno").remove();
            operForm.attr("action" , "/board/list")
            operForm.submit();
        });
    });
</script>

<%@ include file="../includes/footer.jsp"%>