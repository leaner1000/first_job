
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
            <input type="hidden" name="unused1">
            <input type="hidden" name="unused2">
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
        var input_list=$("#clothEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")
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
    function submitcustomerEditForm(){
        if(!$('#customerEditForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        console.log($("#customerEditForm").serialize())
        $.post({
            url:"customer/update",
            data:JSON.stringify($("#customerEditForm").serializeObject()),
            success: function(data){
            if(data.status == 200){
                $.messager.alert('提示','修改成功!','info',function(){
                    $("#customerEditWindow").window('close');
                    $("#customerList").datagrid("reload");
                });
            }else{
                $.messager.alert('提示', data.msg);
            }
        },
            contentType: "application/json"});
    }
</script>