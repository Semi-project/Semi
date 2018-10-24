package dao.file.qna;

import java.util.List;

import dto.file.QnA_Filetb;

public interface QnA_FileDao {

	// 삽입
	public void insertFiletb(QnA_Filetb qna_filetb);

	// 업데이트
//	public void updateFiletbByfileno(QnA_filetb qna_filetb);
	// 삭제
	public void deleteFiletbByfileno(QnA_Filetb qna_filetb);

	// 리스트
	public List<QnA_Filetb> selectFiletbAll();
}
