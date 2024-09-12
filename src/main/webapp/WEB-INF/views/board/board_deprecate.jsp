<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>게시판</title>
  <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	window.onload = function() {
		getBoardData();
	}

	function getBoardData() {
		$.ajax({ 
			url: "/api/board",
			dataType: "json",
			type:"get",
			beforeSend: function() {
				
			},
			success: function(response) {
				renderTable(response.data);
			},
			error: function(e) {
				let eMessage = JSON.parse(e.responseText);
			    $('#modal-body').html(eMessage.message);
			    let modal = new bootstrap.Modal(document.getElementById('myModal'))
			    modal.show();;
			}
		});
	}
	function renderTable(datas) {
		let output = "";
		let now = new Date();
	    const twoH = 2 * 60 * 60 * 1000;
		if(datas.length > 0) {
			output += `<table class="table table-striped table-hover">
							<thead>
								<tr>
									<td>#</td>
									<td>글제목</td>
									<td>작성자</td>
									<td>작성일</td>
									<td>조회수</td>
								</tr>
							</thead>
							<tbody>`;
			$.each(datas, function(index, item) {
				let create_at = new Date(item.postDate);
				let twoHourLater = now-create_at;
				if(item.isDelete == 'Y') {
					output += `<tr>`;
				}else {
					output += `<tr onclick="window.location='/board/\${item.boardNo}'" style='cursor:pointer;'>`
				}
					output+= `<td>\${item.boardNo}</td>
					<td>`;
				for(let i=0; i<item.step; i++) {
					if(i==item.step-1) {
						output += `&nbsp;ㄴ `;
					}else {
						output += `&nbsp;&nbsp;&nbsp;`;
					}
				}
				output += `\${item.title}`;
				if(twoH >= twoHourLater)
					output+= ` <span class="badge bg-warning text-dark">NEW</span>`;
				output += `</td>
					<td>\${item.writer}</td>
					<td>\${create_at.getFullYear()}. \${create_at.getMonth()+1}. \${create_at.getDate()} \${create_at.getHours()}:\${create_at.getMinutes()}</td>
					<td>\${item.readCount}</td>
				</tr>`;
			})
			output += `		</tbody>
						</table>`;
		}
		$(".table-container").html(output);
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container">
		<button class="btn btn-primary mt-3 mb-3" onclick="window.location='./boardForm'">글 작성</button>
		<div class="table-container">
		</div>
		<ul class="pagination">
		  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
		  <li class="page-item"><a class="page-link" href="#">1</a></li>
		  <li class="page-item active"><a class="page-link" href="#">2</a></li>
		  <li class="page-item"><a class="page-link" href="#">3</a></li>
		  <li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>
	
	<div class="modal" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">에러발생</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" id="modal-body">
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
	      </div>
	
	    </div>
	    
	  </div>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>
