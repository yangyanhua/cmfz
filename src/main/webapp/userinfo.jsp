<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%--
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>--%>
<style type="text/css">
    .inp {
        margin-top:30px;
    }
</style>
<table id="tab"></table>
<script>

    $(function () {
        $('#tab').datagrid({
            title:'用户信息',
            fit:true,
            fitColumns:true,
            url:'${pageContext.request.contextPath}/userinfo/Paging.do',
            columns:[[
                {field:'id',title:'编号',width:25},
                {field:'name',title:'真实姓名',width:25},
                {field:'fhname',title:'法号',width:25},
                {field:'email',title:'邮箱',width:25},
                {field:'phone',title:'手机号',width:25},
                {field:'sex',title:'性别',width:25},
                {field:'site',title:'所在地',width:25},
                {field:'signature',title:'签名',width:25},
                {field:'state',title:'状态',width:25,
                    /* formatter:function(val,row,index){
                         return row.gurn.upname;
                     },*/
                    styler:function(val,row,index){
                        if(val=="ok"){
                            return 'background-color:yellow;color:red;';
                        }
                    }
                },
                {field:'addtime',title:'注册时间',width:25},
                {field:'logintime',title:'最后一次登录时间',width:35},
                {field:'cz',title:'操作',width:30,
                    formatter:function (value,row,index) {
                        console.log(row.id);
                        return '<a href="javascript:(void(0))" class="deteledd" onclick="del('+row.id+')"></a><a href="javascript:(void(0))" class="update" onclick="update('+row.id+')"></a>';
                    }},
            ]],
            toolbar:[{
                text:'导出数据',
                iconCls:'icon-2012080412511',
                handler: function(){
                    $('#dd').dialog({
                        title: '导出数据',
                        width: 400,
                        height: 250,
                        modal:true,


                    });
                }

            },{
                text:'导入数据',
                iconCls:'icon-2012080412901',
                handler: function(){alert('编辑按钮')}

            }],
            pagination:true,
            onLoadSuccess:function(){
                $('.deteledd').linkbutton({
                    text:'删除',
                    iconCls:'icon-control_remove_blue'
                });
                $('.update').linkbutton({
                    text:'修改',
                    iconCls:'icon-pencil'
                })
            },
        });
        //加载远程数据上师
        $('#sel').combobox({
           valueField:'id',
           textField:'upname',
           url:'${pageContext.request.contextPath}/userinfo/findupname.do',
           width:100,
           select:0,
        });
        $("#sel").combobox("select","1");
    })

    //删除
    function del(id){
     $.messager.confirm('确认','您确定想要删除吗',function(f){
      if(f){
      $.ajax({
      url:'${pageContext.request.contextPath}/userinfo/delete.do',
      data:'id='+id,// 传一个当前行的id
      type:'POST',
      success:function(data){
      if(data=="ok"){
      $('#tab').datagrid('reload');
          }},
         });
       }else {
        console.log(id);
      }
  });
}

    //修改
    function update(id){
        $('#update').dialog({
           title:'修改',
           iconCls:'icon-pencil',
           width:500,
           height:500,
           modal:true,
           buttons:[{
           text:'提交',
           iconCls:'icon-pencil',
           handler:function(){
            $('#ff').form({
             url:"${pageContext.request.contextPath}/userinfo/update.do?id="+id,
             onSubmit(){
                return is = $('#ff').form('validate');
             },success:function (data) {
                if(data=="ok"){
                    $('#update').dialog('close');
                    $('#tab').datagrid('reload');
                }else {
                    $('#update').dialog('close');
                    alert("修改失败");
                }
           }
        });
        $('#ff').submit();
       },
    }],



      });

    }

</script>
<div id="update">
    <form method="post" id="ff">
            <div  style="text-align:center;">
                <div class="inp">
                    法号：<input  class="easyui-textbox" data-options="prompt:'法号',required:true" name="fhname">
                </div>
                <div class="inp">
                    邮箱：<input  class="easyui-textbox" data-options="prompt:'123456@qq.com',required:true,validType: 'email'" name="email">
                </div>
                <div class="inp">
                    手机：<input  class="easyui-textbox" data-options="prompt:'1388888888',required:true" name="phone">
                </div>
                <div class="inp">
                    密码：<input  class="easyui-textbox" data-options="prompt:'密码',required:true" name="password">
                </div>

                <div class="inp">
                    位置：<input  class="easyui-textbox" data-options="prompt:'位置'" name="site">
                </div>
                <div class="inp">
                    头像：<input  class="easyui-textbox" data-options="prompt:'头像'" name="headurl">
                </div>
                <div class="inp">
                    签名：<input  class="easyui-textbox" data-options="prompt:'签名'" name="signature">
                </div>
                <div class="inp">
                    状态：<input  class="easyui-textbox" data-options="prompt:'状态'" name="state">
                </div>
                <div class="inp">
                    QQ：<input  class="easyui-textbox" data-options="prompt:'qq'" name="qq">
                </div>
                <div class="inp">
                    微信：<input  class="easyui-textbox" data-options="prompt:'微信'" name="weix">
                </div>
                <div class="inp">
                   上师：<select  id="sel" name="guru.id" >
                     </select>
                </div>

                <div class="inp">
                    性别：<input checked type="radio" name="sex" value="男"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"/>女
                </div>
            </div>
    </form>
</div>
<div id="dd" style="background-image:url('${pageContext.request.contextPath}/upload/321.jpg');background-repeat:no-repeat;background-size:100% 100%" >
    <form id="fff" method="post">
        <div class="inp" style="text-align:center;">
            <H1 style="color: crimson">请选择要导出的字段信息</H1>
        </div>
            <div class="inp" style="text-align:center;">
       <select id="cc" class="easyui-combotree" style="width:200px;"data-options="required:true"></select>
            </div>
    </form>
        <div class="inp" style="text-align:center;">
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提交</a>
        </div>
</div>
<script>
    $('#cc').combotree({
        url: '${pageContext.request.contextPath}/map.json',
        required: true,
        checkbox:true,
        onlyLeafCheck:true,
        multiple:true,
    });
    $("#btn").click(function () {
        //获取combotree的选中信息
        var content = $("#cc").combotree("getText");

        var vals = $("#cc").combotree("getValues");
        var ss="";
        $.each(vals,function (index,filed) {
            if(index<vals.length-1){
                ss += filed+",";
            }else{
                ss += filed;
            }

        })

        //提交form表单
        console.log(ss);
        console.log(content);
        $("#fff").form("submit",{
            url:"${pageContext.request.contextPath}/poi/export.do",
            queryParams:{"content":content,"fields":ss},
          /*  handler:function () {
                    $.messager.show({
                        title:'提醒',
                        msg:'导出成功',
                        showType:'show',

                    });
            }*/
        });
    });
</script>


