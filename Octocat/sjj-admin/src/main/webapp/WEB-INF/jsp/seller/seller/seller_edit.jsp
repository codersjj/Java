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
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<input type="hidden" value="${startHour }" id="startH"/>
<input type="hidden" value="${startMin }" id="startM"/>
<input type="hidden" value="${endHour }" id="endH"/>
<input type="hidden" value="${endMin }" id="endM"/>
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="seller/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商家店名:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="50" placeholder="这里输入商家店名" title="商家店名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">地址:</td>
								<td><input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="100" placeholder="这里输入地址" title="地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商家公告:</td>
								<td><input type="text" name="ANNOUNCEMENT" id="ANNOUNCEMENT" value="${pd.ANNOUNCEMENT}" maxlength="200" placeholder="这里输入商家公告" title="商家公告" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">营业开始时间:</td>
								<td>
									<select name="startHour" id="startHour">
										<c:forEach  begin="0" end="23" varStatus="start">
											<c:if test="${start.index<10}">
												<option value="0${start.index}">0${start.index}</option>
											</c:if>
											<c:if test="${start.index>=10}">
												<option value="${start.index}">${start.index}</option>
											</c:if>
										</c:forEach>
									</select>
									:
									<select name="startMins" id="startMins">
										<c:forEach begin="0" end="59" varStatus="start">
											<c:if test="${start.index<10}">
												<option value="0${start.index}">0${start.index}</option>
											</c:if>
											<c:if test="${start.index>=10}">
												<option value="${start.index}">${start.index}</option>
											</c:if>
										</c:forEach>
									</select>
									
<%-- 									<input type="text" name="OPENSTART" id="OPENSTART" value="${pd.OPENSTART}" maxlength="50" placeholder="这里输入营业开始时间" title="营业开始时间" style="width:98%;"/> --%>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">营业结束时间:</td>
								<td>
<%-- 									<input type="text" name="OPENEND" id="OPENEND" value="${pd.OPENEND}" maxlength="50" placeholder="这里输入营业结束时间" title="营业结束时间" style="width:98%;"/> --%>
									<select name="endHour" id="endHour">
										<c:forEach  begin="0" end="23" varStatus="start">
											<c:if test="${start.index<10}">
												<option value="0${start.index}">0${start.index}</option>
											</c:if>
											<c:if test="${start.index>=10}">
												<option value="${start.index}">${start.index}</option>
											</c:if>
										</c:forEach>
									</select>
									:
									<select name="endMins" id="endMins">
										<c:forEach begin="0" end="59" varStatus="start">
											<c:if test="${start.index<10}">
												<option value="0${start.index}">0${start.index}</option>
											</c:if>
											<c:if test="${start.index>=10}">
												<option value="${start.index}">${start.index}</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
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
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入商家店名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#ADDRESS").val()==""){
				$("#ADDRESS").tips({
					side:3,
		            msg:'请输入地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ADDRESS").focus();
			return false;
			}
			if($("#ANNOUNCEMENT").val()==""){
				$("#ANNOUNCEMENT").tips({
					side:3,
		            msg:'请输入商家公告',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ANNOUNCEMENT").focus();
			return false;
			}
			if($("#OPENSTART").val()==""){
				$("#OPENSTART").tips({
					side:3,
		            msg:'请输入营业开始时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OPENSTART").focus();
			return false;
			}
			if($("#OPENEND").val()==""){
				$("#OPENEND").tips({
					side:3,
		            msg:'请输入营业结束时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OPENEND").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
			
			
			$("#startHour option").each(function(){
				if($(this).val()==$("#startH").val()){
					$(this).attr("selected",true);
				}
			})
			$("#startMins option").each(function(){
				if($(this).val()==$("#startM").val()){
					$(this).attr("selected",true);
				}
			})
			$("#endHour option").each(function(){
				if($(this).val()==$("#endH").val()){
					$(this).attr("selected",true);
				}
			})
			$("#endMins option").each(function(){
				if($(this).val()==$("#endM").val()){
					$(this).attr("selected",true);
				}
			})
			
		});
		</script>
</body>
</html>