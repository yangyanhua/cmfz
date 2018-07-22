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
    <style type="text/css">
        .inp {
            margin-top:30px;
        }
    </style>

</head>
<table id="al"></table>

<div id="addyy" >
    <%--上传图片--%>
    <table >
        <tr>
            <td>
                <form id="fi" method="POST" enctype="multipart/form-data">
                    <div class="inp" style="text-align:center;">
                        <h1  style="font-size: 24px;color:#00ee00 ">添加专辑图片</h1>
                    </div>
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
                        专辑名：<input type="text" class="easyui-textbox"  data-options="iconCls:'icon-edit'" name="albumname">
                    </div>
                    <div class="inp" style="text-align:center;">
                        作者：  <input type="text" class="easyui-textbox"  data-options="iconCls:'icon-edit'" name="teller">
                    </div>
                    <div class="inp" style="text-align:center;">
                        集数  ：    <input type="text" class="easyui-textbox"  data-options="iconCls:'icon-edit'" name="pending">
                    </div>
                                  <input type="hidden" name="albumurl"  id="imm"/>
                    <div class="inp" style="text-align:center;">
                        简介  ：    <input type="text" class="easyui-textbox"  data-options="iconCls:'icon-edit'" name="albuintro">
                    </div>
                    <div class="inp" style="text-align:center;">
                      <input type="hidden" class="easyui-textbox"  data-options="iconCls:'icon-edit'"  value="/upload/5x.jpg" name="stars">
                    </div>

                </form></td>
        </tr>
    </table>
</div>


<script type="text/javascript">
    $(function(){
        $('#al').datagrid({
            title:'文章列表',
            fit:true,
            columns:[[
                {field:'id',title:'编号',width:100},
                {field:'albumname',title:'专辑名称',width:100},
                {field:'teller',title:'歌手',width:100},
                {field:'albumurl',title:'专辑图片',width:330,
                    formatter:function(val,row,index){
                        return '<img src="${pageContext.request.contextPath}/'+row.albumurl+'" width="330" height="180"/>';
                    }
                },
                {field:'addalbumtime',title:'专辑创建时间',width:100},
                {field:'issuealbutime',title:'专辑发布时间',width:100},
                {field:'stars',title:'专辑星级',width:100,
                    formatter:function(val,row,index){

                        return '<img src="${pageContext.request.contextPath}/'+row.stars+'" width="90" height="40"/>';
                    }
                },
                {field:'cz',title:'操作',width:100,
                    formatter:function(val,row,index){
                        console.log(row);
                        return '<a href="javascript:(void(0))" class="bofang" onclick="bof('+row.id+')"></a>';
                    }
                }
            ]],
            url:'${pageContext.request.contextPath}/album/Paging.do',
            pagination:true,
            onLoadSuccess:function(){
                $('.bofang').linkbutton({
                    text:'播放',
                    iconCls:'icon-graph'
                });
            },
            toolbar:[{
                text:'添加专辑',
                iconCls:'icon-add',
                handler:function(){
                   $('#addyy').dialog({
                    title:'添加专辑',
                    buttons:[{
                           text:'提交',
                           iconCls:'icon-add',
                           handler:function () {
                               $('#fom').submit();
                               $('#addyy').panel('refresh');
                           }
                       },
                       ],
                   });

                    // 初始化form 上传信息
                    $('#fom').form({
                        url:'${pageContext.request.contextPath}/album/add.do',
                        onSubmit:function(param){
                            // 表单验证 ： 所有的数据合法  flag为true
                            var flag = $('#fom').form('validate');
                            return flag;// 如果返回false 表单终止提交
                        },
                        success:function(data){
                            // 关闭dialog
                            if(data=="ok"){
                                $('#addyy').dialog('close');
                                $('#al').datagrid('reload');
                            }
                        }
                    });
                }

            }]
        });
    });





    function bof(id){
        $('#dfk').dialog({
            title:'播放器',
            width:260,
            height:80,
            buttons:[{
                text:'上一首',
                iconCls:'icon-previous_green',
                handler:function(){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/album/music.do',
                        type:'post',
                        data:{id:id},
                        success:function (data) {
                            if(data!=null){
                                $.each(data,function (i,dd) {
                                 $('#mu').prop('src',dd.musicurl);

                                });
                                /*console.log($('#music').attr('src'));
                                console.log($('#mu').attr('src'));
                                var audio = document.getElementById('mu');
                                audio.play();*/
                            }
                        }
                    })
                }

            },{
                text:'暂停',
                iconCls:'icon-play_green',
                handler:function(){
                    var audio = document.getElementById('music');
                    if(audio!==null){
                        if(audio.paused){
                            audio.play();// 播放
                        }else{
                            audio.pause();// 暂停
                        }
                    }
                }
            },{
                text:'下一首',
                iconCls:'icon-forward_green',
                handler:function(){

                }
            }

            ]
        });

        var audio = document.getElementById('music');
        audio.play();
    }


    //上传图片
    function sub() {
        $('#fi').form('submit',{
            url:'${pageContext.request.contextPath}/album/upload.do',
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

</script>
<div id="dfk" style="background-image:url('${pageContext.request.contextPath}/upload/321.jpg');background-repeat:no-repeat;background-size:100% 100%"></div>
<audio src="${pageContext.request.contextPath}/upload/卢恒宇写给父亲.mp3" controls="controls" preload id="music" hidden>
</audio>
<audio id="mu" preload hidden controls="controls" src=""></audio>
<%--<span onclick="control();">播放/暂停</span>
<span onclick="repeat();">重播</span>
<script>
    function repeat(){
        var audio = document.getElementById('music');
        audio.currentTime = 0;//重新播放
    }
    function control(){
        var audio = document.getElementById('music');
        if(audio!==null){
            if(audio.paused){
                audio.play();// 播放
            }else{
                audio.pause();// 暂停
            }
        }
    }

</script>--%>

</html>