<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">
    <form id="clothAddForm" class="clothForm" method="post" enctype="multipart/form-data">
        <table cellpadding="5" >
            <tr>
                <td>货号:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="cloth_id" id="cloth_id" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>图片</td>
                <td>
                    <input value="上传图片" class="easyui-linkbutton picFileUpload" type="file" name="picture_path" accept="image/jpeg,image/png,image/gif" id="picture_path1"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="des" id="des1"/>
                </td>
            </tr>
            <tr>
                <td>s:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="s" id="s1" />
                </td>
            </tr>
            <tr>
                <td>m:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="m" id="m1" />
                </td>
            </tr>
            <tr>
                <td>l:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="l" id="l1"/>
                </td>
            </tr>
            <tr>
                <td>xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl" id="xl1" />
                </td>
            </tr>
            <tr>
                <td>2xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl2" id="xl21"/>
                </td>
            </tr>
            <tr>
                <td>3xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl3" id="xl31"/>
                </td>
            </tr>
            <tr>
                <td>4xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl4" id="xl41" />
                </td>
            </tr>
            <tr>
                <td>5xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl5" id="xl51"/>
                </td>
            </tr>
            <tr>
                <td>其他:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="others" id="others1"/>
                </td>
            </tr>
            <tr>
                <td>默认价格:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="default_price" id="default_price"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitclothAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearclothAddForm()">重置</a>
    </div>
</div>
<script>
    $(function () {
        setTimeout(bindclothaddenterevent, 10)
    })
    function bindclothaddenterevent(){
        var input_list=$("#clothAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")
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
    function submitclothAddForm(){
        //有效性验证
        if(!$('#clothAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        //使用FormData上传图片及表单
        var formdata=new FormData()
        formdata.append("cloth_id",$("#cloth_id").val())
        formdata.append('picture_path',$('#picture_path1')[0].files[0])
        formdata.append("des",$("#des1").val())
        formdata.append("s",$("#s1").val())
        formdata.append("m",$("#m1").val())
        formdata.append("l",$("#l1").val())
        formdata.append("xl",$("#xl1").val())
        formdata.append("xl2",$("#xl21").val())
        formdata.append("xl3",$("#xl31").val())
        formdata.append("xl4",$("#xl41").val())
        formdata.append("xl5",$("#xl51").val())
        formdata.append("others",$("#others1").val())
        $.ajax({
            type:"POST",
            url:"cloth/insert",
            data:formdata,
            processData: false,  // 不处理数据
            contentType: false,   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $.messager.alert('提示','新增成功!');
                    clearclothAddForm();
                    $("#clothAddWindow").window('close');
                    $("#clothList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })

    }

    function clearclothAddForm(){
        $('#clothAddForm').form('reset');
    }
</script>