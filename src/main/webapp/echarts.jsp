<%@page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>

<html>
<head>
    <script charset="utf-8" src="${pageContext.request.contextPath}/script/jquery.js"></script>
    <%--第一步:引入ecahrts.js文件--%>
    <script charset="utf-8" src="${pageContext.request.contextPath}/back/echarts/echarts.js"></script>
    <%--引入goeasy的js文件--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
</head>
<body>

<!-- 第二步:为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>


<script type="text/javascript">
    /*初始化init echarts表格*/
    var goEasy = new GoEasy({
//( 只能用来订阅channel来接收消息 )
        appkey: "BS-9e01924eba63476bb9867c974d45ed40"
    });
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            // alert("Channel:" + message.channel + " content:" + message.content);
            //json格式字符串转换js对象
            var cdata = $.parseJSON(message.content);
            console.log('cdata =              ' + cdata);
            console.log('countOne =              ' + cdata.countOne);
            console.log('countTwo =              ' + cdata.countTwo);
            console.log('countThree =              ' + cdata.countThree);
            //设置当前echarts表格的配置
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '实时APP用户注册量'
                },
                tooltip: {},
                legend: {
                    data: ['最近一周APP用户注册量', '最近一个月APP用户注册量', '最近三个月APP用户注册量']
                },
                xAxis: {
                    data: ["最近三个月APP用户注册量"]
                },
                yAxis: {},
                series: [
                    {
                        name: '最近一周APP用户注册量',
                        type: 'bar',
                        data: [cdata.countOne]
                    },
                    {
                        name: '最近一个月APP用户注册量',
                        type: 'bar',
                        data: [cdata.countTwo]
                    },
                    {
                        name: '最近三个月APP用户注册量',
                        type: 'bar',
                        data: [cdata.countThree]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })


    //发送ajax查询数据
    /*   $.ajax({
           url:"{pageContext.request.contextPath}/back/echats1.json",
           dataType:"JSON",
           type:"get",
           success:function (result) {

           }
       });*/


</script>
</body>
</html>



