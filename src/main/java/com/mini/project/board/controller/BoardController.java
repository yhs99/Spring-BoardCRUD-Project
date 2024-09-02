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
			return ResponseEntity.badRequest().body(ApiResponseUtil.error("������ �߻��� �����͸� �ҷ����� ���߽��ϴ�."));
		}
	}
	@PostMapping("board")
	public ResponseEntity<ApiResponse<?>> saveBoard(BoardDTO subject) {
		try {
			boolean success = service.saveBoard(subject);
			log.info("�ۼ� �������� : " + success);
			if(success) {
				return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseUtil.success(""));
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(ApiResponseUtil.error("�ۼ� ����"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseUtil.error("������ �߻��߽��ϴ�."));
		}
	}
	
	/**
	 * id���� ���ε��� �Խù� ���� return
	 * @param id
	 * @return ResponseEntity<BoardVO>
	 */
	@GetMapping("board/{id}")
	public ResponseEntity<BoardVO> boardMain(@PathVariable("id") String id) {
		
		return ResponseEntity.noContent().build();
	}
}
