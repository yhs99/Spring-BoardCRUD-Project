package com.mini.project.board.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.domain.PagingInfo;

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

	@Override
	public int checkReplyCnt(BoardDTO reply) {
		return session.selectOne(NS+"cntReplys", reply);
	}

	@Override
	public int updateBoardsRefOrder(int ref, int refOrder) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref", ref);
		map.put("refOrder", refOrder);
		return session.update(NS+"updateBoardRefOrders", map);
	}

	@Override
	public int addReply(BoardDTO reply) {
		return session.insert(NS+"insertReply", reply);
	}

	@Override
	public int updateBoardRef() {
		return session.update(NS+"updateBoardRef");
	}

	@Override
	public int deleteIsDelete(int id) {
		return session.update(NS+"updateIsDelete", id);
	}

	@Override
	public int deleteUpFiles(int id) {
		return session.delete(NS+"deleteUpFiles", id);
	}

	@Override
	public int getPostTotalCnt() throws Exception {
		return session.selectOne(NS+"getPostCnt");
	}

	@Override
	public List<BoardVO> selectAllBoards(PagingInfo info) {
		return session.selectList(NS+"selectAllBoardByLimit", info);
	}
	
}
