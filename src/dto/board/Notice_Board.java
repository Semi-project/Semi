package dto.board;

import java.util.Date;

public class Notice_Board {

	private int boardno;
	private int cateno;
	private String title;
	private String content;
	private Date insert_dat;
	private Date update_dat;
	private int hit; 
	private String userid;
	private int recomend;
	
	@Override
	public String toString() {
		return "Notice_Board [boardno=" + boardno + ", cateno=" + cateno + ", title=" + title + ", content=" + content
				+ ", insert_dat=" + insert_dat + ", update_dat=" + update_dat + ", hit=" + hit + ", userid=" + userid
				+ ", recomend=" + recomend + "]";
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

	public Date getInsert_dat() {
		return insert_dat;
	}

	public void setInsert_dat(Date insert_dat) {
		this.insert_dat = insert_dat;
	}

	public Date getUpdate_dat() {
		return update_dat;
	}

	public void setUpdate_dat(Date update_dat) {
		this.update_dat = update_dat;
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

	public int getRecomend() {
		return recomend;
	}

	public void setRecomend(int recomend) {
		this.recomend = recomend;
	}
   
}