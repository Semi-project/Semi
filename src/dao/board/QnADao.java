package dao.board;

import java.sql.SQLException;
import java.util.List;

import util.Paging;
import dto.board.Free_Board;
import dto.board.QnA;

public interface QnADao {

		// QnA ��Ϻ���
		public List<QnA> selectQnA();

		//QnA �۾���
		public void insertQnA(QnA qna) throws Exception;

		// QnA ���б�
		public QnA selectQnAByBoardno(int boardNo);

		// QnA �� ����
		public void updateQnA(QnA qna) throws Exception;

		// QnA �� ����
		public int deleteQnA(QnA qna) throws Exception;

		// QnA ����Ʈ ����¡ ó��
		public List<QnA> selectQnAPagingList(Paging paging);

		// QnA �� �ۼ� ���
		public int selectQnACntAll();
		
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
		
		
}
