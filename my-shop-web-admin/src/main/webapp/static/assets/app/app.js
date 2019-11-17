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
    }
  }
}();

// 导入就初始化
$(document).ready(function ()
{
  App.init();
});