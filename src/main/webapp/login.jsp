<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="css/common.css" type="text/css" />
    <link rel="stylesheet" href="css/login.css" type="text/css" />
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/jquery.easyui.min.js"></script>
    <script type="text/javascript">

        $(function(){
            $('#fr').form({
                url:'${pageContext.request.contextPath}/user/login.do',
                onSubmit:function(){
                    return is = $('#fr').form('validate');
                },ajax:true,
                success:function(data){
                    if(data=="ok"){
                       location.href='${pageContext.request.contextPath}/main.jsp';
                    }else{
                        alert('验证码有误');
                    }
                }


            });
            $('#fr').submit();
        });


        //刷新验证码
        function  yanzmya(obj){
         obj.src='${pageContext.request.contextPath }/user/code.do?'+Math.random();
          }


        </script>
</head>
<body style="background-image:url(img/rs.jpg)";>

<div class="login" >

    <form id="fr" method="post">
    <table>
        <tbody>
        <tr>
            <td width="190" rowspan="2" align="center" valign="bottom">
                <img src="img/header_logo.gif" />
            </td>
            <th>
                用户名:
            </th>
            <td>
                <input type="text"  name="name" class="easyui-textbox" data-options="prompt:'张三',required:true" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <th>
                密&nbsp;&nbsp;&nbsp;码:
            </th>
            <td>
                <input type="password" name="password" class="easyui-password" data-options="required:true" maxlength="20" autocomplete="off"/>
            </td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <th>验证码:</th>
            <td>
                <input type="text" id="enCode" name="code" class="text captcha"   maxlength="4" autocomplete="off"/>
                <img class="captchaImage" src="${pageContext.request.contextPath }/user/code.do" title="点击更换验证码" onclick="yanzmya(this);"/>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <th>&nbsp;</th>
            <td>
                <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit" class="loginButton" value="登录">
            </td>
        </tr>
        </tbody>
    </form>

    </table>
    <div class="powered">COPYRIGHT © 2008-2099.</div>
    <div class="link">
        <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
        <a href="http://www.chimingbbs.com/">交流论坛</a> |
        <a href="">关于我们</a> |
        <a href="">联系我们</a> |
        <a href="">授权查询</a>
    </div>
    </form>
</div>
</body>
</html>