<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>持名法州后台管理中心</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
        .inp {
            margin-top:30px;
        }
    </style>

</head>
<body >
<table id="bobo"></table>

<div id="add" >
    <%--上传图片--%>
    <table >
        <tr>
            <td>
    <form id="fi" method="POST" enctype="multipart/form-data">

        <input type="file" name="url" /><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="sub()">上传</a>
        <div style="border: #00ee00 solid 1px">
            <img id="imgs" src="" width="300" height="200"/>
        </div>
    </form>
            </td>
    <%--上传信息--%>
    <td>
<form id="fom"  method="post">
    <div class="inp" style="text-align:center;">
        <h1  style="font-size: 24px;color:red ">添加主页轮播图</h1>
    </div>
<input type="hidden" name="url"  id="imm"/>
    <div class="inp" style="text-align:center;">
        图片描述：<input type="text" class="easyui-textbox"  data-options="iconCls:'icon-edit'" name="describe">
    </div>

    <div class="inp" style="text-align:center;">
        图片状态： <select class="easyui-combobox" name="state">
        <option value="yes">展示</option>
        <option value="no">不展示</option>
    </select>
    </div>
</form></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    /*创建菜单栏*/

    $(function () {
        $('#bobo').datagrid({
            title:'轮播图',
            fit:true,
            fitColumns:true,
            striped:true,
            columns:[[
                {field:'id',title:'编号',width:200
                },
                {field:'describe',title:'图片描述',width:200
                },
                {field:'state',title:'状态',width:200,
                  formatter:function (val,row,index) {
                        //console.log(row.state)
                     // console.log(val)
                     // console.log(index)
                    var ss =  $.each(row,function (i,dd) {})
                     // console.log(ss.id)
                      <%--<c:forEach items="row" var="cq">
                       console.log(cq.id)
                      </c:forEach>--%>
                    if(ss.state=="yes"){

                        return  ' <input id="stateyes" value='+ss.id+' class="state" data-options="onText:\'Yes\',offText:\'No\',checked:true,onChange:stayes">'
                    }else {
                        return  ' <input id="stateno"  value='+ss.id+' class="state"   data-options="onText:\'Yes\',offText:\'No\',onChange:stano">'
                    }
                        //console.log(row.state)
                    }
                },
                {field:'time',title:'上传时间',width:200
                },
                {field:'altertime',title:'更新时间',width:200
                },
                {field:'url',title:'图片',width:300,
                    formatter:function(val,row,index){
                       // console.log(row.url);
                        return '<img src="${pageContext.request.contextPath}/'+row.url+'" width="250" height="200"/>';
                    }
                },
                {field:'cc',title:'操作',width:200,
                    formatter:function(val,row,index){

                        return '<a href="javascript:(void(0))" class="deteledd" onclick="del('+row.id+')">删除</a>';
                    }
                },]],
            onLoadSuccess:function(){
            $('.deteledd').linkbutton({
               text:'删除',
                iconCls:'icon-control_remove_blue'
            });
            $('.state').switchbutton({

            });
            },
            url:'${pageContext.request.contextPath}/show/find.do',
            pagination:true,
            pageSize:4,
            pageList:[4,10,20,30],
            toolbar:[{
            text:'添加',
            iconCls:'icon-add',
            handler:function () {
                $('#add').dialog({
                    title:'添加图片',
                    width:600,
                    height:330,
                    modal:true,       //窗口化模式
                    collapsed:false,
                   // collapsible:true,//是否显示折叠按钮
                    resizable:false,//是否允许改变大小
                    draggable:false,//是否允许拖拽
                    buttons:[{
                        text:'提交',
                        iconCls:'icon-add',
                        handler:function () {
                            $('#fom').submit();
                            $('#add').panel('refresh');
                        }
                    },
                    ],

                    });
                // 初始化form 上传信息
                $('#fom').form({
                    url:'${pageContext.request.contextPath}/show/add.do',
                    onSubmit:function(param){
                        // 表单验证 ： 所有的数据合法  flag为true
                        var flag = $('#fom').form('validate');
                        return flag;// 如果返回false 表单终止提交
                    },
                    success:function(data){
                        // 关闭dialog
                        if(data=="ok"){
                        $('#add').dialog('close');
                        $('#bobo').datagrid('reload');
                        }
                    }
                });

            }
            }],
        });
    })

    //删除
    function del(id){

        $.messager.confirm('确认','您确定想要删除吗',function(f){
            if(f){
                console.log(id);
                 $.ajax({
                    url:'${pageContext.request.contextPath}/show/operation.do',
                    data:'id='+id,// 传一个当前行的id
                    type:'POST',
                    success:function(data){
                        if(data=="ok"){
                        $('#bobo').datagrid('reload');
                        }
                    },
                    /*error:function(){
                    }*/
                });
               }else {
               console.log(123);
            }
         });
}

    //上传图片
 function sub() {
     $('#fi').form('submit',{
         url:'${pageContext.request.contextPath}/show/upload.do',
         success:function(data){
             console.log(data);
            $('#imgs').prop('src','${pageContext.request.contextPath}'+data);
            $('#imm').prop('value',data);
             $.messager.show({
                 title:'提现',
                 msg:'上传成功！！！~',
                 timeout:3000,
                 showType:'slide'
             });

         }
     });
 }
 //修改状态
function stayes(){
 console.log($('#stateyes').val())
    dd = $('#stateyes').val()
        $.ajax({
            url:"${pageContext.request.contextPath}/show/updatesta.do?id="+dd,
            type:'post',
            data:{state:"no"},
            success:function (data) {
                if(data=="ok"){
                    $('#bobo').datagrid('reload');
                }
            }
        });
}
//修改状态
function stano(){
        console.log($('#stateno').val())
    dd = $('#stateno').val()
    $.ajax({
        url:"${pageContext.request.contextPath}/show/updatesta.do?id="+dd,
        type:'post',
        data:{state:"yes"},
        success:function (data) {
            if(data=="ok"){
                $('#bobo').datagrid('reload');
            }
        }
    });
    }

</script>


</body>

</html>