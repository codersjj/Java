<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 路径 -->
		<base href="<%=basePath%>">
		<title></title>
		<style>
			/**
			 * 自适应响应式手机宽度
			 */
			@media screen and (min-width:600px){
				*{
					padding: 0px;
					list-style: none;
					box-sizing: border-box;
					/*border: 1px solid red;*/
					margin: 0px;
				}
				
				#head1{
					width: 100%;
					height: 200px;
					background-color: #9C2646;
					font-size:60px;
					color: white;
					position: fixed;
					padding-top:8%;
					margin-top: -260px;
				}
				#head1 a{
					text-decoration: none;
					color: white;
					margin-left: 30px;
				}
				#head1 #shopChe{
					display: inline-block;
					margin-left: 35%;
				}
				.head3{
					margin-top: 260px;
					font-size: 40px;
				}
				
				.head3 li{
					margin-left: 60px;
					height: 150px;
					border-top: 1px solid gray;
				}
				
				.head3 img{
					width: 50px;
					height: 50px;
				}
				
				.head3 .productName{
					margin-left: 10px;
					display: inline-block;
					width:250px;
				}
				.head3 .changeNum{
					float: right;
					margin-right: 60px;
					clear:both;
				}
				.head3 #deleteImg{
					width: 60px;
					height: 60px;
					float: right;
					margin-right: 50px;
				}
				.head3 #oneFoodPrice{
					margin-left: 130px;
				}
				.head3 .nums{
					font-size: 50px;
				}
				.head3 ul{
					margin-bottom: 120px;
				}
				
				.footer {
					font-size: 66px;
		            position: fixed;
		            left: 0px;
		            bottom: 0px;
		            width: 100%;
		            height: 120px;
		            background-color: black;
		            color: white;
		            z-index: 9999;
	       	 	}
	       	 	.footer p{
	       	 		margin-left: 20px;
	       	 		margin-top: 20px;
	       	 	}
	       	 	.footer p span{
	       	 		display: inline-block;
	       	 		margin-left: 10px;
	       	 		vertical-align: top;
	       	 	}
	       	 	.footer a{
	       	 		float: right;
	       	 		margin-right: 50px;
	       	 	}
	       	 	.footer #clear{
	       	 		margin-right: 100px;
	       	 	}
	       	 	
	       	 	#yuyue{
	       	 		text-decoration: none;
	       	 		color: white;
	       	 	}
			}
			
		</style>
	</head>
	<body>
		<form id="myForm" action="productInfo/listAll">
			<div id="head1">
				<span id="submit"><</span></span><span id="shopChe">购物车</span>
			</div>
			<div class="head3">
				<ul id="info">
					<c:forEach items="${info}" var="info">
						<!-- 用于保存对应的食物id -->
						<input type="hidden" name="infoId" value="${info.infoId}"/>
						<!-- 用于保存对应的食物数量 -->
						<input type="hidden" name="nums" value="${info.num}"/>
						<!-- 订单总金额  生成订单用-->
						<input type="hidden" name="sumPrice"/>
						<li>
							<span class="productName">${info.name}</span>
							<span id="oneFoodPrice">
								￥<i>${info.price}</i>
							</span>
							<span class="changeNum">
								<img src="img/mins.jpg" class="mis">
								<span class="nums" id="foodNum">${info.num}</span>	
								<img src="img/add.jpg" class="add"/>
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<footer class="footer">
				<p><span id="clear">清空</span><span>总计:</span><span >￥</span><span id="sumPrice">0</span><a id="yuyue">预约</a></p>
			</footer>
		</form>
	</body>
	<script src="js/jquery-1.12.4.js"></script>
	<script>
	
		//计算总金额
		function calcSumPrice(){
			var sumPrice=0;
			//计算总价格
			$(".nums").each(function(){
				//每个列表的单价
				var oneNum =parseInt($(this).text());
				if($(this).text()==""){
			 		oneNum = 0;
			 	}
			
			 var onePrice = parseInt($(this).parent().prev().find("i").text());
			 
			 sumPrice +=oneNum*onePrice;	
				
			})
			$("#sumPrice").text(sumPrice);
		}
	
	
	
		$(function(){
			calcSumPrice();
			$(".add").click(function(){
				var num = parseInt($(this).prev().html());
				$(this).prev().html(num+1);
				if($(this).prev().html()==""){
					$(this).prev().html(1);
					$(this).prev().prev().show();
				}
				
				calcSumPrice();
				//将数量文本值赋给隐藏域用于传值
				$(this).parent().parent().prev().prev().val($(this).prev().html());
			})	
			//数量
			$(".mis").click(function(){
				var num = parseInt($(this).next().html());
				$(this).next().html(num-1);
				if(num<2){
					$(this).parent().parent().hide();
				}else{
					$(this).attr("src","img/mins.jpg");
				}
				calcSumPrice();
				//循环给对应的数量input传值 
				$(this).parent().parent().prev().prev().val($(this).next().html());
			})
					
			$("#clear").click(function(){
				$("#info").remove();
				$("#sumPrice").text(0);
			})
			
			$("#submit").click(function(){
				$("#myForm").submit();
			})
			
			$("#yuyue").click(function(){
				//判断购物车是否有商品
				if($("#info").children().size()==0){
					alert("您还未选购任何商品~");
				}
				else{
					//改变form提交地址
					//给sumPrice赋值
					$("[name='sumPrice']").val($("#sumPrice").text());
					if($("[name='sumPrice']").val()=="0"){
						alert("请去先买一些商品吧~");
					}else{
						$("#myForm").attr("action","master/goDownDan");
					}
					
					$("#myForm").submit();
				}
			})
		})
	</script>
</html>
