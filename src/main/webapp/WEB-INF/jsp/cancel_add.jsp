<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="cancelAddForm" class="cancelForm" method="post">
        <table cellpadding="5" >
            <tr>
                <td>顾客姓名</td>
                <td>
                    <input id="cancelcombobox" class="easyui-combobox" data-options="valueField:'customer_name',textField:'customer_name'" name="customer_name"/>
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
                    <input type="text" name="date" id="cancelitemdate" class="easyui-datebox" data-options="required:true">
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td>
                    <input class="easyui-textbox" type="text" name="des"/>
                </td>
            </tr>
            <input type="hidden" name="item_id" />
        </table>
        <table class="easyui-datagrid" id="cancelitemList" title="订单" style="width:700px;height:250px"
               toolbar="#cancelitem" pagination="false"
               fitColumns="true" singleSelect="true">
        </table>
        <div id="cancelitem">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newcancelItem()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroycancelItem()">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="finishcanceledit()">完成</a>
        </div>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitcancelAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearcancelAddForm()">重置</a>

    </div>
</div>
<script>
    function bindcancelenterevent(){
        console.log(456)
        for(i=0;i<$("#cancelAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").length;i++){
            $($("#cancelAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")).keydown(function(e){
                    if(e.which==13){
                        $($("#cancelAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")[$("#cancelAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").index(e.target)+1]).focus()
                    }
                }
            )
        }
    }
    $(function (){
        setTimeout(bindcancelenterevent,10)
        $("#cancelcombobox").combobox({
            mode:'remote',
            url:'customer/autocomplete',
            method:'get',
            onSelect:function (record) {
                console.log(record);
                $("#cancelAddForm").form('load',{address:record.address,phone_number:record.phone_number})
            }
        })
        var data=[{id:"s",text:"s"},{id:"m",text:"m"},{id:"l",text:"l"},{id:"xl",text:"xl"},{id:"2xl",text:"2xl"},{id:"3xl",text:"3xl"},{id:"4xl",text:"4xl"},{id:"5xl",text:"5xl"},{id:"其他",text:"其他"}];
        $("#cancelitemList").datagrid({
            high:"auto",
            columns:[[
                {field:'cloth_id',title:"货号",editor:{type:'text',options:{required:true}},width:50},
                {field:"color",title:"颜色",editor:{type:'text',options:{required:true}},width:50},
                {field:"size",title:"尺码",editor:{type:'combobox',options:{editable:true,data:data,valueField:"id",textField:"text",required:true,}},width:50},
                {field:"num",title:"数量",editor:{type:'text',options:{required:true}},width:50},
                {field:"single",title:"单价",editor:{type:'text',options:{required:true,precision:1}},width:50},
                {field:'total',title:"总价",formatter:function(value,row,index){
                        row.total=row.single*row.num;
                        return row.single*row.num;
                    }},
                {field:"des",title:"备注",editor:{type:'text'},width:150},
                {field:"status",hidden:true}
            ]],
            onSelect:function (rowIndex, rowData) {
                $("#cancelitemList").datagrid('beginEdit',rowIndex);
                bindcancelenterevent();
            }
        });
        $(".combo").click(function(){
            $(this).prev().combobox("showPanel");
        })
    });
    function finishcanceledit(){
        for(i in $("#cancelitemList").datagrid('getRows')){
            $("#cancelitemList").datagrid('endEdit',i);
            $("#cancelitemList").datagrid('getRows')[i].date=$("#cancelitemdate").datebox('getText');
            $("#cancelitemList").datagrid('getRows')[i].status='complete'
        }
    }
    function newcancelItem(){
        $("#cancelitemList").datagrid('appendRow',{});
        $("#cancelitemList").datagrid('selectRow', $("#cancelitemList").datagrid('getRows').length-1);
        bindcancelenterevent();
    }
    function destroycancelItem(){
        var row = $("#cancelitemList").datagrid('getSelected');
        if (row!=null){
            var index = $("#cancelitemList").datagrid('getRowIndex',row);
            $.messager.confirm('提示','是否要撤销记录?',function(r){
                if (r){
                    $("#cancelitemList").datagrid('deleteRow',index);
                }
            });
        }
        bindcancelenterevent();
    }
    function submitcancelAddForm(){
        finishcanceledit();
        //有效性验证
        if(!$('#cancelAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        $.ajax({
            type:"POST",
            url:"cancelitem/insert",
            data:JSON.stringify($("#cancelitemList").datagrid('getData').rows),
            processData: false,  // 不处理数据
            contentType: "application/json",   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $("#cancelAddForm").form("load", {item_id:data.msg});
                    $.ajax({type:"POST",url:"cancel/insert",data:$("#cancelAddForm").serialize()});
                    $.messager.alert('提示','新增成功!');
                    clearcancelAddForm();
                    $("#cancelAddWindow").window('close');
                    $("#cancelList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })
    }

    function clearcancelAddForm(){
        $('#cancelAddForm').form('reset');
        while($("#cancelitemList").datagrid('getRows').length>0){
            $("#cancelitemList").datagrid('deleteRow',0);
        }
        $('#cancelAddForm').find('input')[2].focus()
    }
</script>