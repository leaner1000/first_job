<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="easyui-datagrid" id="historyList" title="列表"
       singleSelect="false" collapsible="true" pagination="false" rownumbers="true"
       method="post" pageSize="10" fitColumns="true" toolbar="#toolbarhistory">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="id" formatter="formatid" data-options="width:150">
            编号
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
        <th field="item_id"  data-options="width:150" hidden="true">
            编号
        </th>
        <th field="status" formatter="formattype"  data-options="width:150">
            类型
        </th>
    </tr>
    </thead>
</table>
<div id="toolbarhistory">
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyhistory()">撤销</a>
    <form id="historyform">
        起始日期: <input name="startTime" class="easyui-datebox" required="true" style="width:150px">
        终止日期: <input name="endTime" class="easyui-datebox" required="true" style="width:150px">
        <a class="easyui-linkbutton" iconCls="icon-search" onclick="submithistory()">统计</a>
    </form>
</div>
<script>
    $(function () {
        $("#historyList").datagrid({
            view: detailview,
            detailFormatter:function(index,row){
                return '<div style="padding:2px"><table class="ddv"></table></div>';
            },
            onExpandRow: function(index,row){
                var suffix,id;
                if(row.order_id!=undefined){
                    suffix='orderitem/';
                    id=row.order_id;
                }else{
                    suffix='cancelitem/';
                    id=row.cancel_id
                }
                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                ddv.datagrid({
                    url:suffix+id,
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
                        $("#historyList").datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        setTimeout(function(){
                            $("#historyList").datagrid('fixDetailRowHeight',index);
                        },0);
                    }
                });
                $("#historyList").datagrid('fixDetailRowHeight',index);
            }
        });

    })
    function formatid(val,row) {
        if(row.order_id!=undefined){
            return '<span>'+row.order_id+'</span>'
        }else{
            return '<span>'+row.cancel_id+'</span>'
        }
    }
    function submithistory(){
        if(!$('#historyform').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        var form=$("#historyform").serialize();
        $.ajax({
            url:"history",
            method:"post",
            data:form,
            success:function(data){
                console.log(data);
                $("#historyList").datagrid('loadData',data);
            }
        })
    }
    function formattype(val,row) {
        console.log(row);
        if(row.order_id!=undefined){
            return '<span style="color: green" >'+'订单'+'</span>'
        }else{
            return '<span style="color: red" >'+'退单'+'</span>'
        }
    }


    function destroyhistory(){
        var orderList = $("#historyList");
        var sels = orderList.datagrid("getSelections");
        var orderlist=[]
        var cancellist=[]
        if(sels.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        for(i in sels){
            if(sels[i].order_id!=undefined){
                orderlist.push(sels[i].order_id)
            }else{
                cancellist.push(sels[i].cancel_id)
            }
        }
        $.messager.confirm('确认','确定撤销记录吗？',function(r){
            if (r){
                if(orderlist.length>0){
                    $.post({url:"/order/delete_batch",method:"post",data:{ids:orderlist.join(",")}, success:function(data){
                        if(data.status != 200) {
                            $.messager.alert('提示', data.msg);
                        }
                    }});
                }
                if(cancellist.length>0){
                    $.post({url:"/cancel/delete_batch",method:"post",data:{ids:cancellist.join(",")}, success:function(data){
                            if(data.status != 200) {
                                $.messager.alert('提示', data.msg);
                            }else{
                                submithistory();
                            }
                    }});
                }

            }
        });
    }
</script>