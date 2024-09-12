<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글쓰기</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
		<div class="container">
			<h1>답글쓰기</h1>
			<form action="./reply" method="POST" id="formtag">
			  <div class="mb-3 mt-3">
			    <label for="title" class="form-label">제목:</label>
			    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" name="title">
			  </div>
			  <div class="mb-3">
			    <label for="writer" class="form-label">작성자:</label>
			    <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요" name="writer">
			  </div> 
			  <div class="mb-3">
			    <label for="content" class="form-label">내용:</label>
				<textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
			  </div>
			  <input type="hidden" name="ref" value="${ref}">
			  <input type="hidden" name="step" value="${step}">
			  <input type="hidden" name="refOrder" value="${refOrder}">
			  <button type="submit" id="submit" class="btn btn-primary">작성하기</button>
			</form>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>