<%@page contentType="text/html;charset=utf-8" isELIgnored="false" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/KindEditor/kindeditor-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/KindEditor/kindeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/KindEditor/lang/zh_CN.js"></script>
<div>
    <form id="articleForm" method="post">
        标题：<input class="easyui-textbox" name="title" data-options="required:'true',iconCls:'icon-lock'"/>
        作者：<input id="mycombobox" name="gurn.id">
        <a href="javascript:void(0)"  class="easyui-linkbutton" id="articleSub" data-options="iconCls:'icon-ok'">提交</a>
        <textarea id="editor" name="content" class="editor" style="width: 700px;"></textarea>
    </form>
    </form>
</div>
<script type="text/javascript">
    $(function () {

        $("#mycombobox").combobox({

            width:200,
            //获取数据
            url:"${pageContext.request.contextPath}/editor/gurn.do",
            valueField:"id",//向服务器发送的ID的值
            textField:"upname",//填充到页面的属性的值
            groupFormatter:function (group) {
                return '<span style="color:darkred">'+group+'</span>';
            },
        });

        $("#mycombobox").combobox("select","1");

        //监听按钮  控制表单提交
        $("#articleSub").click(function () {
            $("#articleForm").form('submit',{
                url:'${pageContext.request.contextPath}/editor/addessay.do',
                success:function (data) {
                    console.log(data);
                    if(data=="ok"){
                    $.messager.show({
                        title : '提醒',
                        msg : '发送成功',
                        timeout : 2000,
                        //消失的方式有三种可选值 slide,fade,show
                        showType : 'show',
                    });
                    }else {
                        $.messager.show({
                            title : '提醒',
                            msg :'发送失败' ,
                            timeout : 2000,
                            //消失的方式有三种可选值 slide,fade,show
                            showType : 'show',
                        });
                    }
                    $("#articleForm").form('reset');
                }
            })
        })

        //富文本编辑器
        KindEditor.create("#editor", {
            height: "400px",
            themeType:"simple",//修改主题

            items: [
                "source",  "preview","fullscreen","clearhtml","|",
                "undo", "redo","|",
                "copy", "paste","plainpaste", "wordpaste", "|",
                "justifycenter", "justifyright",
                "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent",  "|",
                "formatblock", "fontname", "fontsize",
                "forecolor", "hilitecolor", "bold",
                "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image","table", "hr", "emoticons", "baidumap", "pagebreak",
                "anchor", "link", "unlink"
            ],
            langType: 'zh_CN',
            syncType: "form",
            filterMode: false,
            pagebreakHtml: '<hr class="pageBreak" \/>',
            allowFileManager: true,
            filePostName: "file",
            fileManagerJson:"${pageContext.request.contextPath }/editor/findtuo.do",//查询所有图片
            uploadJson:"${pageContext.request.contextPath }/editor/add.do",//上传图片
            extraFileUploadParams: {
                token: getCookie("token")
            },
            afterChange: function() {
                this.sync();
            }
        });
    });
</script>

