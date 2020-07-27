<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<c:import url="/WEB-INF/views/includes/admin-menu.jsp"></c:import>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="cateName" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
$(document).ready(function(){		
	// 전체 카테고리 리스트 불러오기
	fetchList();
});

// 카테고리 html 그리기
function render(cateVo) {
	var str = '';
	
	str += '<tr id="t' + cateVo.cateNo + '">';
	str += '	<td>' + cateVo.cateNo + '</td>';
	str += '	<td>' + cateVo.cateName + '</td>';
	str += '	<td>' + cateVo.cnt + '</td>';
	str += '	<td>' + cateVo.description + '</td>';
	str += '	<td class="text-center">';
	str += '		<img class="btnCateDel" data-delNo="' + cateVo.cateNo + '"src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
	str += '	</td>';
	str += '</tr>';

	$("#cateList").prepend(str);
}

// 전체 카테고리 리스트 불러오기
function fetchList() {

	$.ajax({
		url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateList",		
		type : "post",

		dataType : "json",
		success : function(cateVo){ /*성공시 처리해야될 코드 작성*/
			for(var i = 0; i < cateVo.length; i++) {
				render(cateVo[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

// 카테고리 추가하기
$("#btnAddCate").on("click", function() {
	
	var cateName = $("[name = 'cateName']").val();
	var description = $("[name = 'description']").val();
	
	$.ajax({
		url : "${pageContext.request.contextPath}/${authUser.id}/admin/cateList",		
		type : "post",
		data : {cateName: cateName,
				description: description},

		dataType : "json",
		success : function(cateVo){ /*성공시 처리해야될 코드 작성*/
			$("[name = 'cateName']").val("");
			$("[name = 'description']").val("");
			render(cateVo);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});

//카테고리 삭제하기
$("#cateList").on("click", "img", function() {
	
	var cateNo = $(this).data("delno");
	
	$.ajax({
		url : "${pageContext.request.contextPath}/admin/delCate",		
		type : "post",
		data : {cateNo: cateNo},

		dataType : "json",
		success : function(count){ /*성공시 처리해야될 코드 작성*/
			if(count == 1) { // 삭제 성공 시
				
				$("#t" + cateNo).remove(); // 해당 게시글 삭제			
				
			} else { // 삭제 실패 시
				
				alert("게시글이 있을 경우 삭제할 수 없습니다.");
				
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
});
</script>
</html>