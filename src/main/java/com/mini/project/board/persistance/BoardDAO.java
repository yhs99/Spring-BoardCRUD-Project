package com.mini.project.board.persistance;

import java.util.List;

import com.mini.project.board.domain.BoardVO;

public interface BoardDAO {
	List<BoardVO> selectAllBoards();
}
