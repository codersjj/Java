<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
	<link rel="stylesheet" href="static/login/bootstrap.min.css" />
	 <!--  <style>
	 	
	 		
	 		#mydetilTable tr{
	 			border-bottom: 1px solid #b3d9d9;
	 		}
	 		
        	#mydetilTable td{
        		width: 200px;
        		height: 30px;
        	}
        	
        	#userInfo{
        		font-size: 15px;
        	}
        	#title{
        		font-size: 17px;
        		font-weight: bold;
        	}
        
        </style>
        -->
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<div id="zhongxin" style="padding-top: 13px;">
					<table id="mydetilTable" align="center" class="table table-striped table-condensed table-hover">
    					<tr id="title">
    						<td>商品名称</td>
			    			<td>商品价格</td>
			    			<td>数量</td>
			    		</tr>
			    		<c:forEach items="${details}" var="detail">
			    			<tr class='active'>
				    			<td>${detail.PRODUCT_NAME}</td>
				    			<td>${detail.PRODUCT_PRICE}</td>
				    			<td>${detail.PRODUCT_QUANTITY}</td>
			    			</tr>
			    		</c:forEach>
			    		
			    		<tr id="userInfo" class='active'>
			    			<td>客户手机号</td>
			    			<td>${BUYER_PHONE}</td>
			    			<td>￥<span id="sumPrice">${ORDER_AMOUNT}</span></td>
			    		</tr>
			    	</table>
			    	</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>