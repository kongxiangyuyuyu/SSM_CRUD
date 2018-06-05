<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_Path",request.getContextPath());

%>
<!-- 
	不以/开始的相对路径找资源，以当前资源路径为基准，容易出错
	以/开始的相对路径找资源，以服务器的路径为标准，需要加上项目名
 -->
<script src="${APP_Path}/static/js/jquery.min.js"></script>
<link href="${APP_Path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${APP_Path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.css" rel="stylesheet">
</head>
<body>
		<!-- 使用bootstrap显示页面 -->
		<div class="container">
			<!-- 总共有四行 -->
			<!-- 第一行显示大标题 -->
			<div class="row">
				<div class="col-md-12">
					<h1>SSM-CRUD</h1>				
				</div>
			</div>
			<!-- 第二行显示按钮 -->
			<div class="row"></div>
			<!-- 第三行显示表格 -->
			<div class="row"></div>
			<!-- 第四行显示分页信息 -->
			<div class="row"></div>
		</div>

</body>
</html>