
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="userEditForm" class="userEditForm" method="post">
        <input type="hidden" name="user_id"/>
        <table cellpadding="5" >
            <tr>
                <td>角色:</td>
                <td>
                    <select class="easyui-combobox" name="role_id" style="width: 200px">
                        <option value="1">系统管理员</option>
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
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitUserEditForm()">提交</a>
    </div>
</div>
<script>
    function submitUserEditForm(){
        if(!$('#userEditForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        console.log($("#userEditForm").serialize())
        $.post("user/update",$("#userEditForm").serialize(), function(data){
            if(data.status == 200){
                $.messager.alert('提示','修改成功!','info',function(){
                    $("#userEditWindow").window('close');
                    $("#userList").datagrid("reload");
                });
            }else{
                $.messager.alert('提示', data.msg);
            }
        }, "json");
    }
</script>