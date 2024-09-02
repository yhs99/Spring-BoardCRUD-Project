<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>게시글 작성하기</title>
<script>

function submitBoard() {
	var formData = $("#formtag").serialize();
	$.ajax({
		url: "/api/board",
		type: "POST",
		data: formData,
		dataType: "json",
		success: function(response) {
			console.log(response.success);
		},
		error: function(e) {
			console.log(e);
			return false;
		}
	})
}
	
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
		<div class="container">
			<form action="#" id="formtag" onsubmit="return submitBoard()">
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
			  <button type="submit" id="submit" class="btn btn-primary">작성하기</button>
			</form>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>