<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="customerAddForm" class="customerForm" method="post">
        <input type="hidden" name="customer_id"/>
        <table cellpadding="5" >
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
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitcustomerAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearcustomerAddForm()">重置</a>
    </div>
</div>
<script>
    $(function () {
        setTimeout(bindcustomeraddenterevent, 10)
    })
    function bindcustomeraddenterevent(){
        console.log(456)
        for(i=0;i<$("#customerAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").length;i++){
            $($("#customerAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")).keydown(function(e){
                    if(e.which==13){
                        $($("#customerAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")[$("#customerAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)").index(e.target)+1]).focus()
                    }
                }
            )
        }
    }
    function submitcustomerAddForm(){
//有效性验证
        if(!$('#customerAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
//ajax的post方式提交表单
//$("#customAddForm").serialize()将表单序列号为key-value形式的字符串
        $.post("/customer/insert",$("#customerAddForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','新增成功!');
                clearcustomerAddForm();
                $("#customerAddWindow").window('close');
                $("#customerList").datagrid("reload");
            }else{
                $.messager.alert('提示',data.msg);
            }
        });
    }

    function clearcustomerAddForm(){
        $('#customerAddForm').form('reset');
    }
</script>
