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

</head>
<body>
<table id="jo"></table>


<script type="text/javascript">
    $(function(){
        $('#jo').datagrid({
            title:'日志列表',
            fit:true,
            columns:[[
                {field:'ck',checkbox:true},
                {field:'id',title:'编号',width:150},
                {field:'admin',title:'管理员',width:150},
                {field:'time',title:'操作时间',width:150},
                {field:'parameter',title:'调用的业务',width:150},
                {field:'action',title:'是否成功',width:150},
                {field:'result',title:'参数',width:150,
                   /* formatter:function(val,row,index){
                        return row.gurn.upname;
                    },
                    styler:function(val,row,index){

                        if(row.gurn.id==1){
                            return 'background-color:yellow;color:red;';
                        }
                    }*/
                },
                {field:'cz',title:'操作',width:200,
                    formatter:function(val,row,index){
                        return '<a href="javascript:(void(0))" class="deteledd" onclick="del('+row.id+')">'
                    }
                }
            ]],
            toolbar:[{
                text:'批量删除',
                iconCls:'icon-no',
                handler:function(){
                var startList = new Array();
                var allObj = $('#jo').datagrid('getSelections');

                for(var i=0;i<allObj.length;i++){
                        startList[i] = allObj[i].id;}
                    var lists = JSON.stringify(startList);

                    console.log(allObj.length);

                    if(allObj.length!=0){
                    $.ajax({
                      url:'${pageContext.request.contextPath}/journal/pldelete.do?myLists='+lists,
                      type:'post',
                      success:function(data){
                      $('#jo').datagrid('reload');
                       }
                     });
                    }else{
                      $.messager.alert('提示','请先选择多个或者一个在点击批量删除','info');
                    }
                },

            }],
            url:'${pageContext.request.contextPath}/journal/findjournal.do',
            pagination:true,
            onLoadSuccess:function() {
                $('.deteledd').linkbutton({
                    text: '删除',
                    iconCls: 'icon-control_remove_blue'
                });
            }
        });
    });

    //删除
    function del(id){
        $.messager.confirm('确认','您确定想要删除吗',function(f){
            if(f){
                $.ajax({
                    url:'${pageContext.request.contextPath}/journal/delete.do',
                    data:'id='+id,// 传一个当前行的id
                    type:'POST',
                    success:function(data){
                        if(data=="ok"){
                            $('#jo').datagrid('reload');
                        }},
                });
            }else {
                console.log(id);
            }
        });
    }

</script>


</body>

</html>