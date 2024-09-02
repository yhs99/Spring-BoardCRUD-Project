package com.mini.project.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.service.BoardService;
import com.mini.project.infra.ApiResponse;
import com.mini.project.infra.ApiResponseUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class BoardController<T> {

	@Inject
	private BoardService service;
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
	
	/**
	 * id값에 바인딩된 게시물 정보 return
	 * @param id
	 * @return ResponseEntity<BoardVO>
	 */
	@GetMapping("board/{id}")
	public ResponseEntity<BoardVO> boardMain(@PathVariable("id") String id) {
		
		return ResponseEntity.noContent().build();
	}
}
