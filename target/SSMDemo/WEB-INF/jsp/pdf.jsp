<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/14
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<embed src="/order.pdf" style="width: 100%;high:100%">
<script>
    $(document).ready(function () {
        window.print();
    });
</script>
