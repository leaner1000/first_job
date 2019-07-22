<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="userList" title="用户列表"
       singleSelect="false" collapsible="true" pagination="true" rownumbers="true"
       url="/user/page" method="post" pageSize="10" fitColumns="true" toolbar="#toolbar10">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="user_id" data-options="width:150">
            用户编号
        </th>
        <th field="role_id" formatter="formatrole" data-options="width:150">
            角色
        </th>
        <th field="username" data-options="width:150">
            用户名
        </th>
        <th field="password" data-options="width:150">
            密码
        </th>
    </tr>
    </thead>
</table>
<div id="toolbar10">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newuser()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edituser()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyuser()">删除</a>
    <div id="search_custom" style="float: right;">
        <input id="search_text_custom" class="easyui-searchbox"
               data-options="searcher:doSearch_user_id,prompt:'请输入...',menu:'#menu_custom'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_custom" style="width:120px">
            <div data-options="name:'user_id'">用户编号</div>
        </div>
    </div>
</div>

<div id="userEditWindow" class="easyui-window" title="编辑用户信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/user/edit'"
     style="width:65%;height:80%;padding:10px;">
</div>

<div id="userAddWindow" class="easyui-window" title="添加用户"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/user/add'"
     style="width:65%;height:80%;padding:10px;">
</div>

<script>
    function doSearch_user_id(value,name){
        if(value==null||value==''){
            $("#userList").datagrid("reload");
        }else{
            $.get("/user/"+value,'',function(data){
                if(data!=''){
                    $("#userList").datagrid("loadData",{"total":1,"rows":[data]})
                }else{
                    $("#userList").datagrid("loadData",{"total":0,"rows":[]})
                }
            })
        }
    }
    function formatrole(val,row) {
        switch (val){
            case 1:
                return '系统管理员'
        }
    }

    function newuser() {
        $('#userAddWindow').window("open")
    }

    function getUserSelectionsIds(){
        var userList = $("#userList");
        var sels = userList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].user_id);
        }
        ids = ids.join(",");
        console.log(ids);
        return ids;
    }

    function edituser(){
        var ids = getUserSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一个用户信息才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个用户信息!');
            return ;
        }

        $("#userEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#userList").datagrid("getSelections")[0];
                $("#userEditForm").form("load", data);
            }
        }).window("open");

    }
    function destroyuser(){
        var ids = getUserSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/user/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','删除成功!',undefined,function(){
                            $("#userList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }
</script>