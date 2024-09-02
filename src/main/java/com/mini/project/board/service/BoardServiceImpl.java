package com.mini.project.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.persistance.BoardDAO;
import com.mini.project.member.domain.PointLogDTO;
import com.mini.project.point.persistance.PointLogDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO dao;
	@Inject
	private PointLogDAO point_dao;
	
	@Override
	public List<BoardVO> getAllBoards() throws Exception{
		return dao.selectAllBoards();
	}

	@Override
	@Transactional(rollbackFor = SQLException.class)
	public boolean saveBoard(BoardDTO subject) throws Exception {
		PointLogDTO pointData = PointLogDTO.builder().pointWho(subject.getWriter()).pointWhy("±ÛÀÛ¼º").build();
		int result = dao.insertBoard(subject);
		int r2 = point_dao.updateMemberPoint(pointData);
		int r3 = point_dao.insertPointLog(pointData);
		if(result > 0 && r2 > 0 && r3 > 0)
			return true;
		else return false;
	}
	
}
