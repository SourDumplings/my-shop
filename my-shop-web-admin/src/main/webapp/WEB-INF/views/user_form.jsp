<%--
  Created by IntelliJ IDEA.
  User: CHANG Zheng
  Date: 2019/11/12
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>

    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">
                                &times;
                            </button>
                                ${baseResult.message}
                        </div>
                    </c:if>


                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbUser.id == null ? "新增": "编辑"}用户</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/user/save"
                                   modelAttribute="tbUser">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="email"
                                           class="col-sm-2 control-label">邮箱</label>

                                    <div class="col-sm-10">
                                        <form:input path="email" cssClass="form-control required email"
                                                    placeholder="请输入邮箱地址"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password"
                                           class="col-sm-2 control-label">密码</label>

                                    <div class="col-sm-10">
                                        <form:password path="password" cssClass="form-control required"
                                                    placeholder="请输入用户的密码"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="username"
                                           class="col-sm-2 control-label">姓名</label>

                                    <div class="col-sm-10">
                                        <form:input path="username" cssClass="form-control required"
                                                    placeholder="请输入用户的姓名"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone"
                                           class="col-sm-2 control-label">手机</label>

                                    <div class="col-sm-10">
                                        <form:input path="phone" cssClass="form-control required mobile"
                                                    placeholder="请输入用户的手机号"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default"
                                        onclick="history.go(-1)">返回
                                </button>
                                <button type="submit" class="btn btn-info pull-right">提交
                                </button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                        <!-- form end -->
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/copyright.jsp"/>
</div>
<!-- ./wrapper -->

<jsp:include page="../includes/footer.jsp"/>
</body>
</html>

