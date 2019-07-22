
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="customerEditForm" class="customerEditForm" method="post">
        <input type="hidden" name="customer_id"/>
        <table cellpadding="5" >
            <input  type="hidden" name="customer_id" data-options="required:true"/>
            <tr>
                <td>客户名字:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="customer_name" />
                </td>
            </tr>
            <tr>
                <td>电话号码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="phone_number" />
                </td>
            </tr>
            <tr>
                <td>地址</td>
                <td>
                    <input class="easyui-textbox" type="text" name="address" style="width: 280px;"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitcustomerEditForm()">提交</a>
    </div>
</div>
<script>
    $(function () {
        setTimeout(bindcustomereditenterevent, 10)
    })
    function bindcustomereditenterevent(){
        console.log(456)
        for(i=0;i<$("#customerEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").length;i++){
            $($("#customerEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")).keydown(function(e){
                    if(e.which==13){
                        $($("#customerEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")[$("#customerEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").index(e.target)+1]).focus()
                    }
                }
            )
        }
    }
    function submitcustomerEditForm(){
        if(!$('#customerEditForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        console.log($("#customerEditForm").serialize())
        $.post("customer/update",$("#customerEditForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','修改成功!','info',function(){
                    $("#customerEditWindow").window('close');
                    $("#customerList").datagrid("reload");
                });
            }else{
                $.messager.alert('提示', data.msg);
            }
        }, "json");
    }
</script>