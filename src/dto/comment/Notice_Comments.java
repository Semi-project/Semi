package dto.comment;

import java.util.Date;

public class Notice_Comments {
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

	private int rnum;

	@Override
	public String toString() {
		return "Notice_Comments [commentNo=" + commentNo + ", userid=" + userid + ", content=" + content
				+ ", insertDat=" + insertDat + ", boardno=" + boardno + ", rnum=" + rnum + "]";
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

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

}
