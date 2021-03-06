
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="padding:10px 10px 10px 10px">

    <form id="clothEditForm" class="clothForm" method="post" enctype="multipart/form-data">
        <table cellpadding="5" >
            <input type="hidden" name="cloth_id" id="cloth_id1"/>
            <tr>
                <td>图片</td>
                <td>
                    <input value="上传图片" class="easyui-linkbutton picFileUpload" type="file" name="picture_path" accept="image/jpeg,image/png,image/gif" id="picture_path1"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td>
                    <input  class="easyui-textbox" type="text" name="des" id="des"/>
                </td>
            </tr>
            <tr>
                <td>s:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="s" id="s" />
                </td>
            </tr>
            <tr>
                <td>m:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="m" id="m" />
                </td>
            </tr>
            <tr>
                <td>l:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="l" id="l"/>
                </td>
            </tr>
            <tr>
                <td>xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl" id="xl" />
                </td>
            </tr>
            <tr>
                <td>2xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl2" id="xl2"/>
                </td>
            </tr>
            <tr>
                <td>3xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl3" id="xl3"/>
                </td>
            </tr>
            <tr>
                <td>4xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl4" id="xl4" />
                </td>
            </tr>
            <tr>
                <td>5xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl5" id="xl5"/>
                </td>
            </tr>
            <tr>
                <td>其他:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="others" id="others"/>
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
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitclothEditForm()">提交</a>
    </div>
</div>
<script>
    $(function () {
        setTimeout(bindclotheditenterevent, 10)
    })
    function bindclotheditenterevent(){
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
    function submitclothEditForm(){
        var formdata=new FormData()
        formdata.append("cloth_id",$("#cloth_id1").val())
        formdata.append('picture_path',$('#picture_path1')[0].files[0])
        formdata.append("des",$("#des").val())
        formdata.append("s",$("#s").val())
        formdata.append("m",$("#m").val())
        formdata.append("l",$("#l").val())
        formdata.append("xl",$("#xl").val())
        formdata.append("xl2",$("#xl2").val())
        formdata.append("xl3",$("#xl3").val())
        formdata.append("xl4",$("#xl4").val())
        formdata.append("xl5",$("#xl5").val())
        formdata.append("others",$("#others").val())
        formdata.append("default_price",$("#default_price").val())
        $.ajax({
            type:"POST",
            url:"cloth/update",
            data:formdata,
            processData: false,  // 不处理数据
            contentType: false,   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $.messager.alert('提示','修改成功!');
                    $("#clothEditWindow").window('close');
                    $("#clothList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })
    }
</script>