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
<body >
<table id="bobo"></table>
<input id="cc" name="dept" value="01" />
</div>
<script type="text/javascript">
    /*创建菜单栏*/

    $(function () {
        $('#cc').combogrid({
            value:'006',
            width:'100%',
            panelWidth:500,
            label:'Select Item:',
            labelPosition:'top',
            url:'${pageContext.request.contextPath}/userFiledsData.json',
            idField:'id',
            treeField:'name',
            columns:[[
                {field:'name',title:'Name',width:200},
                {field:'size',title:'Size',width:100},
                {field:'date',title:'Date',width:100}
            ]]
        });
    });
    });


</script>


</body>

</html>