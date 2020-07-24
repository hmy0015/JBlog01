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
		
		<div id="joinSucceess">		
			<p class="welcome-message">
				<span>감사합니다.<br>
					   회원 가입이 성공적으로 완료되었습니다.<br>
			    </span>
			<br><br>
			<a href="${pageContext.request.contextPath}/user/loginForm">로그인 하기</a>
			</p>
		</div>
		
		
	</div>
</body>
</html>
