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
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<form action="productinfo/${msg }.do" name="Form" id="Form" method="post" encType="multipart/form-data">
						<input type="hidden" name="PRODUCT_ID" id="PRODUCT_ID" value="${pd.PRODUCT_ID}"/>
						<!-- 用于修改时保存对应信息 -->
						<input type="hidden" name="CREATE_BY" value="${var.CREATE_BY}"/>
						<input type="hidden" name="CREATE_TIME" value="${var.CREATE_TIME}"/>
						<input type="hidden" name="CREATE_BY" value="${var.CREATE_BY}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品名:</td>
								<td><input type="text" name="PRODUCT_NAME" id="PRODUCT_NAME" value="${pd.PRODUCT_NAME}" maxlength="100" placeholder="这里输入商品名" title="商品名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品价格:</td>
								<td><input type="text" name="PRODUCT_PRICE" id="PRODUCT_PRICE" value="${pd.PRODUCT_PRICE}" maxlength="10" placeholder="这里输入商品价格" title="商品价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品的描述:</td>
								<td><input type="text" name="PRODUCT_DESC" id="PRODUCT_DESC" value="${pd.PRODUCT_DESC}" maxlength="255" placeholder="这里输入商品的描述" title="商品的描述" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品小图:</td>
								<td>
									<img src="${pd.PRODUCT_INFO}" class="img" width="100px" height="100px">
									<input type="file" name="PRODUCT_INFO" id="PRODUCT_INFO" class="imgInput"/>
								</td>

							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">是否售罄:</td>
								<td>
									<c:if test="${pd.PRODUCT_STATUS==1}">
										<input type="radio" name="PRODUCT_STATUS" value="1" checked="checked"/>是
										<input type="radio" name="PRODUCT_STATUS" value="0"/>否
									</c:if>
									
									<c:if test="${pd.PRODUCT_STATUS==0}">
										<input type="radio" name="PRODUCT_STATUS" value="1"/>是
										<input type="radio" name="PRODUCT_STATUS" value="0" checked="checked"/>否
									</c:if>
									
									<c:if test="${empty pd.PRODUCT_STATUS}">
										<input type="radio" name="PRODUCT_STATUS" value="1"/>是
										<input type="radio" name="PRODUCT_STATUS" value="0" checked="checked"/>否
									</c:if>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品类型:</td>
								<td>
									<select name="CATEGORY_TYPE" id="CATEGORY_TYPE">
										<c:forEach items="${categoryList}" var="category">
										<c:if test="${category.CATEGORY_TYPE==pd.CATEGORY_TYPE}">
											<option value="${category.CATEGORY_TYPE}" selected="selected">${category.CATEGORY_NAME}</option>
										</c:if>
										<c:if test="${category.CATEGORY_TYPE!=pd.CATEGORY_TYPE}">
											<option value="${category.CATEGORY_TYPE}">${category.CATEGORY_NAME}</option>
										</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注信息:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="100" placeholder="这里输入备注信息" title="备注信息" style="width:98%;"/></td>
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
			if($("#PRODUCT_NAME").val()==""){
				$("#PRODUCT_NAME").tips({
					side:3,
		            msg:'请输入商品名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_NAME").focus();
			return false;
			}
			if($("#PRODUCT_PRICE").val()==""){
				$("#PRODUCT_PRICE").tips({
					side:3,
		            msg:'请输入商品价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_PRICE").focus();
			return false;
			}
			if($("#PRODUCT_DESC").val()==""){
				$("#PRODUCT_DESC").tips({
					side:3,
		            msg:'请输入商品的描述',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_DESC").focus();
			return false;
			}
			
			
			//图片上传是否验证
// 			if($("#PRODUCT_INFO").val()==""){
// 				$("#PRODUCT_INFO").tips({
// 					side:3,
// 		            msg:'请上传商品小图',
// 		            bg:'#AE81FF',
// 		            time:2
// 		        });
// 				$("#PRODUCT_INFO").focus();
// 			return false;
// 			}
			
			/* 判断是否是数字 */
			/* var checkNum = /^(([1-9]{1}\d*)|(0{1}))(\.\d{2})$/;
			if(!(checkNum.test($("PRODUCT_PRICE").val()))){
				$("#PRODUCT_PRICE").tips({
					side:3,
		            msg:'请输入数字 可带2位小数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_PRICE").focus();
			return false;
			}	 */
			
			
			if($("#PRODUCT_STATUS").val()==""){
				$("#PRODUCT_STATUS").tips({
					side:3,
		            msg:'请选择商品是否下架',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRODUCT_STATUS").focus();
			return false;
			}
			if($("#CATEGORY_TYPE").val()==""){
				$("#CATEGORY_TYPE").tips({
					side:3,
		            msg:'请输入商品类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CATEGORY_TYPE").focus();
			return false;
			}
			if($("#REMARK").val()==""){
				$("#REMARK").tips({
					side:3,
		            msg:'请输入备注信息',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARK").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
			
			
			//判断进入的是修改页面
			if($("[name='PRODUCT_ID']").val()!=""){
				var productId = $("[name='PRODUCT_ID']").val();
				$(".img").attr("src","productinfo/getPhoto_Blob?id="+productId);
			}
			
		});
		//显示上传时的图片
		$(".imgInput").change(function(){
			//判断是添加还是修改
			$(".img").attr("src",URL.createObjectURL($(this)[0].files[0]));
		});
		</script>
</body>
</html>