<%@page contentType="text/html; utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%--引入goeasy的JS文件--%>
<%--<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>--%>

<%--<script type="text/javascript" src="${pageContext.request.contextPath}/back/static/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/back/echarts/echarts.min.js"></script>--%>

<script type="text/javascript">
</script>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="tongji" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('tongji'));

   /* //接收（订阅）消息
    var goEasy = new GoEasy({
        appkey: "BS-4c712d6bae55453cb064d0db84bf358b"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            //将json格式转换成js对象
           var cdata = $.parseJSON(message.content);
*/
    /*});*/
    $.ajax({
       url:'${pageContext.request.contextPath}/userinfo/findtime.do',
       dataType:'JSON',
       type:'post',
       success:function (data) {
           // 指定图表的配置项和数据
           var option = {
               title: {
                   text: '驰名法州用户记录'
               },
               tooltip: {},
               legend: {
                   data:['用户注册']
               },
               xAxis: {
                   data: ["近一周","近二周","近三周","近三周","近二月","近四月","近八月","近一年"]
               },
               yAxis: {},
               series: [{
                   name: '用户注册',
                   type: 'bar',
                   data: data.counts.data
               }]
           };
           // 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);
       }

    });



</script>