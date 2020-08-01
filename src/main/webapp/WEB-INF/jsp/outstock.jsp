<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="outstockList" title="列表"
       singleSelect="false" collapsible="true" pagination="true" rownumbers="true"
       url="/outstock/page" method="post" pageSize="50" fitColumns="true" toolbar="#tooloutstock">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="out_stock_id" data-options="width:150">
            id
        </th>
        <th field="cloth_id" data-options="width:150">
            货号
        </th>
        <th field="picture_path" formatter="formatpic" data-options="width:150">
            图片
        </th>

        <th field="s" data-options="width:150">
            s
        </th>
        <th field="m" data-options="width:150">
            m
        </th>
        <th field="l" data-options="width:150">
            l
        </th>
        <th field="xl" data-options="width:150">
            xl
        </th>
        <th field="xl2" data-options="width:150">
            2xl
        </th>
        <th field="xl3" data-options="width:150">
           3xl
        </th>
        <th field="xl4" data-options="width:150">
            4xl
        </th>
        <th field="xl5" data-options="width:150">
            5xl
        </th>
        <th field="others" data-options="width:150">
            others
        </th>
        <th field="des" formatter="formataudio" data-options="width:300">
            备注
        </th>
    </tr>
    </thead>
</table>
<div id="tooloutstock">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newoutstock()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editoutstock()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyoutstock()">删除</a>

    <div id="search_custom" style="float: right;">
        <input id="search_text_outstock" class="easyui-searchbox"
               data-options="searcher:doSearch_out_stock_id,prompt:'请输入...',menu:'#menu_outstock'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_outstock" style="width:120px">
            <div data-options="name:'out_stock_id'">顾客编号</div>
        </div>
    </div>
</div>

<div id="outstockEditWindow" class="easyui-window" title="编辑信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/outstock/edit'"
     style="width:65%;height:80%;padding:10px;">
</div>

<div id="outstockAddWindow" class="easyui-window" title="添加信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/outstock/add'"
     style="width:65%;height:80%;padding:10px;">
</div>

<script>
    function doSearch_out_stock_id(value,name){
        if(value==null||value==''){
            $("#outstockList").datagrid("reload");
        }else{
            $.get("/outstock/"+value,'',function(data){
                if(data!=''){
                    $("#outstockList").datagrid("loadData",{"total":1,"rows":[data]})
                }else{
                    $("#outstockList").datagrid("loadData",{"total":0,"rows":[]})
                }
            })
        }
    }
    function newoutstock() {
        $('#outstockAddWindow').window("open")
    }

    function getoutstockSelectionsIds(){
        var outstockList = $("#outstockList");
        var sels = outstockList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].out_stock_id);
        }
        ids = ids.join(",");
        console.log(ids);
        return ids;
    }

    function editoutstock(){
        var ids = getoutstockSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一条记录才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一条记录!');
            return ;
        }

        $("#outstockEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#outstockList").datagrid("getSelections")[0];
                $("#outstockEditForm").form("load", data);
            }
        }).window("open");

    }
    function destroyoutstock(){
        var ids = getoutstockSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/outstock/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','删除记录成功!',undefined,function(){
                            $("#outstockList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }
</script>