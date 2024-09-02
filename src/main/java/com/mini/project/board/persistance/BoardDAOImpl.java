package com.mini.project.board.persistance;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private final static String NS = "com.mini.mappers.boardMapper.";
	
	@Inject
	private SqlSession session;
	
	@Override
	public List<BoardVO> selectAllBoards() throws Exception {
		return session.selectList(NS+"selectAllBoardLists");
	}

	@Override
	public int insertBoard(BoardDTO subject) throws Exception {
		return session.insert(NS + "saveNewBoard", subject);
	}
}
