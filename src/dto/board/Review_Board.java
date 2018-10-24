package dto.board;

import java.util.Date;

public class Review_Board {
	private int cateno;
	private String userid;
	private int boardno;
	private String title;
	private Date insert_dat;
	private Date update_dat;
	private String content;
	private int hit;
	private int recomend;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRecomend() {
		return recomend;
	}
	public void setRecomend(int recomend) {
		this.recomend = recomend;
	}
	@Override
	public String toString() {
		return "Review_Board [cateno=" + cateno + ", userid=" + userid + ", boardno=" + boardno + ", title=" + title
				+ ", insert_dat=" + insert_dat + ", update_dat=" + update_dat + ", content=" + content + ", hit=" + hit
				+ ", recomend=" + recomend + "]";
	}
	
}
