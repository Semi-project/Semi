package dto.file;

public class QnA_Filetb {

	// 파일번호
	private int fileno;

	// 게시글코드
	private int boardno;

	// 파일원본이름
	private String file_OriginName;

	// 파일저장이름
	private String file_SaveName;

	@Override
	public String toString() {
		return "QnA_Filetb [fileno=" + fileno + ", boardno=" + boardno + ", file_OriginName=" + file_OriginName
				+ ", file_SaveName=" + file_SaveName + "]";
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

	public String getFile_OriginName() {
		return file_OriginName;
	}

	public void setFile_OriginName(String file_OriginName) {
		this.file_OriginName = file_OriginName;
	}

	public String getFile_SaveName() {
		return file_SaveName;
	}

	public void setFile_SaveName(String file_SaveName) {
		this.file_SaveName = file_SaveName;
	}

}
