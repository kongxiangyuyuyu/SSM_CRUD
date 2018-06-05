<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- 
	不以/开始的相对路径找资源，以当前资源路径为基准，容易出错
	以/开始的相对路径找资源，以服务器的路径为标准，需要加上项目名
 -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery.js"></script>

<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<link
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css"
	rel="stylesheet">

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
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
			&nbsp; &nbsp;
		</div>
		<!-- 第三行显示表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
					<!--使用c:forEach将数据遍历出来，item是遍历的内容,取出来的每个值都为emp  -->
					<c:forEach items="${pageInfo.list}" var="emp">
						<tr>
							<th>${emp.empId}</th>
							<th>${emp.empName}</th>
							<th>${emp.gender=="M"?"男":"女"}</th>
							<th>${emp.email}</th>
							<th>${emp.department.deptName}</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-plus-sign " aria-hidden="true"></span>&nbsp;新增
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-remove-sign "
										aria-hidden="true"></span>&nbsp;删除
								</button>
							</th>
						</tr>


					</c:forEach>


				</table>
			</div>
		</div>
		<!-- 第四行显示分页信息 -->
		<div class="row">
			<!--显示分页信息  -->
			<div class="col-md-6">
				当前第${pageInfo.pageNum}页，总共有${pageInfo.pages}页
				,总共有${pageInfo.total}条记录</div>

			<!--显示分页条  -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><a href="${pageContext.request.contextPath}/emps?pn=1">首页</a></li>
					<!-- 上一页 -->
					<!-- 判断是否有上一页 -->
					<c:if test="${pageInfo.hasPreviousPage}">
						<li><a
							href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum-1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<!-- 判断是否为当前页，如果是当前页，则高亮显示 -->
					<c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
						<c:if test="${page_Num==pageInfo.pageNum}">
							<li class="active"><a href="#">${page_Num}</a></li>
						</c:if>
						<!-- 如果不是当前页，则不高亮显示 -->
						<c:if test="${page_Num!=pageInfo.pageNum}">
							<li><a
								href="${pageContext.request.contextPath}/emps?pn=${page_Num}">${page_Num}</a></li>
						</c:if>
					</c:forEach>
					<!-- 下一页 -->
					<!--判断是否有下一页  -->
					<c:if test="${pageInfo.hasNextPage}">
						<li><a
							href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pageNum+1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/emps?pn=${pageInfo.pages}">尾页</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>

</body>
</html>