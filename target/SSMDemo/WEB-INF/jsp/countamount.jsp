<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="stattoolbar" style="padding:5px;height:auto">
    <form id="conditionForm">
        起始日期: <input name="startTime" class="easyui-datebox" style="width:150px">
        终止日期: <input name="endTime" class="easyui-datebox" style="width:150px">
        客户名:<input name="customer_name" class="easyui-combobox" style="width:150px" id="searchcustomercombobox" >
        <a class="easyui-linkbutton" iconCls="icon-search" onclick="submitcondition()">统计</a>
    </form>
</div>
<div id="statfooer">

</div>
<table id="countamounttable" title="统计" class="easyui-datagrid"
       singleSelect="true" iconCls="icon-save" rownumbers="true" showFooter="true" footer="#statfooter"
       pagination="false" fitColumns="true" toolbar="#stattoolbar">
    <thead frozen="true">
    <tr>
        <th field="cloth_id" width="50" >货号</th>
        <th field="picture_path" width="50" formatter="formatpic">图片</th>
    </tr>
    </thead>
    <thead>
        <tr>
            <%--<th colspan="2">s</th>--%>
            <%--<th colspan="2">m</th>--%>
            <%--<th colspan="2">l</th>--%>
            <%--<th colspan="2">xl</th>--%>
            <%--<th colspan="2">2xl</th>--%>
            <%--<th colspan="2">3xl</th>--%>
            <%--<th colspan="2">4xl</th>--%>
            <%--<th colspan="2">5xl</th>--%>
            <%--<th colspan="2">其他</th>--%>
            <%--<th rowspan="2" field="amount" formatter="formatred">总件数</th>--%>
            <%--<th rowspan="2" field="total">总金额</th>--%>
            <th colspan="1">s</th>
            <th colspan="1">m</th>
            <th colspan="1">l</th>
            <th colspan="1">xl</th>
            <th colspan="1">2xl</th>
            <th colspan="1">3xl</th>
            <th colspan="1">4xl</th>
            <th colspan="1">5xl</th>
            <th colspan="1">其他</th>
            <th rowspan="2" field="amount" formatter="formatred">总件数</th>
            <th rowspan="2" field="total">总金额</th>
        </tr>
        <tr>
            <th  field="samount" width="60" align="center">数量</th>
            <%--<th  field="stotal" width="60" align="center">金额</th>--%>
            <th  field="mamount" width="60" align="center">数量</th>
            <%--<th  field="mtotal" width="60" align="center">金额</th>--%>
            <th  field="lamount" width="60" align="center">数量</th>
            <%--<th  field="ltotal" width="60" align="center">金额</th>--%>
            <th  field="xlamount" width="60" align="center">数量</th>
            <%--<th  field="xltotal" width="60" align="center">金额</th>--%>
            <th  field="xl2amount" width="60" align="center">数量</th>
            <%--<th  field="xl2total" width="60" align="center">金额</th>--%>
            <th  field="xl3amount" width="60" align="center">数量</th>
            <%--<th  field="xl3total" width="60" align="center">金额</th>--%>
            <th  field="xl4amount" width="60" align="center">数量</th>
            <%--<th  field="xl4total" width="60" align="center">金额</th>--%>
            <th  field="xl5amount" width="60" align="center">数量</th>
            <%--<th  field="xl5total" width="60" align="center">金额</th>--%>
            <th  field="otheramount" width="60" align="center">数量</th>
            <%--<th  field="othertotal" width="60" align="center">金额</th>--%>
        </tr>
    </thead>
</table>
<script>
    $(function (){

        $("#searchcustomercombobox").combobox({
            mode:'remote',
            url:'customer/autocomplete',
            method:'get',
            textField:"customer_name",
            valueField:"customer_name",
        })
    });
    function submitcondition(){
        var form=$("#conditionForm").serialize();
        $.ajax({
            url:"statistics",
            method:"post",
            data:form,
            success:function(data){
                console.log(data);
                $("#countamounttable").datagrid('loadData',data);
                var tmp=0;
                for(i in $("#countamounttable").datagrid('getRows')){
                    tmp=tmp+$("#countamounttable").datagrid('getRows')[i].total;
                }
                $("#countamounttable").datagrid('reloadFooter',[{total: tmp, amount: '总金额'}])
            }
        })
    }
</script>
