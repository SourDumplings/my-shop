var App = function ()
{
  var _masterCheckbox;
  var _checkbox;

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

  return {
    init: function ()
    {
      handlerCheckBox();
      handlerCheckboxAll();
    },

    getCheckbox: function ()
    {
      return _checkbox;
    }
  }
}();

// 导入就初始化
$(document).ready(function ()
{
  App.init();
});