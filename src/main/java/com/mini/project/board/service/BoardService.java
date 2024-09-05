package com.mini.project.board.service;

import java.util.List;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.BoardVO;

public interface BoardService {
	List<BoardVO> getAllBoards() throws Exception;
	boolean saveBoard(BoardDTO subject) throws Exception;
	BoardVO getBoardByBoardNo(int id) throws Exception;
	List<BoardUpFilesVODTO> getBoardUpFiles(int id) throws Exception;
}
