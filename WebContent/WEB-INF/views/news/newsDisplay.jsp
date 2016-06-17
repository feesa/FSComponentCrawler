<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>user Display</title>
<script type="text/javascript" src="scripts/jquery-1.11.1.min.js"></script>
</head>
<body>
<h1>最新新闻</h1>
<div id="d1"></div>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:'GET',
			url:'news/getUsers',
			dataType:'json',
			success:function(data){
				$(data).each(function(i,item){
					$("#d1").append("<span>"+item.firstName+","+item.lastName+"<span>");
				});
			}
		});
	});
</script>
</body>
</html>