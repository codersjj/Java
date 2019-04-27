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
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" /> -->
		<!-- 路径 -->
		<base href="<%=basePath%>">
		<title></title>
		<!-- <link rel="stylesheet" href="static/login/bootstrap.min.css"/> -->
		<link rel="stylesheet" href="css/bootstrap.css"/>
		<style>
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
						padding-top:8%;
						text-align: center;
					}
					#info{
						width: 90%;
						margin: 0px auto;
						/*background-color: red;*/
						font-size: 50px;
						margin-top:10%;
						
					}
					#info input{
						/* width: 400px;
						height: 60px; */
						/* float: right; */
						/* margin-left: 190px; */
					}
					/*手机号码输入框*/
					#info #phoneNum{
						margin-top: 1%;
					}
					 #info #selectOne{
						width: 20%;
						height: 10%;
					}
					#info #selectTwo{
						width: 20%;
						height: 10%;
					} 
					#info tr{
						display: inline-block;
						margin-bottom: 5%;
						margin-top: 5%;
						width: 100%;
					}
					#info tr td{
						width: 100%;
					}
					#xiaDingDan{
						font-size: 1em;
						border: 0px;
						width: 35%;
/* 						background-color: crimson; */
/* 						color: white; */
						position:relative;
						left:30%;
						text-align: center;
					}
					#goShopCart{
						float: left;
						margin-left: 40px;
						color:white;
					}
					
					
					.input{
						float: right; 
						margin-right: 2%;	
						width: 70%;
					}
					#REMARK{
						float: right;
						margin-right: 2%;
						width: 70%;
					}
					td{
						display: inline-block;
					}
					
					
				}
		</style>
	</head>
	<body>
		<div id="head1">
			<!-- <span href="productInfo/listAll"> --><span id="goShopCart"><</span>
			预定
		</div>
		<div id="info">
			<form id="goDingDan" action="master/saveDingDan" method="post" >
			<!-- 用于页面传值生成详细订单-->
			<input type="hidden" name="sumPrice" value="${sumPrice}"/>
			<c:forEach items="${ids}" var = "id">
				<input type="hidden" name="infoId" value="${id}"/>
			</c:forEach>
			<c:forEach items="${nums}" var = "num">
				<input type="hidden" name="nums" value="${num}"/>
			</c:forEach>
			
			
				<table class="table">
					<tr>
						<td>
							姓名:<input type="text" name="BUYER_NAME" class="input"/>
						</td>
					</tr>
					<tr>
						<td>
							手机号码:
							<input type="text" id="phoneNum" name="BUYER_PHONE" class="input"/>
						</td>
					</tr>
					<tr>
						<td>
							备注:
							<input type="text" id="REMARK" name="REMARK" class="REMARK" placeholder="口味，偏好等要求"/>
						</td>
					</tr>
					<tr>
						<td id="yujitime">预计到店时间:
							<select id="selectOne" name="hour" class="btn-group dropdown">
								<c:forEach var ="hours" begin="${hour}" end="${yingYeEndhour}">
									<c:if test="${hours==hour}">
										<option value="${hours}" selected="selected">${hours}</option>
									</c:if>
									<c:if test="${hours!=hour}">
										<option value="${hours}">${hours}</option>
									</c:if>
								</c:forEach>
							</select>
							:
							<select id="selectTwo" name="minute">
								<c:forEach var ="minutes" begin="1" end="59">
									<c:if test="${minutes==minute}">
										<option value="${minutes}" selected="selected">
											<c:if test="${minutes>9}">
												${minutes}
											</c:if>
											<c:if test="${minutes<10}">
												0${minutes}
											</c:if>
										</option>
									</c:if>
									<c:if test="${minutes!=minute}">
										<option value="${minutes}">
											<c:if test="${minutes>9}">
												${minutes}
											</c:if>
											<c:if test="${minutes<10}">
												0${minutes}
											</c:if>
										</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<!-- 完成按钮 -->
					<tr>
						<td id="ok"><input  value="完   成" id="xiaDingDan" class="btn btn-danger"/></td>
					</tr> 
				</table>
				<!-- 保存用户的openId -->
				<inut type="hidden" name="BUYER_OPENID" value="${sessionScope.openId}"/>
			</form>
		</div>
		
	</body>
	<script src="js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		/*验证所有信息填写完整 */
		function check(){
			var result = false;
			//非空
			$("[class='input']").each(function(){
				if($(this).val()==""){
					alert("请将信息填写完整");
					result = true;
					return false;
				}
			})
			/* 结束上方约束 则下方忽略 */
			if(result){
				return false;
			}
			//判断手机号码
			if(!(/^1[34578]\d{9}$/.test($("#phoneNum").val()))){ 
		        alert("手机号码有误，请重新填写手机号");  
		        return false; 
		    } 
			return true;
		}
		$(function(){
			$("#xiaDingDan").click(function(){
				if(check()){
					$("#goDingDan").submit();
				}
			})
			
			//返回首页
			$("#goShopCart").click(function(){
				$("#goDingDan").attr("action","productInfo/listAll");
				$("#goDingDan").submit();
			})
		})
	</script>
</html>
