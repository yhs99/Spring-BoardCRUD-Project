<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title } | ${board.boardNo }</title>
<script>
	function modifyBoard() {
		
	}
	
	function deleteBoard() {
		
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
		<div class="container">
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">제목:</label>
		    <input type="text" class="form-control" id="title" name="title" value="${board.title}" readonly>
		  </div>
		  <div class="mb-3">
		    <label for="writer" class="form-label">작성자:</label>
		    <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}" readonly>
		  </div> 
		  <div class="mb-3">
		    <label for="content" class="form-label">내용:</label>
			<textarea class="form-control" id="content" name="content" readonly>${board.content }</textarea>
		  </div>
		  <div class="preview" id="preview">
		  	${files}
		  </div>
		  <button type="button" id="modify" class="btn btn-primary" onclick="modifyBoard();">수정</button>
		  <button type="button" id="delete" class="btn btn-warning" onclick="deleteBoard();">삭제</button>
		  <button type="button" id="back" class="btn btn-secondary" onclick="window.location='/board'">목록보기</button>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>