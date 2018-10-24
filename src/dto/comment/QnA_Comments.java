package dto.comment;

import java.util.Date;

public class QnA_Comments {
	private int rnum;
	

	// 댓글번호
	private int commentNo;

	// 작성자
	private String userid;

	// 댓글내용
	private String content;

	// 작성일
	private Date insertDat;

	// 게시글코드
	private int boardno;

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "QnA_comments [commentNo=" + commentNo + ", userid=" + userid + ", content=" + content + ", insertDat="
				+ insertDat + ", boardno=" + boardno + "]";
	}

}
