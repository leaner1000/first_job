<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="countForm" class="countForm" method="post">
    <table cellpadding="5" >
        <input type="hidden" name="cloth_id" id="cloth_id2"/>
        <tr>
            <td>起始日期</td>
            <td>
                <input type="text" name="startTime" class="easyui-datebox">
            </td>
            <td>终止日期</td>
            <td>
                <input type="text" name="endTime" class="easyui-datebox">
            </td>
            <td><a href="javascript:void(0)" id="variablebutton" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="count(this)">查询</a>
            </td>
        </tr>
    </table>
</form>
    <table class="easyui-datagrid" id="statistics"
           singleSelect="false" collapsible="false" pagination="false" rownumbers="true"
             fitColumns="true">
        <thead>
        <tr>
            <th field="amount" data-options="width:150">
                销量（件）
            </th>
            <th field="total"  data-options="width:150">
                销售金额
            </th>
            <th field="average_price" formatter="computeaverage_price" data-options="width:150">
                平均价格
            </th>
        </tr>
        </thead>
    </table>
<script type="text/javascript">
    function computeaverage_price(val,row) {
        return row.total/row.amount;
    }
    function count(button){
        console.log(button);
        var index=$(button).attr('index');
        $.ajax({
            url:"clothcount",
            method:"post",
            data:$("#countForm"+index).serialize(),
            success:function (data) {
                console.log(data);
                $("#clothList").datagrid('getRowDetail',index).find('table.ddv').find('#statistics'+index).datagrid('loadData',[data]);
                for(i in $("#clothList").datagrid('getRows')){
                    $("#clothList").datagrid('fixDetailRowHeight',i);
                }
            }
        })
    }
</script>