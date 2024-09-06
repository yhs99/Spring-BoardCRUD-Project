package com.mini.project.board.persistance;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
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

	@Override
	public BoardVO selectBoard(int id) throws Exception {
		return session.selectOne(NS + "selectBoardByBoardId", id);
	}

	@Override
	public List<BoardUpFilesVODTO> selectBoardUpFiles(int id) throws Exception {
		return session.selectList(NS+"selectBoardUpFiles", id);
	}

	@Override
	public int selectReadLogByIdAndIp(Map<String, Object> data) {
		return session.selectOne(NS+"selectReadLogByIdAndIp", data);
	}

	@Override
	public int updateBoardReadCount(int id) throws Exception {
		return session.update(NS+"updateBoardReadCount",id);
	}

	@Override
	public int updateBoardReadWhen(Map<String, Object> data) throws Exception {
		return session.update(NS+"updateBoardReadLog", data);
	}

	@Override
	public int insertBoardReadLog(Map<String, Object> data) throws Exception {
		return session.insert(NS+"insertBoardReadLog", data);
	}
}
