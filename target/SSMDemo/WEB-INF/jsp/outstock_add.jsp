<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    audio { display: block; margin-bottom: 10px; }
    #audio-container { padding: 20px 0; }
    .ui-btn { display: inline-block; padding: 5px 20px; font-size: 14px; line-height: 1.428571429; box-sizing:content-box; text-align: center; border: 1px solid #e8e8e8; border-radius: 3px; color: #555; background-color: #fff; border-color: #e8e8e8; white-space: nowrap; cursor: pointer; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; }
    .ui-btn:hover, .ui-btn.hover { color: #333; text-decoration: none; background-color: #f8f8f8; border:1px solid #ddd; }
    .ui-btn:focus, .ui-btn:active { color: #333; outline: 0; }
    .ui-btn.disabled, .ui-btn.disabled:hover, .ui-btn.disabled:active, .ui-btn[disabled], .ui-btn[disabled]:hover, .ui-state-disabled .ui-btn { cursor: not-allowed; background-color: #eee; border-color: #eee; color: #aaa; }
    .ui-btn-primary { color: #fff;  background-color: #39b54a;  border-color: #39b54a; }
    .ui-btn-primary:hover, .ui-btn-primary.hover { color: #fff; background-color: #16a329; border-color: #16a329; }
    .ui-btn-primary:focus, .ui-btn-primary:active { color: #fff; }
    .ui-btn-primary.disabled:focus{ color: #aaa; }
</style>
<div style="padding:10px 10px 10px 10px">
    <form id="outstockAddForm" class="outstockForm" method="post" enctype="multipart/form-data">
        <table cellpadding="5" >
            <tr>
                <td>货号:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="out_stock_id" id="out_stock_id" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>图片</td>
                <td>
                    <input value="上传图片" class="easyui-linkbutton picFileUpload" type="file" name="picture_path" accept="image/jpeg,image/png,image/gif" id="picture_path2"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td>
                    <button id="outstockdesstart" class="ui-btn ui-btn-primary" disabled>录音</button>
                    <button id="outstockdesstop" class="ui-btn ui-btn-primary" disabled>停止</button>
                    <div id="outstockaudio-container">
                        <audio id="outstockaudio"></audio>
                    </div>
                </td>
            </tr>
            <tr>
                <td>s:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="s" id="s2" />
                </td>
            </tr>
            <tr>
                <td>m:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="m" id="m2" />
                </td>
            </tr>
            <tr>
                <td>l:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="l" id="l2"/>
                </td>
            </tr>
            <tr>
                <td>xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl" id="xl2" />
                </td>
            </tr>
            <tr>
                <td>2xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl2" id="xl22"/>
                </td>
            </tr>
            <tr>
                <td>3xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl3" id="xl32"/>
                </td>
            </tr>
            <tr>
                <td>4xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl4" id="xl42" />
                </td>
            </tr>
            <tr>
                <td>5xl:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="xl5" id="xl52"/>
                </td>
            </tr>
            <tr>
                <td>其他:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="others" id="others2"/>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitoutstockAddForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearoutstockAddForm()">重置</a>
    </div>
</div>
<script>
    var audio_blob;
    $(function () {
        var start = document.querySelector('#outstockdesstart');     //录音
        var stop = document.querySelector('#outstockdesstop');
        var container = document.querySelector('#outstockaudio-container');
        var recorder = new Recorder({
            sampleRate: 44100, //采样频率，默认为44100Hz(标准MP3采样率)
            bitRate: 128, //比特率，默认为128kbps(标准MP3质量)
            success: function(){ //成功回调函数
                start.disabled = false;
            },
            error: function(msg){ //失败回调函数
                alert(msg);
            },
            fix: function(msg){ //不支持H5录音回调函数
                alert(msg);
            }
        });
        start.addEventListener('click',function(){
            this.disabled = true;
            stop.disabled = false;
            var audio = document.querySelectorAll('audio');
            for(var i = 0; i < audio.length; i++){
                if(!audio[i].paused){
                    audio[i].pause();
                }
            }
            recorder.start();
        });
        stop.addEventListener('click',function(){
            this.disabled = true;
            start.disabled = false;
            recorder.stop();
            recorder.getBlob(function(blob){
                audio_blob=blob;
                var audio = document.querySelector('#outstockaudio');
                audio.src = URL.createObjectURL(blob);
                audio.controls = true;
            });
        });


        setTimeout(bindoutstockaddenterevent, 10)   //初始化回车跳到下一格
    })
    function bindoutstockaddenterevent(){
        var input_list=$("#outstockAddForm").find("input[id^='_easyui_textbox_input'],.datagrid-editable-input:not(.combo-f)")
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
    function submitoutstockAddForm(){
        //有效性验证
        if(!$('#outstockAddForm').form('validate')){
            $.messager.alert('提示','表单还未填写完成!');
            return ;
        }
        //使用FormData上传图片及表单
        var formdata=new FormData()
        formdata.append("outstock_id",$("#outstock_id").val())
        formdata.append('picture_path',$('#picture_path2')[0].files[0])
        formdata.append("des",new File([audio_blob], "des", {type: "audio/mp3", lastModified: Date.now()}))
        formdata.append("s",$("#s2").val())
        formdata.append("m",$("#m2").val())
        formdata.append("l",$("#l2").val())
        formdata.append("xl",$("#xl2").val())
        formdata.append("xl2",$("#xl22").val())
        formdata.append("xl3",$("#xl32").val())
        formdata.append("xl4",$("#xl42").val())
        formdata.append("xl5",$("#xl52").val())
        formdata.append("others",$("#others2").val())
        $.ajax({
            type:"POST",
            url:"outstock/insert",
            data:formdata,
            processData: false,  // 不处理数据
            contentType: false,   // 不设置内容类型
            success:function(data){
                if(data.status == 200){
                    $.messager.alert('提示','新增成功!');
                    clearoutstockAddForm();
                    $("#outstockAddWindow").window('close');
                    $("#outstockList").datagrid("reload");
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        })

    }

    function clearoutstockAddForm(){
        $('#outstockAddForm').form('reset');
        var audio = document.querySelector('#outstockaudio');
        audio.src = "";
    }
</script>