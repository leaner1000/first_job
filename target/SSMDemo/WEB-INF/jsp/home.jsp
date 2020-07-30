
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>服装管理系统</title>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="/js/locale/easyui-lang-zh_CN.js"></script>
    <script src="js/recorder.js"></script>
</head>
<body class="easyui-layout">
<div class="divNorth" style="height:100px;" data-options="region:'north'" >
    <a id="loginout" onclick="login()" href="javascript:void(0);">登录</a>
</div>
<div class="divWest" style="width: 200px" data-options="region:'west'">
    <div class="easyui-accordion" style="padding:0px;">
        <div title="信息管理">
            <ul id="store" class="easyui-tree">
                <li data-options="attributes:{'url':'/cloth'}">商品管理</li>
                <li data-options="attributes:{'url':'/order'}">订单管理</li>
                <li data-options="attributes:{'url':'/outstock'}">缺货管理</li>
                <li data-options="attributes:{'url':'/cancel'}">退货管理</li>
                <li data-options="attributes:{'url':'/customer'}">客户管理</li>

            </ul>
        </div>
        <div title="数据统计">
            <ul id="stat" class="easyui-tree">
                <li data-options="attributes:{'url':'/countamount'}">每日统计</li>
                <li data-options="attributes:{'url':'/historysummary'}">历史数据</li>
            </ul>
        </div>
        <div title="系统管理">
            <ul id="system" class="easyui-tree">
                <li data-options="attributes:{'url':'/user'}">用户管理</li>
            </ul>
        </div>

    </div>
</div>

<div id="LoginWindow" class="easyui-window" title="登录"
     data-options="modal:true,closed:true,resizable:true"
     style="width:auto;height:auto;padding:10px;">
    <form id="loginForm" method="post">
        <table>
            <tr>
                <td>
                    <table>
                        <tr><td>用户名</td><td><input  class="easyui-validatebox"   id="username" name="username" type="text"/></td><td></td></tr>
                        <tr><td>密 码</td><td><input  class="easyui-validatebox"  id="password" name="password" type="password"></td><td></td></tr>
                        <%--<tr><td>角 色</td><td><select name="role"> <option value="1">普通用户</option><option value="2">管理员</option></select> </td></tr>--%>
                        <div style="text-align: center;color: red;" id="showMsg"></div>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="content" region="center" title="" style="padding:5px;">
    <div id="tabs" class="easyui-tabs" >
        <div title="首页" style="padding:20px;"></div>
    </div>
</div>
</body>
<script>
    function formataudio(val,row){
        if (val != null&&val!=''){
            return '<audio src="' + val + '"'+'controls="true"'+'>图片</audio>'
        }
    }
    function formatpic(val,row) {
        if (val != null&&val!=''){
            return '<a href="' + val + '"'+'target="_blank"'+'>图片</a>'
        }
    }
    function formatred(val,row) {
        console.log(val)
        if (val != null&&val!=''){
            if(parseInt(val)>30){
                return '<p style="color:#F00">'+val+'</p>'
            } else{
                return '<p>'+val+'</p>'
            }
        }
    }
    function getCookie(cname) { var name = cname + "="; var ca = document.cookie.split(';'); for(var i=0; i<ca.length; i++) { var c = ca[i].trim(); if (c.indexOf(name)==0) return c.substring(name.length,c.length); } return ""; }
    $.prototype.serializeObject = function () {
        var a,o,h,i,e;
        a = this.serializeArray();
        o={};
        h=o.hasOwnProperty;
        for(i=0;i<a.length;i++){
            e=a[i];
            if(!h.call(o,e.name)){
                o[e.name]=e.value;
            }
        }
        return o;
    }

    $(function () {
        $("#LoginWindow").dialog({
            title : '登录',
            backcolor:'#00f',
            iconCls : 'icon-lock',
            width : "auto",
            height : "auto",
            closable : true,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            open:false,
            buttons : [ {
                text : '登录',
                iconCls : 'icon-ok',
                handler:function(){
                    doLogin();
                }
            } ]
        });
        $.ajax({
            method:"post",
            url:"checklogin",
            success:function (data) {
                if(data){
                    $("#loginout").text("注销");
                    document.cookie="username="+$("input[name='username']").val();
                    $("#loginout").attr("onclick","logout()");
                }else{
                    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                }
            }
        })
    })

    function doLogin() {
        if($("input[name='username']").val()=="" || $("input[name='password']").val()==""){
                $("#showMsg").html("用户名或密码为空，请输入");
                $("input[name='login']").focus();}
            else{
                $.ajax({
                    method:"post",
                    data:$("#loginForm").serialize(),
                    url:"login",
                    success:function (data) {
                        if(data.status==200){
                            $("#LoginWindow").window("close");
                            $("#loginout").attr("onclick","logout()");
                            document.cookie="username="+$("input[name='username']").val();
                            $("#loginForm").form('reset');
                            $("#loginout").text("注销");
                        }else{
                            $("#showMsg").html("用户名或密码错误");
                        }
                    }
                })
        }
    }
    function logout(){
        $.post({url:"logout",
            success:function(){
                $("#loginout").text("登录");
                $("#loginout").attr("onclick","login()");
                document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }})
    }
    function login(){
        $('#LoginWindow').window("open");
    }

    $(function() {
        $('#store').tree({
            onClick:function(node) {
                if ($('#store').tree('isLeaf'), node.target) {
                    var tab = $('#tabs')
                    var tab2 = tab.tabs("getTab", node.text);
                    if (tab2) {
                        tab.tabs("select", node.text);
                    } else {
                        tab.tabs('add', {
                            title: node.text,
                            href: node.attributes.url,
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });
        $('#stat').tree({
            onClick:function(node) {
                if ($('#store').tree('isLeaf'), node.target) {
                    var tab = $('#tabs')
                    var tab2 = tab.tabs("getTab", node.text);
                    if (tab2) {
                        tab.tabs("select", node.text);
                    } else {
                        tab.tabs('add', {
                            title: node.text,
                            href: node.attributes.url,
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });
        $('#system').tree({
            onClick:function(node) {
                if ($('#store').tree('isLeaf'), node.target) {
                    var tab = $('#tabs')
                    var tab2 = tab.tabs("getTab", node.text);
                    if (tab2) {
                        tab.tabs("select", node.text);
                    } else {
                        tab.tabs('add', {
                            title: node.text,
                            href: node.attributes.url,
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });
    });
</script>
</html>
