<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="clothList" title="列表"
       singleSelect="true" collapsible="true" pagination="true" rownumbers="true"
       url="/cloth/page" method="post" pageSize="50" fitColumns="true" toolbar="#toolbarcloth">
    <thead>
    <tr>
        <th checkbox="true" field="ck"></th>
        <th field="cloth_id" data-options="width:150">
            货号
        </th>
        <th field="picture_path" formatter="formatpic" data-options="width:150">
            图片
        </th>
        <th field="des"  data-options="width:300">
            备注
        </th>
        <th field="s" data-options="width:150">
            S
        </th>
        <th field="m" data-options="width:150">
            M
        </th>
        <th field="l" data-options="width:150">
            L
        </th>
        <th field="xl" data-options="width:150">
            XL
        </th>
        <th field="xl2" data-options="width:150">
            2XL
        </th>
        <th field="xl3" data-options="width:150">
            3XL
        </th>
        <th field="xl4" data-options="width:150">
            4XL
        </th>
        <th field="xl5" data-options="width:150">
            5XL
        </th>
        <th field="others" data-options="width:150">
            其他
        </th>
        <th field="default_price" data-options="width:150">
            默认价格
        </th>
    </tr>
    </thead>
</table>
<div id="toolbarcloth">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newcloth()">添加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editcloth()">编辑</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroycloth()">删除</a>

    <div id="search_cloth" style="float: right;">
        <input id="search_text_cloth" class="easyui-searchbox"
               data-options="searcher:doSearch_cloth_id,prompt:'请输入...',menu:'#menu_cloth'"
               style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_cloth" style="width:120px">
            <div data-options="name:'cloth_id'">服装编号</div>
        </div>
    </div>
</div>

<div id="clothEditWindow" class="easyui-window" title="编辑信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/cloth/edit'"
     style="width:65%;height:80%;padding:10px;">
</div>

<div id="clothAddWindow" class="easyui-window" title="添加信息"
     data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'/cloth/add'"
     style="width:65%;height:80%;padding:10px;">
</div>

<script>
    $("#clothList").datagrid({
        view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px" id="cloth'+index+'" ><table class="ddv"></table></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.panel({
                href:'sellcondition',
                onLoad:function(){
                    $("#clothList").datagrid('selectRow',index);
                    $("#countForm").form('load',{cloth_id:row.cloth_id});
                    $("#countForm").attr('id','countForm'+index);
                    $("#statistics").attr('id',"statistics"+index);
                    $("#statistics"+index).datagrid({toolbar:$("#countForm"+index)})
                    $("#variablebutton").attr("index",index);
                    $("#variablebutton").attr("id","variablebutton"+index);
                    $("#clothList").datagrid('fixDetailRowHeight',index);
                }
            })
            ddv.find("#statistics").datagrid({
                toolbar:ddv.find("#countForm")
            })
            $("#clothList").datagrid('fixDetailRowHeight',index);
        }
    });
    function doSearch_cloth_id(value,name){
        if(value==null||value==''){
            $("#clothList").datagrid("reload");
        }else{
            $.get("/cloth/"+value,'',function(data){
                if(data!=''){
                    $("#clothList").datagrid("loadData",{"total":1,"rows":[data]})
                }else{
                    $("#clothList").datagrid("loadData",{"total":0,"rows":[]})
                }
            })
        }
    }

    function newcloth() {
        $('#clothAddWindow').window("open")
    }

    function getclothSelectionsIds(){
        var clothList = $("#clothList");
        var sels = clothList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].cloth_id);
        }
        ids = ids.join(",");
        console.log(ids)
        return ids;
    }

    function editcloth(){
        var ids = getclothSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一条记录才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一条记录!');
            return ;
        }

        $("#clothEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#clothList").datagrid("getSelections")[0];
                data.picture_path=undefined;
                $("#clothEditForm").form("load", data);
            },
            onOpen:function () {
                var data = $("#clothList").datagrid("getSelections")[0];
                data.picture_path=undefined;
                $("#clothEditForm").form("load", data);
            }
        }).window("open");

    }
    function destroycloth(){
        var ids = getclothSelectionsIds();
        console.log(ids)
        var param={"ids":ids}
        console.log(param)
        if(ids.length == 0){
            $.messager.alert('提示','未选中记录!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的记录吗？',function(r){
            if (r){
                $.post("/cloth/delete_batch",param, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','删除记录成功!',undefined,function(){
                            $("#clothList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg);
                    }
                });
            }
        });
    }
</script>
