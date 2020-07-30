
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">

    <form id="outstockEditForm" class="outstockForm" method="post" enctype="multipart/form-data">
        <table cellpadding="5" >
            <input type="hidden" name="outstock_id" id="outstock_id3"/>
            <tr>
                <td>图片</td>
                <td>
                    <input value="上传图片" class="easyui-linkbutton picFileUpload" type="file" name="picture_path" accept="image/jpeg,image/png,image/gif" id="picture_path3"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="des" id="des3"/>
                </td>
            </tr>
            <tr>
                <td>s:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="s" id="s3" />
                </td>
            </tr>
            <tr>
                <td>m:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="m" id="m3" />
                </td>
            </tr>
            <tr>
                <td>l:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="l" id="l3"/>
                </td>
            </tr>
            <tr>
                <td>xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl" id="xl3" />
                </td>
            </tr>
            <tr>
                <td>2xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl2" id="xl23"/>
                </td>
            </tr>
            <tr>
                <td>3xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl3" id="xl33"/>
                </td>
            </tr>
            <tr>
                <td>4xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl4" id="xl43" />
                </td>
            </tr>
            <tr>
                <td>5xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl5" id="xl53"/>
                </td>
            </tr>
            <tr>
                <td>其他:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="others" id="others3"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitoutstockEditForm()">提交</a>
    </div>
</div>
<script>
    $(function () {
        setTimeout(bindoutstockeditenterevent, 10)
    })
    function bindoutstockeditenterevent(){
        var input_list=$("#outstockEditForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")
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
    function submitoutstockEditForm(){
        var formdata=new FormData()
        formdata.append("outstock_id",$("#outstock_id3").val())
        formdata.append('picture_path',$('#picture_path3')[0].files[0])
        formdata.append("des",$("#des3").val())
        formdata.append("s",$("#s3").val())
        formdata.append("m",$("#m3").val())
        formdata.append("l",$("#l3").val())
        formdata.append("xl",$("#xl3").val())
        formdata.append("xl2",$("#xl23").val())
        formdata.append("xl3",$("#xl33").val())
        formdata.append("xl4",$("#xl43").val())
        formdata.append("xl5",$("#xl53").val())
        formdata.append("others",$("#others3").val())
        $.ajax({
            type:"POST",
            url:"outstock/update",
            data:formdata,
            processData: false,  // 不处理数据
            contentType: false,   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $.messager.alert('提示','修改成功!');
                    $("#outstockEditWindow").window('close');
                    $("#outstockList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })
    }
</script>