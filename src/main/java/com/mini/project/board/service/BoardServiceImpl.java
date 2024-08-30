package com.mini.project.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.persistance.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> getAllBoards() {
		return dao.selectAllBoards();
	}
	
}
