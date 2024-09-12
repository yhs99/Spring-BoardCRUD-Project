<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title } | ${board.boardNo }</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	function redirectReply() {
		let uri = '../reply?ref=${board.ref}&step=${board.step}&refOrder=${board.refOrder}'
		window.location = uri;
	}
	function modifyBoard() {
		window.location = "../modify/"+${board.boardNo};
	}
	
	function deleteBoard(boardNo) {
		$.ajax({
			url: '/board/'+boardNo,
			data: {'boardNo' : boardNo},
			type: 'delete',
			dataType: 'json',
			success: function(response) {
				if(response.success === true) {
					alert("삭제 성공");
					window.location='../board';
				}
			},
			error: function(e) {
				alert(e.responseText);
			}
		});
	}
</script>
</head>
<body>
<!-- Modal -->
	<div class="modal fade" id="deleteModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">정말 ${board.boardNo }번 글을 삭제하시겠습니까?</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" onclick="deleteBoard('${board.boardNo}');">삭제하기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>

    </div>
  </div>
</div>
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
		  <div class="mb-3">
		    <label for="readCount" class="form-label">조회수:</label>
		    <input type="text" class="form-control" id="readCount" name="readCount" value="${board.readCount}" readonly>
		  </div> 
		  <div class="preview" id="preview">
		  </div>
		  <button type="button" id="modify" class="btn btn-primary" onclick="modifyBoard();">수정</button>
		  <button type="button" id="reply" class="btn btn-success" onclick="redirectReply();">답글달기</button>
		  <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제하기</button>
		  <button type="button" id="back" class="btn btn-secondary" onclick="window.location='/board'">목록보기</button>
		</div>
	
	<jsp:include page="../footer.jsp"/>
</body>
</html>