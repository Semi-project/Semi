package dto.board;

import java.util.Date;

public class QnA {

	private int cateno; // 게시판코드
	private String userid; // 작성자
	private int boardno ; //게시글번호
	private String title; // 글제목
	private Date insert_Dat; // 작성일
	private String content; // 글내용

	@Override
	public String toString() {
		return "QnA [cateno=" + cateno + ", userid=" + userid + ", boardno=" + boardno + ", title=" + title
				+ ", insert_Dat=" + insert_Dat + ", content=" + content + "]";
	}
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getInsert_Dat() {
		return insert_Dat;
	}
	public void setInsert_Dat(Date insert_Dat) {
		this.insert_Dat = insert_Dat;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}




}