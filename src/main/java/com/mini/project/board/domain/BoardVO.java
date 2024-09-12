package com.mini.project.board.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Timestamp postDate;
	private int readCount;
	private int ref;
	private int step;
	private int refOrder;
	private char isDelete;
	
	private List<BoardUpFilesVODTO> fileList;
	
	public void addList(BoardUpFilesVODTO file) {
		fileList.add(file);
	}
}
