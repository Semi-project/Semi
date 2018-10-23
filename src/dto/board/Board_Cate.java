package dto.board;

import java.util.Date;

public class Board_Cate {

	private int cateno; // 게시판코드
	private String cat_Name; // 게시판이름
	private Date cate_Dat; // 게시판등록일


	@Override
	public String toString() {
		return "Board_Cate [cateno=" + cateno + ", cat_Name=" + cat_Name
				+ ", cate_Dat=" + cate_Dat + "]";
	}


	public int getCateno() {
		return cateno;
	}


	public void setCateno(int cateno) {
		this.cateno = cateno;
	}


	public String getCat_Name() {
		return cat_Name;
	}


	public void setCat_Name(String cat_Name) {
		this.cat_Name = cat_Name;
	}


	public Date getCate_Dat() {
		return cate_Dat;
	}


	public void setCate_Dat(Date cate_Dat) {
		this.cate_Dat = cate_Dat;
	}

}
