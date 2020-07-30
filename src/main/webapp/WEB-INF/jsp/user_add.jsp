<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="userAddForm" class="userForm" method="post">
        <table cellpadding="5" >
            <tr>
                <td>角色:</td>
                <td>
                    <select class="easyui-combobox" name="role_id" style="width: 200px">
                        <option value="1">系统管理员</option>
                        <option value="2">店员</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>用户名:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="username" style="width: 280px;"/>
                </td>
            </tr>
            <tr>
                <td>密码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="password" style="width: 280px;"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitUserAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearUserAddForm()">重置</a>
    </div>
</div>
<script>

    function submitUserAddForm(){
//有效性验证
        if(!$('#userAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
//ajax的post方式提交表单
//$("#customAddForm").serialize()将表单序列号为key-value形式的字符串
        $.post("/user/insert",$("#userAddForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','新增成功!');
                clearUserAddForm();
                $("#userAddWindow").window('close');
                $("#userList").datagrid("reload");
            }else{
                $.messager.alert('提示',data.msg);
            }
        });
    }

    function clearUserAddForm(){
        $('#userAddForm').form('reset');
    }
</script>
