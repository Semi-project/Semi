package dao.board.qna;

import java.sql.SQLException;
import java.util.List;

import util.Paging;
import dto.board.Free_Board;
import dto.board.Notice_Board;
import dto.board.QnA;

public interface QnADao {

		// QnA ��Ϻ���
		public List<QnA> selectQnA();

		//QnA �۾���
		public void insertQnA(QnA qna) throws SQLException ;

		// QnA ���б�
		public QnA selectQnAByBoardno(int boardNo);

		// QnA �� ����
		public void updateQnA(QnA qna) throws SQLException  ;

		// QnA �� ����
		public int deleteQnA(QnA qna) throws SQLException  ;

		// QnA ����Ʈ ����¡ ó��
		public List<QnA> selectQnAPagingList(Paging paging , String search , String searchVal);

		// QnA �� �ۼ� ���
		public int selectQnACntAll(String searchVal, String search);
		
		// QnA �����Խñ� ��ȯ
		public int selectBoardno();

		//QnA �������� ã��
		public QnA selectQnABytitle(QnA qna);

		//QnA �������� ã��
		public QnA selectQnABycontent(QnA qna);

		// QnA ���̵�� ã��
		public QnA selectByuserid(QnA qna);
		
		// ��۹�ȣ�� ��ȸ
		public QnA selectByComment_no(QnA qna);
		
		public QnA selectqnaBoardByBoardNo(QnA qna);
		
		
}
