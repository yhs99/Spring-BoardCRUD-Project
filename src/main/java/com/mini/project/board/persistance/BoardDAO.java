package com.mini.project.board.persistance;

import java.util.List;
import java.util.Map;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.domain.BoardReadLog;
import com.mini.project.board.domain.BoardUpFilesVODTO;
import com.mini.project.board.domain.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectAllBoards() throws Exception;
	int insertBoard(BoardDTO subject) throws Exception;
	BoardVO selectBoard(int id) throws Exception;
	List<BoardUpFilesVODTO> selectBoardUpFiles(int id) throws Exception;
	int selectReadLogByIdAndIp(Map<String, Object> data) throws Exception;
	int insertBoardReadLog(Map<String, Object> data) throws Exception;
	int updateBoardReadCount(int id) throws Exception;
	int updateBoardReadWhen(Map<String, Object> data) throws Exception;
}
