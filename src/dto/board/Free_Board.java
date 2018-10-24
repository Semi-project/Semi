package dto.board;

import java.util.Date;

public class Free_Board {

	private int boardno; //게시글 코드
	private int cateno; // 게시판코드
	private String title; // 글제목
	private String content; // 글내용
	private Date insert_Dat; //작성일
	private Date update_Dat; // 수정일
	private int hit ; //조회수
	private String userid; //작성자
	private int recommend; //추천수
	
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
	@Override
	public String toString() {
		return "Free_Board [boardno=" + boardno + ", cateno=" + cateno + ", title=" + title + ", content=" + content
				+ ", insert_Dat=" + insert_Dat + ", update_Dat=" + update_Dat + ", hit=" + hit + ", userid=" + userid
				+ ", recommend=" + recommend + "]";
	}

	
}