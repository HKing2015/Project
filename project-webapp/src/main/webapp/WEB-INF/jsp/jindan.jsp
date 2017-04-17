<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>砸金蛋</title>
	<script src="http://106.38.226.66/operative/wap/js/base/mobile-util.js"></script>
	<meta name="Keywords" content=""/>
	<meta name="Description" content=""/>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-title" content="乐视云">
	<link rel="apple-touch-icon" href="http://i3.letvimg.com/lc05_lecloud/201601/12/10/21/favicon.ico" type="image/vnd.microsoft.icon">
	<link rel="shortcut icon" href="http://i3.letvimg.com/lc05_lecloud/201601/12/10/21/favicon.ico" type="image/vnd.microsoft.icon">
    <link rel="stylesheet" href="http://106.38.226.66/operative/wap/css/core.css">
    <link rel="stylesheet" href="http://106.38.226.66/operative/wap/css/modify.css">

</head>
<body class="cj-03-bg">
    <div class="cj-03 font-16">
        <img src="http://106.38.226.66/operative/wap/images/light.png" alt="" class="bg-light">   
        <div class="cj-title">
            <p>${activity.acTitle}</p>
        </div>
        <a href="4-zhongjiangjilu.html" title="" class="cj-common-btn-yellow mg-center ui-mt-10">我的奖品</a>    
        <div class="cj-egg-wrap">
            <div class="cj-egg-bg mg-center zoomIn animated">
                <a href="#" title="" class="cj-common-btn-red-y mg-center ui-mt-10 btn-start">开始</a> 
            </div>
            <div class="cj-egg-point hide">
            </div>
            <div class="egg-open-mask hide"></div>
            <div class="egg-cuizi wobble animated"></div>
        </div>
        <div class="cj-content font-12 color-fff">
            <p class="cj-date text-center cj-common-box-bg ui-mt-10">
                活动时间：
                <fmt:formatDate value="${activity.acStartTime}" type="both"/>
                --
                <fmt:formatDate value="${activity.acEndTime}" type="both"/>
            </p>
            <h2 class="style-02"><span><img src="http://i1.letvimg.com/lc07_lecloud/201703/09/16/10/hd-03.png" alt=""></span></h2>
            <ul class="cj-ruler cj-common-box-bg ui-mt-10">
                <li>${activity.acDesc}</li>
            </ul>
            
            <!-- 幸运朋友占位符 -->
            <div id="luckyVueDom"></div>
        </div>    
    </div>

    <!-- 提示、中奖信息，俩占位符 -->
    <div id="tipDom"></div>
    <div id="dialogDom"></div>

    <script src="http://106.38.226.66/operative/wap/js/base/base.js"></script>
    <script src="http://106.38.226.66/operative/wap/dist/js/vendor.js"></script>
    <script src="http://106.38.226.66/operative/wap/js/abc.js"></script>
    <script>
        __loadjs('jindan')
    </script>
    
</body>
</html>