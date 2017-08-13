<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/common/wechatLibs.jsp"%>
<!doctype html>
<html>
<head>
    <title>我的项目</title>
    <meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    
</head>
<body>
   
    <div class="my-project-list">
        <div class="aui-tab" id="tab">
            <div class="aui-tab-item aui-active"><span>未使用</span></div>
            <div class="aui-tab-item"><span>已使用</span></div>
            <div class="aui-tab-item"><span>已过期</span></div>
        </div>
        <div class="box">
            <ul>
                <li>
                    <img class="fl" src="../images/temp/temp03.jpg">
                    <div class="intro fl">
                        <h3>SN美白 1.0</h3>
                        <p>有效期至：2017-04-20</p>
                    </div>
                    <div class="state">
                        <span>未使用</span>
                    </div>
                </li>
                <li>
                    <img class="fl" src="../images/temp/temp03.jpg">
                    <div class="intro fl">
                        <h3>SN美白 1.0</h3>
                        <p>有效期至：2017-04-20</p>
                    </div>
                    <div class="state">
                        <span>未使用</span>
                    </div>
                </li>
                <li>
                    <img class="fl" src="../images/temp/temp03.jpg">
                    <div class="intro fl">
                        <h3>SN美白 1.0</h3>
                        <p>有效期至：2017-04-20</p>
                    </div>
                    <div class="state">
                        <span>未使用</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/static/wechat/js/aui-tab.js" ></script>
<script type="text/javascript">
    /*切换*/
    var tab = new auiTab({
        element:document.getElementById("tab"),
    });
</script>
</html>