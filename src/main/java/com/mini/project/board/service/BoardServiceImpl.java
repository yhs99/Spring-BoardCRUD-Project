package com.mini.project.board.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardReadLog;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.board.domain.PagingInfo;
import com.mini.project.board.persistance.BoardDAO;
import com.mini.project.member.domain.PagingDTO;
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
		PointLogDTO pointData = PointLogDTO.builder().pointWho(subject.getWriter()).pointWhy("���ۼ�").build();
		int result = dao.insertBoard(subject);
		dao.updateBoardRef();
		int r2 = point_dao.updateMemberPoint(pointData);
		int r3 = point_dao.insertPointLog(pointData);
		if(result > 0 && r2 > 0 && r3 > 0)
			return true;
		else return false;
	}

	@Override
	public BoardVO getBoardByBoardNo(int id, String ip) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("ip", ip);
		int isSaw = dao.selectReadLogByIdAndIp(map);
		if(isSaw < 0) {
			log.info(isSaw + " : ��ȸ�̷��� ����");
			dao.insertBoardReadLog(map);
			dao.updateBoardReadCount(id);
		}else if(isSaw > 0) {
			log.info(isSaw + " : ��ȸ�̷��� �Ϸ簡 ���� ������Ʈ��.");
			dao.updateBoardReadWhen(map);
			dao.updateBoardReadCount(id);
		}
		return dao.selectBoard(id);
	}
	
	@Override
	public BoardVO getBoardByBoardNo(int id) throws Exception {
		return dao.selectBoard(id);
	}

	@Override
	public List<BoardUpFilesVODTO> getBoardUpFiles(int id) throws Exception {
		return dao.selectBoardUpFiles(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean addReply(BoardDTO reply) {
		// 1. �θ���� ù��° ������� Ȯ���Ѵ�.
		// ref = �θ�ref and �θ�refOrder < reply.refOrder
		// 2-1. 1�� �ش� row�� ���ٸ�
		// �� ���� ref = �θ�ref, step = �θ�step+1, refOrder = �θ�refOrder+1�� ����Ѵ�.
		dao.updateBoardsRefOrder(reply.getRef(), reply.getRefOrder());
		// 2-2. 2�� row�� �ִٸ�
		// �ش� row�� refOrder�� +1�� ���ϰ�
		// ���� ����ϴ� ����� ref = �θ�ref, step = �θ�step+1, refOrder = �θ�refOrder+1�� ����Ѵ�.
		reply.setRefOrder(reply.getRefOrder()+1);
		reply.setStep(reply.getStep()+1);
		dao.addReply(reply);

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBoard(int id) {
		dao.deleteIsDelete(id);
		dao.deleteUpFiles(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean modifyBoard(BoardDTO board) throws Exception {
		return false;
	}

	@Override
	public Map<String, Object> getAllBoards(PagingDTO dto) throws Exception {
		PagingInfo info = new PagingInfo(dto);
		makePagingInfo(info);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", dao.selectAllBoards(info));
		map.put("pagingInfo", info);
		return map;
	}
	
	public void makePagingInfo(PagingInfo info) throws Exception {
		info.setTotalPostCnt(dao.getPostTotalCnt());
		info.setTotalPageCnt();
		info.setStartRowIndex();
		info.setPageBlockNoCurPage();
		info.setStartPageNoCurBlock();
		info.setEndPageNoCurBlock();
	}
}
