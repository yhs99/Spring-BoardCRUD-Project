package com.mini.project.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	
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
}
