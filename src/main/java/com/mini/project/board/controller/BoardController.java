package com.mini.project.board.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.service.BoardService;
import com.mini.project.infra.ApiResponse;
import com.mini.project.infra.ApiResponseUtil;
import com.mini.project.infra.FileProcess;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class BoardController<T> {

	@Inject
	private BoardService service;
	@Inject
	private FileProcess fileProcess;
	/**
	 * @return ResponseEntity<ApiResponse<List<BoardVO>>>
	 */
	@GetMapping("board")
	public ResponseEntity<ApiResponse<?>> boardList(){
		log.info("boardList call");
		try {
			return ResponseEntity.ok().body(ApiResponseUtil.success(service.getAllBoards()));
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(ApiResponseUtil.error("문제가 발생해 데이터를 불러오지 못했습니다."));
		}
	}
	@PostMapping("board")
	public ResponseEntity<ApiResponse<?>> saveBoard(BoardDTO subject) {
		try {
			boolean success = service.saveBoard(subject);
			log.info("작성 성공여부 : " + success);
			if(success) {
				return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseUtil.success(""));
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseUtil.error("작성 실패"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseUtil.error("오류가 발생했습니다."));
		}
	}
	
	@PostMapping("upfile")
	public ResponseEntity<ApiResponse<?>> saveBoardFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
		log.info("파일 전송 요청됨");
		try {
			fileSave(file, req);			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(ApiResponseUtil.success(""));
	}
	
	/**
	 * id값에 바인딩된 게시물 정보 return
	 * @param id
	 * @return ResponseEntity<BoardVO>
	 */
	@GetMapping("board/{id}")
	public ResponseEntity<BoardVO> boardMain(@PathVariable("id") String id) {
		
		return ResponseEntity.noContent().build();
	}
	
	private void fileSave(MultipartFile file, HttpServletRequest req) throws IOException{
		String originalFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		byte[] upfile = file.getBytes();
		String realPath = req.getSession().getServletContext().getRealPath("./resources/boardUpFiles");
		fileProcess.saveFileToRealPath(upfile, realPath, contentType, originalFileName, fileSize);
	}
}
