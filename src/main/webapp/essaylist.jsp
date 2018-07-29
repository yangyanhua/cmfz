<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>持名法州后台管理中心</title>

<%--    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>--%>

</head>
<body>
<table id="btu"></table>
<div id="ylck"></div>

<script type="text/javascript">
    $(function(){
        $('#btu').datagrid({
            title:'文章列表',
            fit:true,
            columns:[[
                {field:'id',title:'编号',width:200},
                {field:'title',title:'标题',width:200},
                {field:'issuetime',title:'发布时间',width:200},
                {field:'content',title:'内容',width:200,
                    /*formatter:function (val,row,index) {

                        return '<a href="javascript:(void(0))" class="yunantup" onclick="yunantup('+val+')"></a>';
                    }*/
                },
                {field:'upid',title:'上师作者',width:200,
                    formatter:function(val,row,index){
                        return row.gurn.upname;
                    },
                    styler:function(val,row,index){

                        if(row.gurn.id==1){
                            return 'background-color:yellow;color:red;';
                        }
                    }
                },
                {field:'cz',title:'操作',width:200,
                    formatter:function(val,row,index){
                        return '<a id="bb" data-options=""></a>'
                    }
                }
            ]],
            onLoadSuccess:function(){
                $('.yunantup').linkbutton({
                    text:'预览',
                    iconCls:'icon-2012092109942',
                });
            },
            url:'${pageContext.request.contextPath}/editor/findlist.do',
            pagination:true,
        });
    });

    /*function yunantup(val){
       $('#ylck').dialog({

       })
    }*/

</script>


</body>

</html>