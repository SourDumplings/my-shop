<%--
  Created by IntelliJ IDEA.
  User: CHANG Zheng
  Date: 2019/11/11
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

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
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">
                                &times;
                            </button>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username"
                                               class="col-sm-4 control-label">姓名</label>

                                        <div class="col-sm-8">
                                            <input id="username" class="form-control"
                                                   placeholder="姓名"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email"
                                               class="col-sm-4 control-label">邮箱</label>

                                        <div class="col-sm-8">
                                            <input id="email" class="form-control"
                                                   placeholder="邮箱"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone"
                                               class="col-sm-4 control-label">手机号</label>

                                        <div class="col-sm-8">
                                            <input id="phone" class="form-control"
                                                   placeholder="手机号"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="button" class="btn btn-info pull-right"
                                    onclick="search()">搜索
                            </button>
                        </div>
                    </div>


                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div>

                        <div class="box-body">
                            <div class="col-xs-12">
                                <a href="/user/form" type="button"
                                   class="btn btn-primary btn-sm"><i
                                        class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                                <button type="button" onclick="App.deleteMulti('/user/delete')"
                                        class="btn btn-danger btn-sm"><i
                                        class="fa fa-trash"></i> 删除
                                </button>&nbsp;&nbsp;&nbsp;&nbsp;
                                <%--<a href="#" type="button"
                                   class="btn btn-default btn-sm"><i
                                        class="fa fa-level-down"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button"
                                   class="btn btn-default btn-sm"><i
                                        class="fa fa-level-up"></i> 导出</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                <button type="button"
                                        onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"
                                        class="btn btn-primary btn-sm"><i
                                        class="fa fa-search"></i> 搜索
                                </button>
                            </div>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table class="table table-hover" id="dataTable">
                                <thead>
                                <tr>
                                    <th><input id="${tbUser.id}" type="checkbox"
                                               class="minimal icheck_master"/></th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
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

<%-- 自定义模态框 --%>
<sys:modal/>

<script>
  var _dataTable;

  $(function ()
  {
    var _columns = [
      {
        "data": function (row, type, val, meta)
        {
          return '<input id="' + row.id
              + '" type="checkbox" class="minimal"/>'
        }
      },
      {"data": "id"},
      {"data": "username"},
      {"data": "phone"},
      {"data": "email"},
      {"data": "updated"},
      {
        "data": function (row, type, val, meta)
        {
          var detailUrl = "/user/detail?id=" + row.id;
          return '<button type="button" onclick="App.showDetail(\'' + detailUrl
              + '\')" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 查看</button>&nbsp;&nbsp;&nbsp;'
              +
              ' <a href="/user/form?id=' + row.id
              + '" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> 编辑</a>&nbsp;&nbsp;&nbsp;'
              /*+
              '<a href="#" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> 删除</a>';*/
        }
      }
    ];

    _dataTable = App.initDataTables("/user/page", _columns);
  });

  function search()
  {
    var username = $('#username').val();
    var email = $('#email').val();
    var phone = $('#phone').val();

    var params = {
      "username": username,
      "email": email,
      "phone": phone
    };
    _dataTable.settings()[0].ajax.data = params;
    _dataTable.ajax.reload();
  }

</script>

</body>
</html>
