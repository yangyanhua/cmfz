
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function() {

        //定义数据表格
        $('#userdg').datagrid({
            loadMsg: "Loading..",//远程加载展示字段
            showFooter: true,
            fitColumns: true,//自动展开/收缩列的大小，以适应网格的宽度
            //title : 'PictMessages',//标题
            striped: true,//展示斑马线
            remoteSort: false,
            ctrlSelect: true,//可多选
            rownumbers: true,// 显示行号
            selectOnCheck: true,//选中行是否可以选中框
            checkOnSelect: true,//如果为true，当用户点击行的时候该复选框就会被选中或取消选中。
            //如果为false，当用户仅在点击该复选框的时候才会呗选中或取消
            //singleSelect : false,//如果为true，则只允许选择一行
            columns: [[{
                field: 'ckbox',
                checkbox: true,
            },
                {
                    field: 'id',
                    title: '编号',
                    width: 100,
                    height: 100,
                    align: 'center',
                    sortable: true,//配合remoteSort:false使用
                },

                {
                    field: 'name',
                    title: '真实姓名',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'fhname',
                    title: '法名',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'email',
                    title: '邮箱',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'password',
                    title: '用户密码',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'phone',
                    title: '联系方式',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'sex',
                    title: '用户性别',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'site',
                    title: '用户地址',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'headurl',
                    title: '用户头像',
                    width: 200,
                    height: 100,
                    align: 'center',
                    formatter:function(val,row,index){
                        return "<img src='${pageContext.request.contextPath}/"+val+"' style='width:200px;height:100px'/>"
                    }
                },

                {
                    field: 'signature',
                    title: '用户签名',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'addtime',
                    title: '用户注册时间',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'logintime',
                    title: '用户登陆时间',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'master_id',
                    title: '上师编号',
                    width: 100,
                    height: 100,
                    align: 'center',
                    formatter:function(val,row,index){
                       return row.guru.id;
                    }

                },

                {
                    field: 'mastername',
                    title: '上师姓名',
                    width: 100,
                    height: 100,
                    align: 'center',
                    formatter:function(val,row,index){
                        return row.guru.upname;
                    }
                },

                {
                    field: 'qq',
                    title: '用户QQ号',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'weix',
                    title: '用户微信号',
                    width: 100,
                    height: 100,
                    align: 'center',
                },

                {
                    field: 'state',
                    title: '用户状态',
                    width: 100,
                    height: 100,
                    align: 'center',
                },
            ]
            ],

            //datagrid自定义工具
            toolbar:[
                {
                    text:'自定义导出',
                    iconCls:'icon-arrow_down',
                    handler:function(){
                        $('#dai').dialog({
                            title:'自定义导出',
                        })
                    }
                },
                {
                    text:'自定义导入',
                    iconCls:'icon-arrow_down',
                    handler:function(){
                        $('#importDialog').dialog('open');
                        //$('#importDialog').dialog('refresh');
                    }
                }
            ],
            url:'${pageContext.request.contextPath}/userinfo/Paging.do',
            //加入分页控件
            pagination : true,//底部展示工具栏
            pagePosition : 'bottom',//分页栏的位置
            pageNumber : 1,//默认展示第几页
            pageSize : 5,//默认每页展示的条数
            pageList : [ 5, 10, 15, 20 ],//可选择每页展示数据
        });
    });

    $('#importDialog').dialog({
        closed:true,
        title:'导入用户信息',
        buttons:[
            {
                text:'确认',
                iconCls:'icon-ok',
                handler:function(){
                    $('#importForm').form('submit', {
                        url:'${pageContext.request.contextPath}/userinfo/insertAppUser.do',
                        onSubmit:function(){
                            return $('importForm').form('validate');
                        },
                        success:function(result){
                            $('#userTable').datagrid('reload');
                            $('#importDialog').dialog('close');
                            alert(result);
                        }
                    });

                }
            },
            {
                text:'取消',
                iconCls:'icon-cancel',
                handler:function(){
                    $('#importDialog').dialog('close');
                }
            }

        ]
    });


    //提交
    function sub() {

    }

    //重置
    function remove() {

    }


    //定义树状表格
    $('#cc').combotree({
        url: '${pageContext.request.contextPath}/userFiledsData.json',
        required: true,
        checkbox:true,//定义是否在每一个节点之前都显示复选框
        onlyLeafCheck:false,//定义是否只在末级节点之前显示复选框
        multiple:true,//是否支持多选
        animate:true,
    });
    //导出
    $('#btn').click(function () {
        //获取combotree的选中信息, 提交form表单
        var content = $('#cc').combotree("getText");
        console.log('content =    '+content);
        //获取组件值的数据
        var vals = $('#cc').combotree("getValues");
        console.log('vals=    '+vals);
        var ss = '';
        $.each(vals,function (index,filed) {
            if(index<vals.length-1){
                ss += filed+",";
            }else{
                ss += filed;
            }
        });
        console.log('ss =     '+ss);
        $('#fff').form("submit",{
            url:'${pageContext.request.contextPath}/poi/export.do',
            queryParams:{"content":content,"fields":ss},
            success:function () {
                alert('Join success');
                $('#dai').dialog('close');
            },
        })
    })
</script>
<table id="userdg" class="easyui-datagrid">
</table>

<!--自定义导出-->
<div id="dai" style="display: none;background-color: #bababa">
    <form id="fff" method="post">
        <select id="cc" class="easyui-combotree" style="width:200px;"
                data-options="url:'get_data.php',required:true"></select>
        <a id="btn" href="javascript:void (0)" class="easyui-linkbutton" data-options="iconCls:'icon-arrow_down'">导出</a>
    </form>
</div>
<!--自定义导出结束...-->


<!--自定义导入开始-->
<div id="importDialog" style="height:350px; width: 350px;"></div>
<div style="text-align: center;">
    <form id="importForm" class="easyui-form" method="post" enctype="multipart/form-data">
        <br/>
        <input type="file" name="userTable" data-options="required:true"/>
    </form>
</div>
<!--自定义导入结束....-->

<!-- 提交按钮 -->
<div id="tool" style="display: none">
    <a class="easyui-linkbutton" href="javascript:void(0)" id="sub"
       data-options="iconCls:'icon-ok',onClick:sub">提交</a> <a
        class="easyui-linkbutton" href="javascript:void(0)" id="remove"
        data-options="iconCls:'icon-remove',onClick:remove">重置</a>
</div>
<hr/>


















































































