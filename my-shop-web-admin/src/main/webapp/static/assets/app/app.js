var App = function ()
{
  // iCheck
  var _masterCheckbox;
  var _checkbox;

  // 用于存放 ID 的数组
  var _idArray;

  /**
   * 私有方法：初始化iCheck
   */
  var handlerCheckBox = function ()
  {
    // 激活 iCheck
    $('input[type="checkbox"].minimal, input[type="checkbox"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass: 'iradio_minimal-blue'
    });

    // 获取控制端checkbox
    _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
    // 获取全部checkbox集合
    _checkbox = $('input[type="checkbox"].minimal');
  };

  /**
   * 私有方法：checkbox的全选功能
   */
  var handlerCheckboxAll = function ()
  {
    _masterCheckbox.on("ifClicked", function (e)
    {
      if (e.target.checked)
      {
        _checkbox.iCheck("uncheck");
      }
      else
      {
        _checkbox.iCheck("check");
      }
    });
  };

  /**
   * 批量删除
   */
  var handlerDeleteMulti = function (url)
  {
    _idArray = new Array();

    // console.log("_checkbox 的数量：", _checkbox.length);
    _checkbox.each(function ()
    {
      // console.log($(this).attr('id'));
      var _id = $(this).attr('id')
      if (_id != null && _id != "undefine" && $(this).is(":checked"))
      {
        // 将选中元素的 ID 加入到数组中
        _idArray.push(_id);
      }
    });
    // console.log(_idArray);

    if (_idArray.length === 0)
    {
      $('#modal-message').html("您还没有选择任何数据项，请至少选择一项。");
    }
    else
    {
      $('#modal-message').html("您确定删除数据项吗？");

    }
    $('#modal-default').modal('show');

    $('#btnModalOK').bind('click', function ()
    {
      del();
    });

    /**
     * 当前私有函数的私有函数 del 删除数据
     */
    function del()
    {
      $('#modal-default').modal('hide');

      if (_idArray.length === 0)
      {
        // 如果没有选择删除项
      }
      else
      {
        // console.log("提交数据到后台");
        setTimeout(function ()
        {
          $.ajax({
            "url": url,
            "type": "POST",
            "data": {"ids": _idArray.toString()},
            "dataType": "JSON",
            "async": false,  // 设置为同步请求
            "success": function (data)
            {
              if (data.status === 200)
              {
                window.location.reload();
              }
              else
              {
                $('#btnModalOK').unbind('click');
                $('#btnModalOK').bind('click', function ()
                {
                  $('#modal-default').modal('hide');
                });

                $('#modal-message').html(data.message);
                $('#modal-default').modal('show');
              }
            }
          });
        }, 500);
      }
    }
  };

  var handlerInitDatatables = function (url, columns)
  {
    $('#dataTable').DataTable({
      "paging": true,
      "info": true,
      "lengthChange": false,
      "ordering": false,
      "processing": true,
      "searching": false,
      "serverSide": true,
      "deferRender": true,
      "ajax": {
        "url": url
      },
      "columns": columns,
      "language": {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
          "sFirst": "首页",
          "sPrevious": "上页",
          "sNext": "下页",
          "sLast": "末页"
        },
        "oAria": {
          "sSortAscending": ": 以升序排列此列",
          "sSortDescending": ": 以降序排列此列"
        }
      },
      "drawCallback": function ()
      {
        handlerCheckBox();
        handlerCheckboxAll();
      }
    });
  };

  /**
   * 查看详情
   *
   * @param url
   */
  var handlerShowDetail = function (url)
  {
    $.ajax({
      url: url,
      type: "get",
      dataType: "html",
      success: function (data)
      {
        $('#modal-detail-body').html(data);
        $('#modal-detail').modal('show');
      }
    });
  };

  return {
    init: function ()
    {
      handlerCheckBox();
      handlerCheckboxAll();
    },

    getCheckbox: function ()
    {
      return _checkbox;
    },

    deleteMulti: function (url)
    {
      handlerDeleteMulti(url);
    },

    initDataTables: function (url, columns)
    {
      handlerInitDatatables(url, columns);
    },
    
    showDetail: function (url)
    {
      handlerShowDetail(url);
    }
  }
}();

// 导入就初始化
$(document).ready(function ()
{
  App.init();
});