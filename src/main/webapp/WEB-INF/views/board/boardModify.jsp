<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	function addFileInput() {
		let input = `<input type="file" name="modifyNewFile">`;
		$(input).insertAfter($("#fileForm"));
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<form action="./${board.boardNo}" method="POST" enctype="multipart/form-data">
		<div class="container">
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">제목:</label>
		    <input type="text" class="form-control" id="title" name="title" value="${board.title}">
		  </div>
		  <div class="mb-3">
		    <label for="writer" class="form-label">작성자:</label>
		    <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}">
		  </div> 
		  <div class="mb-3">
		    <label for="content" class="form-label">내용:</label>
			<textarea class="form-control" id="content" name="content">${board.content }</textarea>
		  </div>
		  <div id="fileForm" class="mb-3">
		  	
		  </div>
		  <div class="preview" id="preview">
		  </div>
		  <button type="button" class="btn btn-success" onclick="addFileInput();">추가하기</button>
		  <input type="submit" id="modify" class="btn btn-primary" value="수정하기">
		  <button type="button" id="back" class="btn btn-secondary" onclick="window.location='/board'">목록보기</button>
		</div>
	</form>
	<jsp:include page="../footer.jsp"/>
</body>
</html>