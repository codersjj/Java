<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no,maximum-scale=1.0">
		
		<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="../css/Normalize.css"/> <!--保证跨浏览器的一致性-->
		<script src="../js/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="../js/docs.min.js" type="text/javascript" charset="utf-8"></script>
		<style>
				/*头部 */
				#head{
					width: 100%;
					height: 70px;
					background-color: #9C2646; 
					color: white;	
					font-size: 20px;
					line-height: 70px;
					position: fixed;
				}
				
				/*订单容器*/
				#head2{
					overflow-y: scroll;
					position: fixed;
					width: 100%;
					height: 100%;
					margin-top: 120px;
					padding-bottom: 200px;
				}
				
				/* 订单号 */
				.masterNum{
					font-weight: bold;
				}
				/*每个订单*/
				.mymaster{
					margin-top: 50px;
				}
				/*第一个订单*/
				.mymaster:nth-of-type(1) { 
					margin: 0px;
				 }
				
				.footer{
					width: 100%;
					height: 50px;
					color: white;
				}
				
				/*返回首页*/
				#goList{
					background-color: black; 
					height: 50px;
					line-height: 40px;
					font-size: 18px;
					position: fixed;
					bottom: 0px;
					color:white;
				}
				
				/*删除全部*/
				#deleteAll{
					position: fixed;
					top: 10%;
					right: 2%;
					color: grey;
					font-size: 14px;
				}
				
				/*回到顶层*/
				#goHead{
					position: fixed;
					bottom: 10%;
					right: 0%;
					font-size: 14px;
				}
				
				/* 条件form */
				#term{
					margin-top: 80px;
					margin-left: 40%;
				}
				#term label{
					color:grey;
				}
				#sub{
					position: relative;
					top: 105px;
				}
				/* 时间二字 */
				#textTime{
					position: relative;
					right: 35%;
					top: 31px;
				}
		</style>
	</head>
	<body>
		<!--保存用户的openId-->
		<input type="hidden" value="${sessionScope.openId}" id="buyer_openId"/>
		
		<div id="head" class="text-center">我的订单</div>
		<!-- 时间查询 -->
		<div id="term" class="pull-left">
             <form role="form" id="timeSelectForm" action="goMymaster" method="post">
             
             	<!-- 保存对应的数量和商品id -->
				<c:forEach items="${info}" var="info">
					<!-- 用于保存对应的食物id -->
					<input type="hidden" name="infoId" value="${info.infoId}"/>
					<!-- 用于保存对应的食物数量 -->
					<input type="hidden" name="nums" value="${info.num}"/>
				</c:forEach>
            	 <div class="form-group">
            	 	<label for="name" id="textTime">时间</label>
    				<select class="selectpicker form-control" id="timeSel" name="timeName">
    					<option value="-1">请选择</option>
    					<option value="day">当天</option>
    					<option value="week">一周内</option>
    					<option value="month">一月内</option>
    				</select>
  				 </div>
            </form>
        </div>
        <span class="btn btn-link" id="sub">查看</span>	
		<div id="head2">	
			<!-- 删除全部按钮 -->
			<span class="btn btn-xs btn-link pull-right" id="deleteAll">删除全部</span>
			<c:forEach var = "oneDetails" items="${allDetails}" varStatus="start">
				<div class="mymaster">
					<table class="table table-striped table-responsive">
						<thead>
							<tr class="master">
								<td>时间</td>
								<td colspan="2">
									${times[start.index]}
								</td>
							</tr>
							<tr class="masterNum">
								<td>订单号</td>
								<td colspan="2">${ods[start.index]}</td>
							</tr>
							</thead>
							<tr>
								<td>商品名</td>
								<td>商品单价</td>
								<td>商品数量</td>
							</tr>
						<c:forEach var = "detail" items="${oneDetails}">
							<tr>
								<td>${detail.product_name}</td>
								<td>${detail.product_price}</td>
								<td>${detail.product_quantity}</td>
							</tr>
						</c:forEach>
						<tr class="masterNum">
							<td></td>
							<td>金额</td>
							<td>${sumPrices[start.index]}</td>
						</tr>
						<tr class="masterNum">
							<td></td>
							<td>预计到达</td>
							<td><span>${goHourMins[start.index]}</span></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>
								<span class="btn btn-warning btn-xs"onclick="cancelMaster(this,'${oderIds[start.index]}')">取消订单</span>
								<input type="hidden" value="${gotimes[start.index]}" class="hiddenOrderTime"/>
								<span class="btn btn-danger btn-xs" onclick="del(this,'${oderIds[start.index]}')">删除订单</span>
							</td>	
						</tr>
					</table>
					<%-- <!--五条一个显示更多-->
					<c:if test="${start.count % 2 eq 0 || start.count eq 2}">
						<div><a class="btn btn-block lookmore">查&nbsp;看&nbsp;更&nbsp;多>></a></div>
					</c:if> --%>
				</div>
				
			</c:forEach>
			<span class="btn btn-xs btn-link pull-right" id="goHead">回到顶部</span>
		</div>
		<div class="footer text-center">
			<span class="btn btn-block" id="goList">返回首页</span>
		</div>
		
		
		
		
		
		<!-- 删除全部提示框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	        <div class="modal-dialog" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                    <h4 class="modal-title" id="myModalLabel">提示</h4>
	                </div>
	                <div class="modal-body">
						您将删除所有的订单信息！包含未完成的订单！
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-default" data-dismiss="modal"><span aria-hidden="true"></span>取消</button>
	                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span  aria-hidden="true"></span>确定</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    
	    
	    
	    
	</body>
	
	<script type="text/javascript">
			/*判断 回到顶部  删除全部是否隐藏*/
			$(function(){
				if($("table").size()!=0){
					$("#deleteAll").show();
					$("#goHead").show();
				}else{
					$("#deleteAll").hide();
					$("#goHead").hide();
				}
				
				//判断订单是否是过去时  过去的则没有取消订单按钮
				var sysDate = new Date();//获取系统时间
				$(".hiddenOrderTime").each(function(){
					var time =  $(this).val().substr(0,$(this).val().lastIndexOf("."));
					//手机兼容
					var arr = time.split(/[- : \/]/);
					var orderTime = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
					if(orderTime<sysDate){
						$(this).prev().hide();
						$(this).next().show();
					}else{
						$(this).prev().show();
						$(this).next().hide();
					}
				})
				
				/* 没有订单则显示图片 */
				if($("table").size()==0){
				    var $divNoMaster = "<div class='center-block'><img src='../img/noMaster.png' width='100%' height='100%' /></div>";
					$("#head2").append($divNoMaster);
				}
				
				
				
				
				/* 显示的更多初始化 */
				/* $(".mymaster:gt(1)").hide(); */
			})
			
	
			/* 用户删除订单 更改订单状态 */
			function del(ele,orderId){
				$.ajax({
					url:"goUpdateState?orderid="+orderId,
					type:"post",
					dataType:"json",
					success:function(data){
						if(data>0){
							ele.parentNode.parentNode.parentNode.parentNode.remove();
						}
						
					}
				})
			}
			
			
			
			
			
			/* 删除全部 */
			$("#deleteAll").click(function(){
				$('#myModal').modal();
			})
			
			
			
			/*删除全部弹出层确定按钮按下*/
			$("#btn_submit").click(function(){
				//修改该用户的所有订单状态
				var openId = $("#buyer_openId").val();
				if(openId!=""){
					 $.ajax({
						url:"deleteAllState?openId="+openId,
						type:"post",
						dataType:"json",
						success:function(data){
							if(data>0){
								$(".mymaster").remove();
								$("#deleteAll").hide();
								$("#goHead").hide();
							}
						}
					})
				}
			})	
			
			
			
			/* 回到顶部 */
			$("#goHead").click(function(){
				$(".mymaster:eq(0)").get(0).scrollIntoView(true);
			})
			
			
			/* 取消订单 */
			function cancelMaster(ele,orderId){
				var result = confirm("您确定取消该订单吗?");
				if(result){
					$.ajax({
						url:"cancelMaster?orderId="+orderId,
						type:"post",
						dataType:"json",
						success:function(data){
							if(data == "-2"){
								alert("您已经过了取消的时间");
							}else if(data == "-3"){
								alert("系统故障!请联系店家");
							}else{
								alert("取消成功~");
								ele.parentNode.parentNode.parentNode.parentNode.remove();
							}
						}
					})
				}
			}
			
			
			
			/*时间查询 */
			$("#sub").click(function(){
				$("#timeSelectForm").submit();
			})
			
			
			
			
			/* 查看更多点击事件 */
			/* $(".lookmore").each(function(i){
				$(this).click(function(){
					/*  (i+1)*2+2-1 乘2加2的数字是显示的条数    i是当前索引     获得隐藏显示的关键数 */
					/* var num = (i+1)*2+2-1;
					var info = ".mymaster:lt("+num+")";
					$(info).show();
					info = ".lookmore:eq("+(i+1)+")";
					$(info).show();
					$(this).remove();
				}) */
			/*}) */
			
			
			
			/*返回首页*/
			$("#goList").click(function(){
				$("#timeSelectForm").attr("action","listAll");
				$("#timeSelectForm").submit();
			})
			

	</script>
</html>