package dto.board;

import java.util.Date;

public class Free_Board_param {
	private int boardno; //게시글 코드
	private int cateno; // 게시판코드
	private String title; // 글제목
	private String content; // 글내용
	private Date insert_Dat; //작성일
	private Date update_Dat; // 수정일
	private int hit ; //조회수
	private String userid; //작성자
	private int recommend; //추천수
	
	//lock number(2) not null
	//private int lock; -> freeboard dto에다가
	//jsp 에서 리스트 출력할때 
	
	//검색
	
	private String namesearch;
	private String contentsearch;
	private Date datesearch;
	
	//정렬
	
	
	//페이징
	 
	private int recordCountPerPage = 10;
	private int pageNumber = 0;
	private int start = 1;
	private int end = 10;
	
	public Free_Board_param() {}

	public Free_Board_param(int boardno, int cateno, String title, String content, Date insert_Dat, Date update_Dat,
			int hit, String userid, int recommend, String namesearch, String contentsearch, Date datesearch,
			int recordCountPerPage, int pageNumber, int start, int end) {
		super();
		this.boardno = boardno;
		this.cateno = cateno;
		this.title = title;
		this.content = content;
		this.insert_Dat = insert_Dat;
		this.update_Dat = update_Dat;
		this.hit = hit;
		this.userid = userid;
		this.recommend = recommend;
		this.namesearch = namesearch;
		this.contentsearch = contentsearch;
		this.datesearch = datesearch;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public int getCateno() {
		return cateno;
	}

	public void setCateno(int cateno) {
		this.cateno = cateno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInsert_Dat() {
		return insert_Dat;
	}

	public void setInsert_Dat(Date insert_Dat) {
		this.insert_Dat = insert_Dat;
	}

	public Date getUpdate_Dat() {
		return update_Dat;
	}

	public void setUpdate_Dat(Date update_Dat) {
		this.update_Dat = update_Dat;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getNamesearch() {
		return namesearch;
	}

	public void setNamesearch(String namesearch) {
		this.namesearch = namesearch;
	}

	public String getContentsearch() {
		return contentsearch;
	}

	public void setContentsearch(String contentsearch) {
		this.contentsearch = contentsearch;
	}

	public Date getDatesearch() {
		return datesearch;
	}

	public void setDatesearch(Date datesearch) {
		this.datesearch = datesearch;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Free_Board_param [boardno=" + boardno + ", cateno=" + cateno + ", title=" + title + ", content="
				+ content + ", insert_Dat=" + insert_Dat + ", update_Dat=" + update_Dat + ", hit=" + hit + ", userid="
				+ userid + ", recommend=" + recommend + ", namesearch=" + namesearch + ", contentsearch="
				+ contentsearch + ", datesearch=" + datesearch + ", recordCountPerPage=" + recordCountPerPage
				+ ", pageNumber=" + pageNumber + ", start=" + start + ", end=" + end + "]";
	}
	
	

	
}
