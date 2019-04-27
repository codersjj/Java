<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- 路径 -->
<base href="<%=basePath%>">
<title>预定成功</title>

<style type="text/css">
	a{
		font-size: 50px;
	}
</style>
</head>
<body>
	<a href="productInfo/listAll?yuDing=ok" style="color:red;">预定成功!恭候您的光临~</a>
	<script type="text/javascript">
		function toIndex(){
			window.location.href="<%=basePath%>productInfo/listAll?yuDing=ok";
		}
		
		window.setTimeout(toIndex,5000);
	
	</script>
</body>
</html>