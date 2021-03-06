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
                <form action="/board/modify" method="post" role="form">

                    <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'/>
                    <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'/>
                    <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>

                    <div class="form-group">
                        <label>Bno</label><input class="form-control" name="bno"
                    value='<c:out value="${board.bno}" />' readonly>
                    </div>

                    <div class="form-group">
                        <label>Title</label><input class="form-control" name="title"
                    value='<c:out value="${board.title}"/>'>
                    </div>

                    <div class="form-group">
                        <label>Text Area</label>
                        <textarea class="form-control" rows="3" name="content"><c:out value="${board.content}" /></textarea>
                    </div>

                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly>
                    </div>

                    <div class="form-group">
                        <label>RegDate</label>
                        <input class="form-control" name="regDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regDate}"/>' readonly>
                    </div>

                    <div class="form-group">
                        <label>Update Date</label>
                        <input class="form-control" name="regDate" value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>' readonly>
                    </div>

                    <button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
                    <button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
                    <button type="submit" data-oper="list" class="btn-info btn">List</button>
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
        let formObj = $("form");

        $('button').on('click', function (e){
            e.preventDefault();
            let operation = $(this).data("oper");
            console.log(operation);

            if(operation === 'remove'){
                formObj.attr("action", "/board/remove");
            }else if(operation === 'list'){
                // self.location = "/board/list";
                // return;
                formObj.attr("action", "/board/list").attr("method", "get");

                let pageNumTag = $("input[name='pageNum']").clone();
                let amountTag = $("input[name='amount']").clone();
                let keywordTag = $("input[name='keyword']").clone();
                let typeTag = $("input[name='type']").clone();

                formObj.empty();
                formObj.append(pageNumTag);
                formObj.append(amountTag);
                formObj.append(typeTag);
                formObj.append(keywordTag);
            }
            formObj.submit();
        });

    });
</script>

<%@ include file="../includes/footer.jsp"%>


