<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.CityInformation" %><%--
  Created by IntelliJ IDEA.
  User: mrjiao
  Date: 2018/3/29
  Time: 01:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<CityInformation> cities = (ArrayList) request.getAttribute("cities");
    pageContext.setAttribute("cities", cities);
%>

<head>
    <title>${cities[0].name}查询结果展示</title>
    <script src="echarts.common.min.js"></script>
    <link rel="stylesheet" href="layui.css">
    <style type="text/css">
        body {
            background-color: #f3f3f3;
        }
        h2 {
            text-align: center;
            margin: 30px auto;
        }
        #main {
            margin: 20px auto;
            height:300px;
        }
        #infoTable {
            margin: 20px auto;
            width: 80%;
        }
    </style>
</head>
<body>
    <div id="main"></div>
    <h2>${cities[0].name}历时30天空气质量日数据</h2>
    <table class="layui-table" id = "infoTable">
        <colgroup>
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>序号</th>
            <th>日期</th>
            <th>城市</th>
            <th>AQI指数</th>
            <th>主要污染物</th>
            <th>空气质量级别</th>
        </tr>
        </thead>
        <tbody>
        <%--此处可以进一步修改--%>
        <%
            for (int i = 0; i < cities.size(); ++i) {
                out.write(i);
                out.write("<tr>");
                out.write("<td>" + (i + 1) + "</td>");
                out.write("<td>" + cities.get(i).getRecordDate() + "</td>");
                out.write("<td>" + cities.get(i).getName() + "</td>");
                out.write("<td>" + cities.get(i).getAQI() + "</td>");
                out.write("<td>" + cities.get(i).getPollutant() + "</td>");
                out.write("<td>" + cities.get(i).getAirQuality() + "</td>");
                out.write("</tr>");
            }
        %>

        </tbody>
    </table>
</body>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title: {
            left : 'center',
            text: '${cities[0].name}' + '历时30天空气质量指数折线图',
        },
        xAxis: {
            type: 'category',
            data: ["${cities[0].recordDate}", "${cities[4].recordDate}", "${cities[8].recordDate}",
                "${cities[12].recordDate}", "${cities[16].recordDate}", "${cities[20].recordDate}",
                "${cities[24].recordDate}", "${cities[28].recordDate}"]
        },
        yAxis: {
            type: 'value',
            show: true,
            name : 'AQI指数'
        },
        series: [{
            data: ["${cities[0].AQI}", "${cities[4].AQI}", "${cities[8].AQI}", "${cities[12].AQI}",
                "${cities[16].AQI}", "${cities[20].AQI}", "${cities[24].AQI}", "${cities[28].AQI}"],
            type: 'line'
        }]
    };
    myChart.setOption(option);
</script>
</html>
