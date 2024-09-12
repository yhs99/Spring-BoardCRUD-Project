package com.mini.project.board.domain;

import com.mini.project.member.domain.PagingDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingInfo {
	private int pageNo; // 현재 페이지 번호
	private int viewPostCntPerPage; // 1페이지당 보여줄 글의 갯수
	
	private int totalPostCnt; // 전체 글(데이터의)갯수
	private int totalPageCnt; // 전체 페이지 수
	private int startRowIndex; // 현재 페이지에서 보여죽 시작할 글의 index 번호
	
	private int pageCntPerBlock = 10; // 1개의 페이징 블럭에서 보여줄 페이징 갯수
	private int pageBlockNoCurPage; // 현재 페이지가 속한 페이징 블럭 번호
	private int startPageNoCurBlock; // 현재 페이징 블럭의 시작 페이지 번호
	private int endPageNoCurBlock; // 현재 페이징 블럭의 마지막 페이징 번호
	
	public PagingInfo(PagingDTO dto) {
		this.pageNo = dto.getPageNo();
		this.viewPostCntPerPage = dto.getPagingSize();
	}
	
	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
	}
	
	public void setTotalPageCnt() {
		if(this.totalPostCnt % this.viewPostCntPerPage == 0) {
			this.totalPageCnt = this.totalPostCnt / this.viewPostCntPerPage;
		}else {
			this.totalPageCnt = (this.totalPostCnt / this.viewPostCntPerPage) + 1;
		}
	}
	
	public void setStartRowIndex() {
		this.startRowIndex = (this.pageNo-1) * this.viewPostCntPerPage;
	}
	
	public void setPageBlockNoCurPage() {
		if(this.pageNo % this.pageCntPerBlock == 0) {
			this.pageBlockNoCurPage = this.pageNo / this.pageCntPerBlock;
		}else {
			this.pageBlockNoCurPage = (this.pageNo / this.pageCntPerBlock)+1;
		}
	}
	
	public void setStartPageNoCurBlock() {
		this.startPageNoCurBlock = (this.pageBlockNoCurPage - 1) * this.pageCntPerBlock + 1;
	}
	
	public void setEndPageNoCurBlock() {
		this.endPageNoCurBlock = this.startPageNoCurBlock + (this.pageCntPerBlock - 1);
	}
}
