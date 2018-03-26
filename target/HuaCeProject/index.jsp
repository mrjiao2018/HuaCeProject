<%--
  Created by IntelliJ IDEA.
  User: mrjiao
  Date: 2018/3/24
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import ="java.*, vo.CityInformation"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.CityInformation" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
    <meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
    <title>百度地图API自定义地图</title>
    <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4rKgAEhmkiTqU9Ve2MHodRXHyXcG5SBH"></script>
    <script src="echarts.common.min.js"></script>
    <style type="text/css">
        body{
            background-color: aliceblue;
        }
        .container {
            width:1000px;
            margin: 20px auto;
        }
        .leftBar {
            float: left;
        }
        .rightBar {
            height: 250px;
            width: 600px;
            float: right;
            /*border: 1px solid blue;*/
        }
        input{
            height: 30px;
            width: 200px;
            border: none;
            border-radius: 5px;
            margin: 10px auto;
            font-size: 12px;
        }
        .submitBtn{
            font-size: 14px;
        }
        .submitBtn:hover {
            background-color: #ffb;
        }
        #map {
            height: 700px;
            margin-top:20px;
            clear: both;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="leftBar">
        <h2 class = "header">全国省会城市污染状况查询</h2>
        <p class = "header inputHelp">请输入省会城市名称进行查询</p>
        <div class = "header inputBox">
            <form action="/test" method = "get">
                <input type="text" name = "city" class = "inputCity"><br>
                <input type="submit" name = "city" value = "查询" class = "submitBtn">
            </form>
        </div>
    </div>
    <div class = "rightBar" id = "main"></div>
    <div id="map"></div>
</div>
</body>
<script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        //setMapEvent();//设置地图事件
        //addMapControl();//向地图添加控件
        addMapOverlay();//向地图添加覆盖物
    }
    function createMap(){
        map = new BMap.Map("map");
        map.centerAndZoom(new BMap.Point(108.06,36.07),5);
    }
    function setMapEvent(){
        map.enableScrollWheelZoom();
        map.enableKeyboard();
        map.enableDragging();
        map.enableDoubleClickZoom()
    }
    function addClickHandler(target,window){
        target.addEventListener("click",function(e){
            target.openInfoWindow(window);
        });
    }
    function addMapOverlay(){

    }


    var map;
    initMap();

    //向地图添加控件
    function addMapControl(){
        var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
        map.addControl(scaleControl);
        var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
        map.addControl(navControl);
        var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
        map.addControl(overviewControl);
    }

    //信息接受及展示
    function infoReceive() {
        <%--<%--%>
            <%--ArrayList list = (ArrayList)request.getAttribute("cities");--%>
            <%--ArrayList AQIs = new ArrayList();--%>
            <%--ArrayList dates=new ArrayList();--%>
            <%--ArrayList pollutions=new ArrayList();--%>
            <%--ArrayList airQualitys=new ArrayList();--%>
            <%--for(int i = 0; i < list.size(); i+=4) {--%>
                <%--CityInformation city = (CityInformation) list.get(i);--%>
                <%--AQIs.add(Integer.parseInt(city.getAQI()));--%>
                <%--dates.add(city.getRecordDate());--%>
                <%--pollutions.add(city.getPollutant());--%>
                <%--airQualitys.add(city.getAirQuality());--%>
            <%--}--%>
        <%--%>--%>
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title: {
                text: '北京市历时30天空气质量指数折线图（AQI指数）'
            },
            xAxis: {
                type: 'category',
                <%--data: [<%=dates.get(0)%>, <%=dates.get(1)%>, <%=dates.get(2)%>, <%=dates.get(3)%>, <%=dates.get(4)%>, <%=dates.get(5)%>, <%=dates.get(6)%>]--%>
                data: ['02-24', '02-28', '03-04', '03-08', '03-12', '03-16', '03-20']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                <%--data: [<%=AQIs.get(0)%>, <%=AQIs.get(1)%>, <%=AQIs.get(2)%>, <%=AQIs.get(3)%>, <%=AQIs.get(4)%>, <%=AQIs.get(5)%>, <%=AQIs.get(6)%>],--%>
                data: [62, 117, 129, 63, 195, 56, 46],
                type: 'line'
            }]
        };
        myChart.setOption(option);
    }
    infoReceive();

</script>
</html>

