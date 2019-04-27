<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />

<!-- 路径 -->
<base href="<%=basePath%>">
<title>章鱼猫美食</title>
<style>
/**
			 * 自适应手机宽度
			 */
@media screen and (min-width:600px) {

* {
	list-style
	:
	 
	none
	;
	
					
	padding
	:
	 
	0
	px
	;
	
					
	box-sizing
	:
	 
	border-box
	;
}

body {
	background-color: gainsboro;
}

.head1 {
	width: 100%;
	height: 15%;
	background-color: #C6000A; 
	font-size: 60px;
	color: white;
	margin-top: -260px;
	position: fixed;
}


.head1 span {
	display: inline-block;
}

.head1 #one {
	display: inline-block;
	margin-left: 2%;
	margin-top: 10%;
}

.head1 #two {
	margin-left: 20%;
}

.head1 #three {
	float: right;
	margin-right: 3%;
	margin-top: 9%;
}

.head2 .oneDiv {
	font-size: 30px;
}

.head2 .oneDiv #type {
	width: 25%;
	height: 7%;
	font-size: 45px;
	background-color: #C6000A;
	margin-top:-25px;
	line-height: 130px;
	text-align: center;
	float: left;
	color: white;
	position: fixed;
	z-index: 100;
}

.head2 .oneDiv #type ul {
	padding: 0px;
	margin: 0px;
	color: grey;
	text-align: left;
	overflow-y: scroll;
	height: 1500px;
	margin-top: 130px;
}

.head2 .oneDiv #type ul li {
	padding-left: 20px;
}

.head2 .oneDiv #type ul #productTypeAll {
	background-color: white;
	color: orangered;
	margin-top: -24px;
}

.head2 .oneDiv #all {
	width: 100%;
	height: 7%;
 	background-color: #C6000A;
 	margin-top:-25px;
	font-size: 45px;
	text-align: center;
	line-height: 130px;
	color: white;
	position: fixed;
	z-index: 10;
}

.head3 {
	width: 74%;
	height:72%;
	float: right;
	padding: 0px;
	background-color: white;
	position: fixed;
	top:365px;
	left: 250px;
	overflow-y: scroll;
	
}

.head3 ul {
	margin: 0px;
	list-style: none;
}

.head3 ul li {
	display:flex;
	margin-bottom: 30px;
}

.head3 img {
	width: 140px;
	height: 120px;
}

.head3 .productName {
	font-size: 43px;
	vertical-align: top;
	/* 字体设置为默认手机字体风格*/
	font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif;;
}

.head3 .price {
	font-size: 40px;
}

.head3 .price span {
	color: red;
	font-size: 40px;
}

.head3 .changeNum img {
	width: 60px;
	height: 60px;
}

.head3 .changeNum .nums {
	font-size: 70px;
	margin-left: 10px;
	margin-right: 10px;
}

section {
	margin-top: 260px;
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

.footer p {
	margin-left: 170px;
	margin-top: 20px;
}

.footer p span {
	display: inline-block;
	margin-left: 10px;
	vertical-align: top;
}

.footer p img {
	z-index: 1000;
	width: 150px;
	height: 150px;
	margin-left: 200px;
	margin-top: -25px;
}

/* 搜索框 */
#search {
	width: 300px;
	height: 50px;
	border-radius: 42px;
	border: 2px solid #324B4E;
	background: white;
	transition: .3s linear;
	float: right;
	z-index: 9999999;
	font-size: 40px;
	text-align: center;
}

#search:focus {
	width: 400px;
}

/* 分类二字*/
#typeHead {
	display: inline-block;
	width: 10%;
	height: 7%;
	background-color: #C6000A;
	position: fixed;
	left: 27px;
}

/* 显示商品信息头部的分类名字 */
.typeName{
	background-color: white;
	font-size: 40px;
	color:darkgrey;
}
/* 营业时间 */
#time{
	font-size: 30px;
	position: absolute;
	left: 0%;
	top:6%;
}

/* 我的订单 */
#myMasters{
	font-size: 40px;
	position: absolute;
	right: 2%;
	top:6%;
}
/* 我的订单超链接 */
#myMasters a{
	text-decoration: none;
	color:white;	
}

/* 打烊的照片 */
#closeImg{
	width: 100%;
	height: 100%;
}
}
</style>
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script>
	//计算总金额
	function calcSumPrice() {
		var sumPrice = 0;
		//计算总价格
		$(".nums").each(function() {
			//每个列表的单数
			var oneNum = $(this).text();
			if ($(this).text() == "") {
				oneNum = 0;
			}
			
			var onePrice = parseInt($(this).parent().prev().children().children("span").eq(1).text());
			sumPrice += oneNum * onePrice;

		})
		$("#sumPrice").text(sumPrice);
	}

	$(function() {
		//获取当前时间
		var today = new Date();
		var hour = today.getHours();
		var mins = today.getMinutes();
		
		//获取营业开始和结束时间
		var openStartHour = $("#time").text().substring(6,8);
		var openStartMins = $("#time").text().substring(9,11); 
		var openEndHour = $("#time").text().substring(12,14);
		var openEndMins = $("#time").text().substring(15,17); 
		//分钟截取
		if(openStartMins.substring(0,1)=="0"){
			openStartMins=openStartMins.substring(1,2);
		}
		//小时截取
		if(openStartHour.substring(0,1)=="0"){
			openStartHour=openStartHour.substring(1,2);
		}
		//小时数在营业数中
		if(hour>=openStartHour && openEndHour>=hour){
			if(openStartHour==hour){
				//分钟小于营业开始分钟数
				if(openStartMins>mins){
					$("#listInfo").hide();
					$("#closeImg").show();
					$("#subImg").hide();
				}else{

					/* 判断是否是暂停营业    1是暂停营业*/
					if($("[name='isOpen']").val()=="1"){
						$("#listInfo").hide();
						$("#closeImg").show();
						$("#subImg").hide();
					}else{
						$("#listInfo").show();
						$("#closeImg").hide();
						$("#subImg").show();
					}
				}
			}
			else if(openEndHour==hour){
				//分钟数大于营业开始分钟数
				if(openStartMins<mins){
					$("#listInfo").hide();
					$("#closeImg").show();
					$("#subImg").hide();
				}else{

					/* 判断是否是暂停营业    1是暂停营业*/
					if($("[name='isOpen']").val()=="1"){
						$("#listInfo").hide();
						$("#closeImg").show();
						$("#subImg").hide();
					}else{
						$("#listInfo").show();
						$("#closeImg").hide();
						$("#subImg").show();
					}
				}	
			}
			else{

				/* 判断是否是暂停营业    1是暂停营业*/
				if($("[name='isOpen']").val()=="1"){
					$("#listInfo").hide();
					$("#closeImg").show();
					$("#subImg").hide();
				}else{
					$("#listInfo").show();
					$("#closeImg").hide();
					$("#subImg").show();
				}
			}
		}else{
			$("#listInfo").hide();
			$("#closeImg").show();
			$("#subImg").hide();
		}
		
		
		
		
		
		
		
		//初始化 将数量赋给隐藏数量input
		$(".nums").each(function(){
			var oneNum = $(this).text();
			if($(this).text()==""){
				oneNum=0;
			}
			$(this).prev().val(oneNum);
		})
		
		
		
		//设置减号是否存在
		$(".nums").each(function(){
			if($(this).text()=="0"){
				$(this).text("");
				$(this).prev().prev().hide();
			}
		})
		
		calcSumPrice();
		//添加数量
		$(".add").click(function() {
			var num = parseInt($(this).prev().html());
			$(this).prev().html(num + 1);
			if ($(this).prev().html() == "") {
				$(this).prev().html(1);
				$(this).prev().prev().prev().show();
			}
			calcSumPrice();
			//将文本赋值
			$(this).prev().prev().val($(this).prev().text());
		})
		//数量
		$(".mis").click(function() {
			var num = parseInt($(this).next().next().html());
			$(this).next().next().html(num - 1);
			if (num < 2) {
				$(this).next().next().text("");
				$(this).hide();
			} else {
				$(this).attr("src", "img/mins.jpg");
			}
			calcSumPrice();
			//将文本赋值
			$(this).next().val($(this).next().next().text());
		})

		$(".productType").click(function() {
			$(this).parent().children().css("background-color", "gainsboro");
			$(this).parent().children().css("color", "#808080");
			$(this).css("background-color", "white");
			$(this).css("color", "orangered");
			
			
			//通过类型的名来找到对应显示的页面的位置
			//类型名称
			var typeName = $(this).text();
			$(".typeName").each(function(){
				if($(this).text()==typeName){
						//拿到名字相同的节点的scrollIntoView()方法改变滚动条
						$(this).get(0).scrollIntoView(true);
				}	
			})
		})

		$("#subImg").click(function() {
			//将数量赋值
			$("[name='nums']").each(function() {
				$(this).val($(this).next().html());
			})

			$("#myForm").submit();
		})

		/* 查询 */
		$("#three").click(
				function() {
					//数量值传递
					$("[name='nums']").each(function() {
						$(this).val($(this).next().html());
					})

					//修改form提交地址
					$("#myForm").attr(
							"action",
							"productInfo/listAll?searchFoodName="
									+ $(this).next().val());

					//开始提交
					$("#myForm").submit();
				})
		
		
		/*查看我的订单*/
		$("#myMasters").click(
				function() {
					//数量值传递
					$("[name='nums']").each(function() {
						$(this).val($(this).next().html());
					})

					//修改form提交地址
					$("#myForm").attr(
							"action",
							"productInfo/goMymaster");

					//开始提交
					$("#myForm").submit();
				})		
			
				
		//判断商品是否售罄 如果售罄则禁止添加
		$(".shouxin").each(function(){
			if($(this).html()!=""){
				//将节点禁止点击
				var addImg = $(this).parent().parent().next().children("img").eq(1);
				addImg.hide();
			}
			if($(this).html()==""){
				//将节点禁止点击
				var addImg = $(this).parent().parent().next().children("img").eq(1);
				addImg.show();
			}
		})
		

	})
	
</script>
</head>
<body>
	<input type="hidden" name="isOpen" value="${seller.ISOPEN }"/>
	<!--标题栏-->
	<form id="myForm" action="productInfo/goShopCart" method="post">
		<div class="head1">
			<span id="one">点菜</span><span id="two">章鱼猫美食</span> 
			<!-- <span id="three">查询</span> <input id="search" type="text" placeholder="输入食物名称" /> -->
			<span id="time">营业时间:&nbsp;${seller.OPENSTART}-${seller.OPENEND}</span>
			<span id="myMasters">我的订单</span>
		</div>
		<section class="headBig2">
		<div class="head2">
			<div class="oneDiv">
				<div id="type">
					<span id="typeHead">分类</span>
					<ul>
						<li class="productType" id="productTypeAll" onclick="$('.head3').scrollTop(0)">全部</li>
						<c:forEach var="CATEGORY" items="${categoryList}">
							<li class="productType">${CATEGORY.CATEGORY_NAME}</li>
						</c:forEach>
					</ul>
				</div>
				<div id="all">全部</div>
			</div>
		</div>
		<div class="head3">
			<ul id="listInfo">
				<!-- 判断是否是模糊查询 -->
				<c:if test="${empty productInfoList}">
				<!-- 判断是否从购物车返回 -->
					<c:if test="${not empty pdnums }">
						<c:forEach items="${productInfoLists}" var="productInfos"
							varStatus="status1">
							<!-- 判断该类型其中是否有食物 如没有则不显示 -->
							<c:if test="${fn:length(productInfos)!=0}">
								<div class="typeName">${pdTypes[status1.index].CATEGORY_NAME}</div>
								<hr>
							</c:if>
							<c:forEach items="${productInfos}" var="productInfo"
								varStatus="status2">
								<!-- 用于保存Id -->
								<input type="hidden" value="${productInfo.product_id}"
									name="PRODUCT_ID" />
								<!-- 如果id相等则赋值 -->
								<li>
									<img src="productInfo/getPhoto_Blob?id=${productInfo.product_id}"/>
									<input type="hidden" value="${productInfo.product_name}" name="PRODUCT_NAME"/>
									<div style="display:flex;">
										<div style="display:flex;flex-wrap:wrap;width:300px;margin-left:20px">
											<div style="width:100%;"><span class="productName">${productInfo.product_name}</span></div>
											<input type="hidden" value="${productInfo.product_price}" name="PRODUCT_PRICE"/>
											
											<!-- 判断是否售罄 -->
												<c:if test="${productInfo.product_status==0}">
													<div class="price" style="width:100%;">
														￥<span>${productInfo.product_price}</span>/份
														 <span class="shouxin"></span>
													</div>
												</c:if>
												<c:if test="${productInfo.product_status==1}">
													<div class="price" style="width:100%;">
														￥<span>${productInfo.product_price}</span>/份
														<span class="shouxin">售罄</span>
													</div>
												</c:if>
										</div>
										<p class="changeNum">
											<img style="border-radius: 50%" src="img/mins.jpg" class="mis"/> 
											<input type="hidden" value="${pdnums[status1.index][status2.index].pdnum}" name="nums"/> 
											<span class="nums" id="num">${pdnums[status1.index][status2.index].pdnum}</span>
											<img style="border-radius: 50%" src="img/add.jpg" class="add" />
										</p>
									</div>
								</li>
							</c:forEach>
						</c:forEach>
					</c:if>
					<c:if test="${empty pdnums}">
						<c:forEach items="${productInfoLists}" var="productInfos">
							<div class="typeName">${pdTypes[status1.index].CATEGORY_NAME}</div>
							<hr>
							<c:forEach var="productInfo" items="${productInfos}">
								<!-- 用于保存Id -->
								<input type="hidden" value="${productInfo.product_id}"
									name="PRODUCT_ID" />
								<li>
									<!-- ${productInfo.product_info} 图片路径 --> 
									<img src="productInfo/getPhoto_Blob?id=${productInfo.product_id}"/>
									<input type="hidden" value="${productInfo.product_name}" name="PRODUCT_NAME"/>
									<div style="display:flex;">
										<div style="display:flex;flex-wrap:wrap;width:300px;margin-left:20px">
											<div style="width:100%;"><span class="productName">${productInfo.product_name}</span></div>
											<input type="hidden" value="${productInfo.product_price}" name="PRODUCT_PRICE"/>
											<div class="price" style="width:100%;">
												￥<span>${productInfo.product_price}</span>/份
												<span class="shouxin"></span>
											</div>
										</div>
										<p class="changeNum">
											<img style="border-radius: 50%" src="img/mins.jpg" class="mis"/> 
											<input type="hidden" value="" name="nums"/> 
											<span class="nums" id="num">0</span>
											<img style="border-radius: 50%" src="img/add.jpg" class="add" />
										</p>
									</div>
								</li>
								
							</c:forEach>
						</c:forEach>
					</c:if>
				</c:if>
				<!-- 模糊查询的数据 -->
				<c:if test="${not empty productInfoList}">
					<c:forEach var="productInfo" items="${productInfoList}" varStatus="start">
							<!-- 用于保存Id -->
							<input type="hidden" value="${productInfo.PRODUCT_ID}"
								name="PRODUCT_ID" />
							<li>
								<!-- ${productInfo.product_info} 图片路径 --> 
								<img src="productInfo/getPhoto_Blob?id=${productInfo.PRODUCT_ID}"/>
								<input type="hidden" value="${productInfo.PRODUCT_NAME}" name="PRODUCT_NAME"/>
								<div style="display:flex;">
									<div style="display:flex;flex-wrap:wrap;width:300px;margin-left:20px">
										<div style="width:100%;"><span class="productName">${productInfo.PRODUCT_NAME}</span></div>
										<input type="hidden" value="${productInfo.PRODUCT_PRICE}" name="PRODUCT_PRICE"/>
										<div class="price" style="width:100%;">
											￥<span>${productInfo.PRODUCT_PRICE}</span>/份
										</div>
									</div>
									<p class="changeNum">
										<img style="border-radius: 50%" src="img/mins.jpg" class="mis"/> 
										<input type="hidden" value="" name="nums"/>
										<!-- 将id相等的数量赋值 -->
										<c:forEach items="${info}" var="inf" varStatus="ind">
											<c:if test="${inf.infoId==productInfo.PRODUCT_ID}">
												<span class="nums" id="num">${inf.num}</span>
												<span class="shouxin"></span>
											</c:if>
										</c:forEach>
										<img style="border-radius: 50%" src="img/add.jpg" class="add" />
									</p>
								</div>
							</li>
						</c:forEach>	
				</c:if>
			</ul>
			<img id="closeImg" src="img/close.jpg"/>
		</div>
		
		</section>
		<footer class="footer">
		<p>
			<span>总计:</span><span>￥</span><span id="sumPrice" name="sumPrice">0</span>
			<img class="goByList" src="img/buyLogo.png" id="subImg"/>
		</p>
		</footer>
	</form>
</body>
</html>




 
