package dao.file.free;

import dto.board.Free_Board;
import dto.file.Free_Filetb;

public interface Free_FileDao {


	//첨부파일 입력
	public void insertFile(Free_Filetb freefiletb);
	
	//첨부파일 조회
	public Free_Filetb selectFile(Free_Board freeboard);


	//파일번호로 첨부파일 정보 조회
	public Free_Filetb selectByfileno(int freefileno);
	
}
