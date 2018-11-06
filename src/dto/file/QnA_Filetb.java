package dto.file;

import java.util.Date;

public class QnA_Filetb {

	// 파일번호
	private int fileno;
	private int boardno;
	private String file_originname;
	private String file_savename;
	private long filesize;
	private Date insert_dat;
	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getFile_originname() {
		return file_originname;
	}
	public void setFile_originname(String file_originname) {
		this.file_originname = file_originname;
	}
	public String getFile_savename() {
		return file_savename;
	}
	public void setFile_savename(String file_savename) {
		this.file_savename = file_savename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public Date getInsert_dat() {
		return insert_dat;
	}
	public void setInsert_dat(Date insert_dat) {
		this.insert_dat = insert_dat;
	}
	@Override
	public String toString() {
		return "QnA_Filetb [fileno=" + fileno + ", boardno=" + boardno + ", file_originname=" + file_originname
				+ ", file_savename=" + file_savename + ", filesize=" + filesize + ", insert_dat=" + insert_dat + "]";
	}
	
}
