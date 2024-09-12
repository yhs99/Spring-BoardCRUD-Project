package com.mini.project.view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.PagingInfo;
import com.mini.project.board.service.BoardService;
import com.mini.project.infra.ApiResponseUtil;
import com.mini.project.infra.GetClientIPAddr;
import com.mini.project.member.domain.PagingDTO;

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
	public String boardLists(@RequestParam(value="pageNo", defaultValue="1") int pageNo,
							 @RequestParam(value="pagingSize", defaultValue = "10") int pagingSize, 
							Model model) {
		try {
			PagingDTO dto = PagingDTO.builder().pageNo(pageNo).pagingSize(pagingSize).build();
			Map<String, Object> result;
			result = service.getAllBoards(dto);
			
			PagingInfo pi = (PagingInfo) result.get("pagingInfo");
			model.addAttribute("boardList", result.get("boardList"));
			model.addAttribute("pagingInfo", pi);
			model.addAttribute("pagingSize", pagingSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@GetMapping("modify/{id}")
	public String modifyBoard(@PathVariable("id") int id, 
							  Model model,
							  HttpServletRequest req) {
		logger.info(id + "번 게시글 조회");
		try {
			logger.info(GetClientIPAddr.getClientIp(req) + " 조회");
			model.addAttribute("board", service.getBoardByBoardNo(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/boardModify";
	}

	@PostMapping("modify/{id}")
	public String modifyBoardPost(@PathVariable("id") int id, 
							  Model model,
							  HttpServletRequest req,
							  @ModelAttribute("modifyNewFile") MultipartFile[] modifyNewFile,
							  BoardDTO board) {
		try {
			for(int i=0; i<modifyNewFile.length; i++) {
				logger.info("fileName : {}", modifyNewFile[i].getOriginalFilename());
			}
			logger.info(board.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/boardModify";
	}
	
	@GetMapping("reply")
	public String boardReply(@RequestParam("ref") int ref,
							 @RequestParam("step") int step,
							 @RequestParam("refOrder") int refOrder,
							 Model model) {
		logger.info("boardReply View call");
		model.addAttribute("ref", ref);
		model.addAttribute("step", step);
		model.addAttribute("refOrder", refOrder);
		return "board/boardReply";
	}
	
	@PostMapping("reply")
	public String boardAddReply(BoardDTO reply) {
		logger.info(reply.toString());
		service.addReply(reply);
		return "redirect:/board";	
	}
	
	@PutMapping("board/{id}")
	public ResponseEntity<Object> modifyBoard(@PathVariable("id") int id, 
											  @ModelAttribute BoardDTO board) {
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@DeleteMapping("board/{id}")
	public ResponseEntity<Object> deleteBoard(@PathVariable("id") int id) {
		logger.info(id + "게시물 삭제");
		try {
			service.deleteBoard(id);
		}catch(Exception e) {
			return ResponseEntity.status(400).body(ApiResponseUtil.error("삭제실패"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponseUtil.success(true));
	}
}
