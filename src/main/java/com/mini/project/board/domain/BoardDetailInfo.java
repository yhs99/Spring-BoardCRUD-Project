package com.mini.project.board.domain;

import java.sql.Timestamp;
import java.util.List;

public class BoardDetailInfo {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Timestamp postDate;
	private int readCount;
	private int ref;
	private int step;
	private int refOrder;
	
	private List<BoardUpFilesVODTO> fileList;
	private String email;
	private String userName;
}
