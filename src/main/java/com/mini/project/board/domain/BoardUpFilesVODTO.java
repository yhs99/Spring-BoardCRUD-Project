package com.mini.project.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardUpFilesVODTO {
	private int boardUpFileNo;
	private String originFileName;
	private String thumbFileName;
	private String ext;
	private long size;
	private int boardNo;
	private String base64Img;
}
