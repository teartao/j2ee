<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>view welcome</title>
<link rel="stylesheet" href="./css/index.css" type="text/css"/>
<script type="text/javascript" src="./js/jquery-1.10.2.js"></script>
</head>
<script type="text/javascript">

</script>

<body>
	<div class="body-div">
		<div class="nav-logo"></div>
	</div>
<form method="post" action="person">
    id:<input type="text" value=""/>
    name:<input type="text" value=""/>
    <input type="submit" value="submit"/>
    <input type="button" value="button" onclick="addPerson()"/>
    <a href="app001/demo001.html">demo001</a>
</form>
<script>
    var requestParam={
        url:"<%=basePath%>"
    }
    function addPerson(){
        $.ajax({
            url: requestParam.url + 'person',
            type: 'POST',
            dataType: 'json',
            data: {id: 1,name:'张三',sex:'男',age:23}
        }).done(function(data, status, xhr) {
            console.log("data, status, xhr");
            console.log(data);
            console.log(status);
            console.log(xhr);
        }).fail(function(xhr, status, error) {
            console.log("error, status, xhr");
            console.log(error);
            console.log(status);
            console.log(xhr);
        });
    }
</script>
</body>
</html>
