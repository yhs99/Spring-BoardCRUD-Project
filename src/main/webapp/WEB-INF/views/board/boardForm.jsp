<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>게시글 작성하기</title>
<style>
	.fileUpload {
		line-height: 200px;
		background-color: gray;
	}
</style>
<script>
let files = new Array();

$(function() {
	$(".fileUpload").on("dragenter dragover", (e) => {
		e.preventDefault();
	})
	
	$(".fileUpload").on("drop", function(e) {
		e.preventDefault();
		for(let file of e.originalEvent.dataTransfer.files) {
			if(file.size > 1024*1024*1) { // 10MB
				alert("파일이 너무 큽니다.")
			}else {
				files.push(file);
				showPreview(file);
				fileUpload(file);
			}
			console.log(files);
		}
	})
})
function submitBoard() {
	var formData = $("#formtag").serialize();
	$.ajax({
		url: "/api/board",
		type: "POST",
		data: formData,
		dataType: "json",
		beforeSend: function() {
			if(!validBoard()) {
				return false;
			}
		},
		success: function(response) {
			console.log(response.success);
		},
		error: function(e) {
			console.log(e);
			return false;
		}
	})
}

function validBoard() {
	let result = false;
	let title = $("#title").val();
	if(title == "") {
		alert("제목을 입력하세요");
		return false;
	}
}

// 이미지 파일이면 미리보기하여 출력
function showPreview(file, path) {
	console.log(path);
	let show = ``;
	let imageType = ["image/jpeg", "image/png", "image/gif"];
	if(imageType.includes(file.type)) {
		show += `<p><image src='../../resources/boardUpFiles/2024/09/05/thumb_\${file.name}'>\${file.name}</p>`;
	}else {
		show += `<p>${file.name}</p>`;
	}
	$('#preview').html(show);
	
}

function fileUpload(file) {
	let fd = new FormData();
	fd.append("file", file);
	$.ajax({
		url: 'api/upfile',
		contentType: false,
	    processData: false,
		type: "POST",
		data: fd,
		dataType: "json",
		success: function(data) {
			showPreview(file, data.data);
		},
		error: function(e) {
			console.log(e);
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
			    <input type="text" class="form-control" id="ti....0t0le" placeholder="제목을 입력하세요" name="title">
			  </div>
			  <div class="mb-3">
			    <label for="writer" class="form-label">작성자:</label>
			    <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요" name="writer">
			  </div> 
			  <div class="mb-3">
			    <label for="content" class="form-label">내용:</label>
				<textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
			  </div>
			  <div class="fileUpload mb-3">
			  	<p>업로드할 파일을 드래그하세요</p>
			  </div>
			  <div class="preview" id="preview">
			  </div>
			  <button type="submit" id="submit" class="btn btn-primary">작성하기</button>
			</form>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>