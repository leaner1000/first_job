<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="orderList" title="列表"
       singleSelect="false" collapsible="true" pagination="true" rownumbers="true"
       url="/order/page" method="post" pageSize="50" fitColumns="true" toolbar="#toolbarorder">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="order_id" data-options="width:150">
            订单编号
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
            运费
        </th>
        <th field="item_id"  data-options="width:150" hidden="true">
            编号
        </th>
        <th field="status" formatter="formatstatus"  data-options="width:150">
            状态
        </th>
    </tr>
    </thead>
</table>
<div id="toolbarorder">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="neworder()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyorder()">撤销</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="editorder()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="printorder()">打印</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="confirmpay()">确认支付</a>
    <form id="orderconditionForm">
        客户名:<input name="customer_name" class="easyui-combobox" style="width:150px" id="order_customer" >
        <a class="easyui-linkbutton" iconCls="icon-search" onclick="order_custom()">统计</a>
    </form>
    <div id="search_order" style="float: right;">
        <input id="search_text_order" class="easyui-searchbox"
               data-options="searcher:doSearch_order_id,prompt:'请输入...',menu:'#menu_order'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_order" style="width:120px">
            <div data-options="name:'order_id'">订单编号</div>
        </div>
    </div>
</div>

<div id="orderAddWindow" class="easyui-window" title="添加信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/order/add'"
     style="width:65%;height:80%;padding:10px;">
</div>
<div id="orderEditWindow" class="easyui-window" title="修改信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/order/edit'"
     style="width:65%;height:80%;padding:10px;">8
</div>

<script>
    $(function (){
        $("#order_customer").combobox({
            mode:'remote',
            url:'customer/autocomplete',
            method:'get',
            textField:"customer_name",
            valueField:"customer_name",
        })
    });
    function order_custom() {
        var form=$("#orderconditionForm").serialize();
        $.get("/custom_name",form,function(data){
            if(data!=''){
                $("#orderList").datagrid("loadData",data)
            }else{
                $("#orderList").datagrid("loadData",{"total":0,"rows":[]})
            }
        })
    }
    function formatstatus(val,row){
        if(val=='complete'){
            return '<span style="color: yellowgreen" >未支付</span>'
        }if(val=='cancel'){
            return '<span style="color: red" >已撤销</span>'
        }if(val=='payed'){
            return '<span style="color: green" >已支付</span>'
        }
    }
    $("#orderList").datagrid({
        view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'orderitem/'+row.order_id,
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
                    {field:'des',title:"运费"}                  //应甲方要求备注改为运费
                ]],
                onResize:function(){
                    $("#orderList").datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $("#orderList").datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $("#orderList").datagrid('fixDetailRowHeight',index);
        }
    });

    function doSearch_order_id(value,name){
        console.log(value,name)
        if(value==null||value==''){
            $("#orderList").datagrid("reload");
        }else{
            if(name=='order_id'){
                $.get("/order/"+value,'',function(data){
                    if(data!=''){
                        $("#orderList").datagrid("loadData",{"total":1,"rows":[data]})
                    }else{
                        $("#orderList").datagrid("loadData",{"total":0,"rows":[]})
                    }
                })
            }
        }
    }

    function neworder() {
        $('#orderAddWindow').window("open")
    }
    function unique(arr) {
        arr = arr.sort()
        var arrry= [arr[0]];
        for (var i = 1; i < arr.length; i++) {
            if (arr[i] !== arr[i-1]) {
                arrry.push(arr[i]);
            }
        }
        return arrry;
    }
    function editorder(){
        var ids = getorderSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一条记录才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一条记录!');
            return ;
        }

        $("#orderEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#orderList").datagrid("getSelections")[0];
                $("#orderEditForm").form("load", data);
                $.ajax({
                    method:"post",
                    url:"/orderitem/"+data.order_id,
                    success:function (data) {
                        s=[];
                        for(i in data){
                            s.push(data[i].cloth_id);
                        }
                        s=unique(s)
                        l=[]
                        for(i in s){
                            l.push(new Object())
                        }
                        for(i in data){
                            for(j in s){
                                if(data[i].cloth_id==s[j]){
                                    tmp=j
                                }
                            }
                            console.log(data)
                            l[tmp].cloth_id=data[i].cloth_id;
                            l[tmp].color=data[i].color;
                            l[tmp].single=data[i].single;
                            l[tmp].des=data[i].des;
                            if(data[i].size=="s"){
                                l[tmp].s=data[i].num;
                            }
                            if(data[i].size=="m"){
                                l[tmp].m=data[i].num;
                            }
                            if(data[i].size=="l"){
                                l[tmp].l=data[i].num;
                            }
                            if(data[i].size=="xl"){
                                l[tmp].xl=data[i].num;
                            }if(data[i].size=="2xl"){
                                l[tmp].xl2=data[i].num;
                            }if(data[i].size=="3xl"){
                                l[tmp].xl3=data[i].num;
                            }
                            if(data[i].size=="4xl"){
                                l[tmp].xl4=data[i].num;
                            }
                            if(data[i].size=="5xl"){
                                l[tmp].xl5=data[i].num;
                            }if(data[i].size=="other"){
                                l[tmp].other=data[i].num;
                            }
                            console.log(l[tmp].total,data[i].total)
                            l[tmp].num=(l[tmp].num==undefined?0:l[tmp].num)+data[i].num;
                            l[tmp].total=(l[tmp].total==undefined?0:l[tmp].total)+data[i].total
                            console.log(l[tmp])
                        }
                        for(i in l){
                            $("#orderitemeditList").datagrid('appendRow',l[i]);
                        }
                    }
                })
            }
        }).window("open");
    }
    function getorderSelectionsIds(){
        var sels = $("#orderList").datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].order_id);
        }
        ids = ids.join(",");
        console.log(ids)
        return ids;
    }

    function destroyorder(){
        var ids = getorderSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定撤销ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/order/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','撤销记录成功!',undefined,function(){
                            $("#orderList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }

    function confirmpay(){
        var ids = getorderSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定将ID为 '+ids+' 的记录修改为已支付吗？',function(r){
            if (r){
                $.post("/complete_order",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','状态修改成功!',undefined,function(){
                            $("#orderList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }

    function printorder(){
        var ids = getorderSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一条记录才能打印!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一条记录!');
            return ;
        }
        window.open("/orderpdf/"+ids, "_blank")
    }
</script>
