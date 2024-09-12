package com.mini.project.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	private String title;
	private String content;
	private String writer;
	private int ref;
	private int step;
	private int refOrder;
	private int readCount;
}
