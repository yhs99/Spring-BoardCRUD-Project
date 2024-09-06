package com.mini.project.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.service.BoardService;
import com.mini.project.infra.GetClientIPAddr;

@Controller
public class ViewController {

	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	private List<BoardUpFilesVODTO> uploadFileList = new ArrayList<BoardUpFilesVODTO>();

	@Inject
	private BoardService service;
	
	@GetMapping("")
	public String index() {
		logger.info("index call");
		return "index";
	}
	
	@GetMapping("board")
	public String boardLists() {
		logger.info("boardLists Controller call");
		return "board/board";
	}
	
	@GetMapping("boardForm")
	public String boardForm() {
		logger.info("boardForm URL call");
		return "board/boardForm";
	}
	
	@GetMapping("board/{id}")
	public String boardMain(@PathVariable("id") int id, Model model, HttpServletRequest req) {
		logger.info(id + "번 게시글 조회");
		try {
			logger.info(GetClientIPAddr.getClientIp(req) + " 조회");
			model.addAttribute("board", service.getBoardByBoardNo(id, GetClientIPAddr.getClientIp(req)));
			model.addAttribute("files", service.getBoardUpFiles(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/boardDetail";
	}
	
	@PutMapping("board/{id}")
	public ResponseEntity<Object> modifyBoard(@PathVariable("id") int id, 
											  @ModelAttribute BoardDTO board) {
		return ResponseEntity.ok(true);
	}
	
	@DeleteMapping("board/{id}")
	public ResponseEntity<Object> deleteBoard(@PathVariable("id") int id) {
		return ResponseEntity.ok(true);
	}
}
