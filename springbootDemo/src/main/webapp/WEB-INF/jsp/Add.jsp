<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css" media="all">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新增</title>
</head>
<body>
	<from class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="username" id="username" value="" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-inline">
				<input type="text" name="password" id="password" value="" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-danger" onclick="add()">提交</button>
			</div>
		</div>
	</form>
</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/layui/layui.js" charset="utf-8"></script>
<script>
function User(){
    this.name = $("#username").val();
    this.password = $("#password").val();
}
//编辑提交
function add(){
		var user = new User()
		layui.use('layer', function(){
			 layer.open({
				  content: '确认提交吗？'
				  ,btn: ['确定', '取消']
				  ,yes: function(index, layero){
				      $.ajax({
					   	  type: 'GET',
					   	  url: 'http://localhost:8080/tuser/addUser',//发送请求
					   	  dataType : "json",
					   	  data: {tuser:JSON.stringify(user)},
					   	  success: function(result) {
					   		  console.info(result);
					   		  if(result>0){
					   			  alert("新增成功！");
					              window.location.replace("http://localhost:8080/tuser/getUser");  
					   		  }
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