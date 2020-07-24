<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>		
		
		<div id="loginForm">
			<form method="post" action="${pageContext.request.contextPath}/user/login">
	      		<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="textId">아이디</label></td>
		      			<td><input id="textId" type="text" name="id"></td>
		      		</tr>
		      		<tr>
		      			<td><label for="textPassword">패스워드</label> </td>
		      			<td><input id="textPassword" type="password" name="password"></td>   
		      			   			
		      		</tr>
		      		 
					<c:if test="${param.result == 'fail'}">
			      		<tr>
			      			<td colspan="2" id="tdMsg" colspan="2">
			      				<span>
			      					<font color="red" size="2pt">
			      						* 아이디 또는 비밀번호가 일치하지 않습니다.
		      						</font>
	      						</span>
			      			</td>
			      		</tr> 
	      		 	</c:if>
		      		
		      	</table>
	      		<div id="btnArea"><br>
					<button class="btn" type="submit" >로그인</button>
				</div>
	      		
			</form>
		
		</div>
		
	</div>
	
</body>

</html>