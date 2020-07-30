<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="cancelList" title="列表"
       singleSelect="false" collapsible="true" pagination="true" rownumbers="true"
       url="/cancel/page" method="post" pageSize="50" fitColumns="true" toolbar="#toolbarcancel">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="cancel_id" data-options="width:150">
            退单编号
        </th>
        <th field="customer_name"  data-options="width:150">
            顾客姓名
        </th>
        <th field="address"  data-options="width:150">
            地址
        </th>
        <th field="phone_number"  data-options="width:150">
            电话
        </th>
        <th field="date"  data-options="width:150">
            日期
        </th>
        <th field="des"  data-options="width:150">
            备注
        </th>
        <th field="cancelitem_id"  data-options="width:150" hidden="true">
            编号
        </th>
        <th field="status" formatter="formatstatus"  data-options="width:150">
            状态
        </th>
    </tr>
    </thead>
</table>
<div id="toolbarcancel">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newcancel()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroycancel()">撤销</a>

    <div id="search_cancel" style="float: right;">
        <input id="search_text_cancel" class="easyui-searchbox"
               data-options="searcher:doSearch_cancel_id,prompt:'请输入...',menu:'#menu_cancel'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_cancel" style="width:120px">
            <div data-options="name:'cancel_id'">退单编号</div>
        </div>
    </div>
</div>

<div id="cancelAddWindow" class="easyui-window" title="添加信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/cancel/add'"
     style="width:65%;height:80%;padding:10px;">
</div>

<script>
    function formatstatus(val,row){
        if(val=='complete'){
            return '<span style="color: green" >已完成</span>'
        }else{
            return '<span style="color: red" >已撤销</span>'
        }
    }
    $("#cancelList").datagrid({
        view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'cancelitem/'+row.cancel_id,
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                columns:[[
                    {field:'cloth_id',title:"货号",width:50},
                    {field:"color",title:"颜色",width:50},
                    {field:"size",title:"尺码",width:50},
                    {field:"num",title:"数量",width:50},
                    {field:"single",title:"单价",width:50},
                    {field:'total',title:"总价"},
                    {field:'des',title:"备注"}
                ]],
                onResize:function(){
                    $("#cancelList").datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $("#cancelList").datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $("#cancelList").datagrid('fixDetailRowHeight',index);
        }
    });

    function doSearch_cancel_id(value,name){
        if(value==null||value==''){
            $("#cancelList").datagrid("reload");
        }else{
            $.get("/cancel/"+value,'',function(data){
                if(data!=''){
                    $("#cancelList").datagrid("loadData",{"total":1,"rows":[data]})
                }else{
                    $("#cancelList").datagrid("loadData",{"total":0,"rows":[]})
                }
            })
        }
    }

    function newcancel() {
        $('#cancelAddWindow').window("open")
    }

    function getcancelSelectionsIds(){
        var cancelList = $("#cancelList");
        var sels = cancelList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].cancel_id);
        }
        ids = ids.join(",");
        console.log(ids)
        return ids;
    }

    function destroycancel(){
        var ids = getcancelSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定撤销ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/cancel/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','撤销记录成功!',undefined,function(){
                            $("#cancelList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }
</script>
