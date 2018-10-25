package dto.file;

import java.util.Date;

public class QnA_Filetb {

	// 파일번호
	private int fileno;
	private int boardno;
	private String originName;
	private String storedName;
	private long filesize;
	private Date insert_dat;

	@Override
	public String toString() {
		return "QnA_Filetb [fileno=" + fileno + ", boardno=" + boardno + ", originName=" + originName + ", storedName="
				+ storedName + ", filesize=" + filesize + ", insert_dat=" + insert_dat + "]";
	}

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

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
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

}