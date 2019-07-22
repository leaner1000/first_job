<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="customerList" title="列表"
       singleSelect="false" collapsible="true" pagination="true" rownumbers="true"
       url="/customer/page" method="post" pageSize="10" fitColumns="true" toolbar="#toolcustomer">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="customer_id" data-options="width:150">
            顾客编号
        </th>
        <th field="customer_name" data-options="width:150">
            顾客名字
        </th>
        <th field="phone_number" data-options="width:150">
            电话号码
        </th>
        <th field="address" data-options="width:150">
            地址
        </th>
    </tr>
    </thead>
</table>
<div id="toolcustomer">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newcustomer()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editcustomer()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroycustomer()">删除</a>
    <div id="search_custom" style="float: right;">
        <input id="search_text_customer" class="easyui-searchbox"
               data-options="searcher:doSearch_customer_id,prompt:'请输入...',menu:'#menu_customer'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_customer" style="width:120px">
            <div data-options="name:'customer_id'">顾客编号</div>
        </div>
    </div>
</div>

<div id="customerEditWindow" class="easyui-window" title="编辑信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/customer/edit'"
     style="width:65%;height:80%;padding:10px;">
</div>

<div id="customerAddWindow" class="easyui-window" title="添加信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/customer/add'"
     style="width:65%;height:80%;padding:10px;">
</div>

<script>
    function doSearch_customer_id(value,name){
        if(value==null||value==''){
            $("#customerList").datagrid("reload");
        }else{
            $.get("/customer/"+value,'',function(data){
                if(data!=''){
                    $("#customerList").datagrid("loadData",{"total":1,"rows":[data]})
                }else{
                    $("#customerList").datagrid("loadData",{"total":0,"rows":[]})
                }
            })
        }
    }
    function newcustomer() {
        $('#customerAddWindow').window("open")
    }

    function getcustomerSelectionsIds(){
        var customerList = $("#customerList");
        var sels = customerList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].customer_id);
        }
        ids = ids.join(",");
        console.log(ids);
        return ids;
    }

    function editcustomer(){
        var ids = getcustomerSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一条记录才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一条记录!');
            return ;
        }

        $("#customerEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#customerList").datagrid("getSelections")[0];
                $("#customerEditForm").form("load", data);
            }
        }).window("open");

    }
    function destroycustomer(){
        var ids = getcustomerSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/customer/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','删除记录成功!',undefined,function(){
                            $("#customerList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }
</script>