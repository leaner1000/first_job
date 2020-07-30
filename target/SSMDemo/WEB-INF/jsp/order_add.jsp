<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="orderAddForm" class="orderForm" method="post">
        <table cellpadding="5" >
            <tr>
                <td>顾客姓名</td>
                <td>
                    <input id="ordercombobox" class="easyui-combobox" data-options="valueField:'customer_name',textField:'customer_name'" name="customer_name"/>
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td>
                    <input class="easyui-textbox" type="text" name="address" />
                </td>
            </tr>
            <tr>
                <td>电话</td>
                <td>
                    <input class="easyui-textbox" type="text" name="phone_number" />
                </td>
            </tr>
            <tr>
                <td>日期</td>
                <td>
                    <input type="text" name="date" id="orderitemdate" class="easyui-datebox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>运费</td>
                <td>
                    <input class="easyui-textbox" type="text" name="des"/>
                </td>
            </tr>
                <input type="hidden" name="item_id" />
        </table>
        <table class="easyui-datagrid" id="orderitemList" title="订单" style="width:700px;height:250px"
               toolbar="#orderitem" pagination="false"
               fitColumns="true" singleSelect="true">
        </table>
        <div id="orderitem">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="neworderItem()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyorderItem()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="finishorderedit()">完成</a>
        </div>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitorderAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearorderAddForm()">重置</a>

    </div>
</div>
<script>
    function bindorderenterevent(){
        var input_list=$("#orderAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")
        for(i=0;i<input_list.length;i++){
            $(input_list).keydown(function(e){
                    if(e.which==13){
                        $(input_list[input_list.index(e.target)+1]).focus()
                    }
                    if(e.which==106){
                        $(input_list[input_list.index(e.target)-1]).focus()
                        return false;
                    }
                }
            )
        }
    }
    $(function (){
        setTimeout(bindorderenterevent,10)

        $("#ordercombobox").combobox({
            mode:'remote',
            url:'customer/autocomplete',
            method:'get',
            onSelect:function (record) {
                console.log(record);
                $("#orderAddForm").form('load',{address:record.address,phone_number:record.phone_number})
            }
        })
        $("#orderitemList").datagrid({
            high:"auto",
            columns:[[
                {field:'cloth_id',title:"货号",editor:{type:'combobox',options:{required:true, mode:'remote',url:'cloth/autocomplete',valueField:'cloth_id',textField:'cloth_id',method:'get',onSelect:function(record){
                                console.log(record)
                                var row = $('#orderitemList').datagrid('getSelected');
                                var rowIndex = $('#orderitemList').datagrid('getRowIndex',row);//获取行号

                                $.ajax({
                                    type:"POST",
                                    url:"cloth/default_price",
                                    data:JSON.stringify({cloth_id:record.cloth_id,custom_name:$("#ordercombobox").combobox('getValues')[0]}),
                                    processData: false,  // 不处理数据
                                    contentType: "application/json",   // 不设置内容类型
                                    success:function(data){
                                        var editors =$('#orderitemList').datagrid("getEditors",rowIndex);
                                        console.log(data)
                                        console.log(editors)
                                        if(data.status==200){
                                            $(editors[11].target).val(data.msg)
                                        }else{
                                            $(editors[11].target).val(record.price)
                                        }
                                    }
                                })
                                $('.combo-panel').hide()
                            }}},width:50},
                {field:"color",title:"颜色",editor:{type:'text',options:{required:true}},width:50},

                {field:"s",title:"S",editor:{type:'text'},width:25},
                {field:"m",title:"M",editor:{type:'text'},width:25},
                {field:"l",title:"L",editor:{type:'text'},width:25},
                {field:"xl",title:"XL",editor:{type:'text'},width:25},
                {field:"xl2",title:"2XL",editor:{type:'text'},width:25},
                {field:"xl3",title:"3XL",editor:{type:'text'},width:25},
                {field:"xl4",title:"4XL",editor:{type:'text'},width:25},
                {field:"xl5",title:"5XL",editor:{type:'text'},width:25},
                {field:"other",title:"其他",editor:{type:'text'},width:25},
                {field:"num",title:"总数",formatter:function(value,row,index){
                        row.num= (isNaN(parseInt(row.s))?0:parseInt(row.s))+
                            (isNaN(parseInt(row.m))?0:parseInt(row.m))+
                            (isNaN(parseInt(row.l))?0:parseInt(row.l))+
                            (isNaN(parseInt(row.xl))?0:parseInt(row.xl))+
                            (isNaN(parseInt(row.xl2))?0:parseInt(row.xl2))+
                            (isNaN(parseInt(row.xl3))?0:parseInt(row.xl3))+
                            (isNaN(parseInt(row.xl4))?0:parseInt(row.xl4))+
                            (isNaN(parseInt(row.xl5))?0:parseInt(row.xl5))+
                            (isNaN(parseInt(row.other))?0:parseInt(row.other));
                        return row.num;
                    }},
                {field:"single",title:"单价",editor:{type:'text',options:{required:true,precision:1}},width:50},
                {field:'total',title:"总价",formatter:function(value,row,index){
                    row.total=row.s*row.single+row.m*row.single+row.l*row.single+row.xl*row.single+row.xl2*row.single+row.xl3*row.single+row.xl4*row.single+row.xl5*row.single+row.other*row.single;
                    return row.s*row.single+row.m*row.single+row.l*row.single+row.xl*row.single+row.xl2*row.single+row.xl3*row.single+row.xl4*row.single+row.xl5*row.single+row.other*row.single;
                    }},
                {field:"des",title:"备注",editor:{type:'text'},width:150},
                {field:"status",hidden:true}
            ]],
            onSelect:function (rowIndex, rowData) {
            $("#orderitemList").datagrid('beginEdit',rowIndex);
                bindorderenterevent();
        }
    });
        $(".combo").click(function(){
            $(this).prev().combobox("showPanel");
        })
    });
    function finishorderedit(){
        for(i in $("#orderitemList").datagrid('getRows')){
            $("#orderitemList").datagrid('endEdit',i);
            $("#orderitemList").datagrid('getRows')[i].date=$("#orderitemdate").datebox('getText');
            $("#orderitemList").datagrid('getRows')[i].status='complete'
        }
    }
    function neworderItem(){
        $("#orderitemList").datagrid('appendRow',{});
        $("#orderitemList").datagrid('selectRow', $("#orderitemList").datagrid('getRows').length-1);
        bindorderenterevent();
    }
    function destroyorderItem(){
        var row = $("#orderitemList").datagrid('getSelected');
        if (row!=null){
            var index = $("#orderitemList").datagrid('getRowIndex',row);
            $.messager.confirm('提示','是否要撤销记录?',function(r){
                if (r){
                    $("#orderitemList").datagrid('deleteRow',index);
                }
            });
        }
        bindorderenterevent();
    }
    function submitorderAddForm(){
        finishorderedit();
        //有效性验证
        if(!$('#orderAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        var l=[];
        for(i in $("#orderitemList").datagrid('getData').rows){
            var o=new Object()
            o.single=$("#orderitemList").datagrid('getData').rows[i].single;
            o.cloth_id=$("#orderitemList").datagrid('getData').rows[i].cloth_id;
            o.color=$("#orderitemList").datagrid('getData').rows[i].color;
            o.des=$("#orderitemList").datagrid('getData').rows[i].des;
            o.date=$("#orderitemList").datagrid('getData').rows[i].date;
            o.status=$("#orderitemList").datagrid('getData').rows[i].status;
            if($("#orderitemList").datagrid('getData').rows[i].s!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="s"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].s;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].m!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="m"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].m;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].l!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="l"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].l;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].xl!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="xl"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].xl;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].xl2!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="2xl"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].xl2;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].xl3!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="3xl"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].xl3;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].xl4!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="4xl"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].xl4;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].xl5!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="5xl"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].xl5;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
            if($("#orderitemList").datagrid('getData').rows[i].other!=0){
                tmp=JSON.parse(JSON.stringify(o))
                tmp.size="other"
                tmp.num=$("#orderitemList").datagrid('getData').rows[i].other;
                tmp.total=tmp.num*tmp.single;
                l.push(tmp)
            }
        }
        $.ajax({
            type:"POST",
            url:"orderitem/insert",
            data:JSON.stringify(l),
            processData: false,  // 不处理数据
            contentType: "application/json",   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $("#orderAddForm").form("load", {item_id:data.msg});
                    $.ajax({type:"POST",url:"order/insert",data:$("#orderAddForm").serialize()});
                    $.messager.alert('提示','新增成功!');
                    clearorderAddForm();
                    $("#orderAddWindow").window('close');
                    $("#orderList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })
    }

    function clearorderAddForm(){
        $('#orderAddForm').form('reset');
        while($("#orderitemList").datagrid('getRows').length>0){
            $("#orderitemList").datagrid('deleteRow',0);
        }
    }
</script>