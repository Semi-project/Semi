package dto.comment;

import java.util.Date;

public class QnA_Comments {
	private int rnum;
	

	// 댓글번호
	private int comment_No;

	// 작성자
	private String userid;

	// 댓글내용
	private String content;

	// 작성일
	private Date insertDat;

	// 게시글코드
	private int boardno;

	@Override
	public String toString() {
		return "QnA_Comments [rnum=" + rnum + ", comment_No=" + comment_No + ", userid=" + userid + ", content="
				+ content + ", insertDat=" + insertDat + ", boardno=" + boardno + "]";
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getComment_No() {
		return comment_No;
	}

	public void setComment_No(int comment_No) {
		this.comment_No = comment_No;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInsertDat() {
		return insertDat;
	}

	public void setInsertDat(Date insertDat) {
		this.insertDat = insertDat;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	
	
	}
