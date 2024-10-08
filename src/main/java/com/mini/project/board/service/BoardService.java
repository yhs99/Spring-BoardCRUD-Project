package com.mini.project.board.service;

import java.util.List;
import java.util.Map;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.BoardVO;
import com.mini.project.member.domain.PagingDTO;

public interface BoardService {
	List<BoardVO> getAllBoards() throws Exception;
	Map<String, Object> getAllBoards(PagingDTO dto) throws Exception;
	boolean saveBoard(BoardDTO subject) throws Exception;
	BoardVO getBoardByBoardNo(int id, String ip) throws Exception;
	BoardVO getBoardByBoardNo(int id) throws Exception;
	List<BoardUpFilesVODTO> getBoardUpFiles(int id) throws Exception;
	boolean addReply(BoardDTO reply);
	void deleteBoard(int id);
	boolean modifyBoard(BoardDTO board) throws Exception;
}
