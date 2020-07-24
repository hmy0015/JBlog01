<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>		

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" id="input-uid" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">중복체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
	</div>

</body>

<script type="text/javascript">
$("#btnIdCheck").on("click", function() {
	var uId = $("#input-uid").val();
	
	$.ajax({
		url : "${pageContext.request.contextPath}/user/idcheck",		
		type : "post",
		data : {id: uId},

		dataType : "json",
		success : function(userVo){ // function(변수명:컨트롤러에서 넘어오는 거랑 상관없이 새로 변수 만드는 거임)
			
			/*성공 시 처리해야될 코드 작성*/
			
			if(userVo == true) {
				$("#tdMsg").html("<font color='blue'>* 사용 가능한 아이디입니다.</font>");
			}
			else {
				console.log($("#input-uid").val(""));
				$("#tdMsg").html("<font color='red'>* 사용할 수 없는 아이디입니다.</font>");
			}
		},
		error : function(XHR, status, error) {
			/* 실패 시 처리해야될 코드 작성*/

			console.error(status + " : " + error);
		}
	});
	
});

// 약관 동의
$("#joinFormTag").on("click", function(){
	var agree = $("#chkAgree").is(":checked"); // 해당 아이디를 가진 태그에 체크가 되어있는 지를 확인하는 함수
	
	if(agree == true) { /* 체크 한 경우 */
		return true;
	} 
	else { /* 체크하지 않은 경우 */
		alert("약관동의가 필요합니다."); // 팝업창 띄우기
		return false;
	}
});

</script>

</html>