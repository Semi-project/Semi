package dao.file.qna;

import dto.board.QnA;
import dto.file.QnA_Filetb;

public interface QnA_FileDao {

	//첨부파일 입력
		public void insertFile(QnA_Filetb qnafile) throws Exception;
		
		//첨부파일 조회
		public QnA_Filetb selectFile(QnA qna) throws Exception;

		//파일번호로 첨부파일 정보 조회
		public QnA_Filetb selectByFileno(int fileno);
		
		//첨부파일 삭제
		public int delete(QnA qna) throws Exception;

		//삭제리스트의 첨부파일 삭제
		public void deletedeleteBoardListFile(String names);
}
