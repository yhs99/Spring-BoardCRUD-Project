package com.mini.project.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("api")
public class BoardController<T> {

	@Inject
	private BoardService service;
	/**
	 * @return ResponseEntity<List<BoardVO>>
	 */
	@GetMapping("board")
	public ResponseEntity<Map<String, List<BoardVO>>> boardList(){
		Map<String, List<BoardVO>> map = new HashMap<>();
		map.put("data", service.getAllBoards());
		System.out.println(map.get("data"));
		return ResponseEntity.ok().body(map);
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
