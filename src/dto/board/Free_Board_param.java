package dto.board;

import java.util.Date;

public class Free_Board_param {

	//검색
	
	//정렬
	
	//페이징
	private int pageNum;
	private int curPage; //현재 페이지 
	
	
	
	private int totalCount;	//총 게시글 수 (DB 조회 결과로 얻어옴)
	private int listCount;	//한 페이지당 출력될 게시글 수 (직접 설정함)
	private int totalPage;// 총페이지수 
	
	

	private int startPage;	//화면에 보이는 시작 페이지 (계산으로 알아냄)
	private int endPage;	//화면에 보이는 끝 페이지 (계산으로 알아냄)
	
	private int startNo;	//게시물리스트 첫 번호
	private int endNo;	//게시물리스트 마지막 번호

public Free_Board_param(){};

// 총 게시글 수만 입력하는 생성자
//	curPage == 1
//	pageCount == 10
//	listCount == 10
public Free_Board_param(int totalCount) {
	this.setTotalPage(totalCount);
	
	makePaging();
}

// 총 게시글 수와 현재 페이지를 입력하는 생성자
//	pageCount == 10
//	listCount == 10
public Free_Board_param(int totalCount, int curPage) {
	this.setTotalPage(totalCount);
	this.setCurPage(curPage);
	
	makePaging();
}

// 총 게시글 수와 현재 페이지, 보여지는 게시글 수를 입력하는 생성자
//	pageCount == 10
public Free_Board_param(int totalCount, int curPage, int listCount) {
	this.setTotalPage(totalCount);
	this.setCurPage(curPage);
	this.setListCount(listCount);
	
	makePaging();
}

// 총 게시글 수와 현재 페이지, 보여지는 게시글 수, 페이지 수를 입력하는 생성자
public Free_Board_param(int totalCount, int curPage, int listCount, int pageNum) {
	this.setTotalPage(totalCount);
	this.setCurPage(curPage);
	this.setListCount(listCount);
	this.setPageNum(pageNum);
	
	makePaging();
}
	
	// 페이징 정보 생성
	private void makePaging() {
		if(totalCount == 0)	return; //게시글이 없는 경우
		
		// 기본값 설정
		if(curPage == 0)	setCurPage(1);	//기본값으로 첫 페이지(1) 세팅
		if(pageNum == 0)	setPageNum(10); //한 화면에 보이는 페이지수 기본값(10) 세팅
		if(listCount == 0)	setListCount(10); //한 화면에 보이는 게시글수 기본값(10) 세팅
	
		// 총 페이지수 계산
		totalPage = totalCount / listCount;
		if( totalCount % listCount > 0 )	totalPage++;
		
		// 현재 페이지가 총 페이지보다 크게 입력되면
		// 강제로 마지막페이지 고정
		if (totalPage < curPage)	curPage = totalPage;
		
		
		// 화면에 보일 시작 페이지 & 끝 페이지 설정 
		startPage = ((curPage-1)/pageNum)*pageNum+1;
		endPage = startPage+pageNum-1;
		
		// 계산된 마지막 페이지가 totalPage보다 커질 경우
		// 강제로 최종 페이지까지만 보이도록 설정
		if(endPage > totalPage)	endPage = totalPage;
		
		
		// 게시글 시작번호
		startNo = (curPage-1)*listCount+1;
		// 게시글 끝번호
		endNo = curPage*listCount;
	}

	public Free_Board_param(int pageNum, int curPage, int totalCount, int listCount, int totalPage, int startPage,
			int endPage, int startNo, int endNo) {
		super();
		this.pageNum = pageNum;
		this.curPage = curPage;
		this.totalCount = totalCount;
		this.listCount = listCount;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.startNo = startNo;
		this.endNo = endNo;
	}

	@Override
	public String toString() {
		return "Free_Board_param [pageNum=" + pageNum + ", curPage=" + curPage + ", totalCount=" + totalCount
				+ ", listCount=" + listCount + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", startNo=" + startNo + ", endNo=" + endNo + "]";
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	
}