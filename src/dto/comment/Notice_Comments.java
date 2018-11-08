package dto.comment;

import java.util.Date;

public class Notice_Comments {
	
	private int rnum;
	private int comment_no;
	private int boardno;
	private String userid;
	private String userpw;
	private String content;
	private Date insert_dat;
	
	@Override
	public String toString() {
		return "Notice_Comments [rnum=" + rnum + ", comment_no=" + comment_no + ", boardno=" + boardno + ", userid="
				+ userid + ", userpw=" + userpw + ", content=" + content + ", insert_dat=" + insert_dat + "]";
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getInsert_dat() {
		return insert_dat;
	}

	public void setInsert_dat(Date insert_dat) {
		this.insert_dat = insert_dat;
	}
	

}
