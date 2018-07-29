<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>持名法州后台管理中心</title>

    <%--引入goeasy的JS文件--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/back/static/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/static/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/back/echarts/echarts.js"></script>

    <script type="text/javascript">
        /*创建菜单栏*/
        $(function(){
            //加载数据库中的菜单信息
            $.get('${pageContext.request.contextPath}/menu/find.do',function(data){
                //menus是一个一级菜单集合  每一个一级菜单都是手风琴的面板
                   /* <div class="easyui-linkbutton" data=></div>*/

                $.each(data,function (i,dd) {

                    var content = "";
                    $.each(dd.menu,function (i,child) {
                        //构建我们的content
                        content+="<div class=\"easyui-linkbutton\" onclick=\"addTab('"+child.content+"','"+child.iconcls+"','"+child.url+"')\" data-options=\"iconCls:'"+child.iconcls+"'\" style=\"border:1px solid green;width:90%;margin:5 5 5 5\">"+child.content+"</div>";
                    })
                    //添加面板
                    $('#m').accordion("add",{
                        title: dd.content,
                        iconCls:dd.iconcls,
                        content:content
                       });
                })

            },"JSON");


        });

        function addTab(title,iconCls,url){
            //判断面板是否存在
            if(!$("#tt").tabs('exists',title)){
                //创建新的tab
                $("#tt").tabs('add',{
                    title:title,
                    iconCls:iconCls,
                    href:"${pageContext.request.contextPath}/"+url,
                    closable:true,
                    tools:[{
                        iconCls:'icon-mini-refresh',
                        handler:function(){
                            var tab = $('#tt').tabs('getSelected');
                            tab.panel('refresh',"${pageContext.request.contextPath}/"+url);
                        }
                    }],
                })
            }else{
                $("#tt").tabs('select',title);
            }
        }
        //退出
        function tuichu(){
        $('#out').click(function () {
            $.ajax({
                url:'${pageContext.request.contextPath}/user/empty.do',
                type:'get',
                success:function (data) {
                    if(data=="ok"){
                        location.href="${pageContext.request.contextPath}/login.jsp"
                    }
                }
            })
        });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:#5C160C ">
    <div style="font-size: 24px;color: white;font-family: 楷体;float: left;padding-top: 10px;padding-left: 20px">驰名法洲后台管理系统</div>
    <div style="font-size: 24px;color: white;font-family: 楷体;float: right;padding-top: 10px;padding-right: 20px">欢迎您:${sessionScope.admin.name}
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" >修改密码</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="out" onclick="tuichu()">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height:40px;background-color:#5C160C ">
    <div style="text-align: center;font-family: 楷体;color: white;font-size: 15px">&copy; yyh; yyh427@163.com</div>
</div>
<div data-options="region:'west',title:'导航菜单',split:true" style="width:200px;">
    <div id="m" class="easyui-accordion" data-options="fit:true"></div>
</div>
<div data-options="region:'center'" >
    <div id="tt" class="easyui-tabs" data-options="fit:true,pill:true,narrow:true" style="width:500px;height:250px;">
        <div title="主页" data-options="iconCls:'icon-house'" style="background-image:url('${pageContext.request.contextPath}/img/shouye.jpg');background-repeat:no-repeat;background-size:100% 100%" ></div>
    </div>
</div>
</body>
</html>