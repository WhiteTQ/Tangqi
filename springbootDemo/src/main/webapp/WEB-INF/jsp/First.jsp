<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css"  media="all">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>首页</title>
</head>
<body>
   <table class="layui-table"  lay-filter="tblist"  style=" width: 50%; max-width: 100%; margin-bottom: 20px; margin: auto;">
 	 	<!-- <button class="layui-btn"  style="margin-left: 1375px;" onclick="add()">新增</button> -->
		<a class="layui-btn" style="margin-left: 1375px;" href="/tuser/addJsp">新增</a>
 		<thead align="center">
			<tr>
			  <td>ID</td>
			  <td>姓名</td>
			  <td>密码</td>
			  <td>操作</td>
			</tr>
		<thead>
		<tbody align="center">
			<c:forEach var="users" items="${user}">
				<tr>
				  <td>${users.id}</td>
				  <td>${users.name}</td>
				  <td>${users.password}</td>
				  <td>
				 		 <a class="layui-btn layui-btn-warm"  href="/tuser/fidUserByID?id=${users.id}">编辑</a>
					     <button class="layui-btn layui-btn-danger" onclick="del(${users.id})">删除</button>
				  </td>
				</tr>
			</c:forEach>
		<tbody> 
	</table>
</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/layui/layui.js" charset="utf-8"></script>
<script>
	//删除
  function del(id){
		layui.use('layer', function(){
			 layer.open({
				  content: '确认要删除吗'
				  ,btn: ['确定', '取消']
				  ,yes: function(index, layero){
				      $.ajax({
					   	  type: 'GET',
					   	  url: 'http://localhost:8080/tuser/delUser',//发送请求
					   	  data: {id:id},
					   	  success: function(result) {
					   		  console.info(result);
					   		  location.reload();
						  },
					   	  error : function(data){
					   		 console.info(data);
					   	  }
					 })
				  }
				  ,btn2: function(index, layero){
					layer.close(index);
				  }
				  ,cancel: function(){ 
				    //右上角关闭回调
				   layer.close();
				  }
			});
	});
  }
</script> 
</html>