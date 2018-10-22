package service.board;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import dto.file.QnA_Filetb;
import util.Paging;

public interface QnAService {
	
	// �����Խ��� ��ü ��ȸ
	public List<QnA> selectQnA();
	
	// QnA �� ����ȸ
	public QnA viewQnA(int boardNo);
	
	// �����Խ��� �� ���� �� ���� ���ε�
	public void writeQnA(HttpServletRequest req) throws Exception;
	
	// �����Խ��� ����
	public int deleteQnA(QnA qna) throws Exception;
	
	// �����Խ��� ����
	public void updateQnA(QnA qna) throws Exception;// , File file);
	
	//�Խ��� �������� ã��
	public QnA  searchQnABytitle(QnA qna);

	//�Խ��� �������� ã��
	public QnA  searchQnABycontent(QnA qna);

	// �Խ��� ���̵�� ã��
	public QnA searchQnAByuserid(QnA qna);

	// �Խ��� �� �ۼ� ���
	public int selecntQnACntAll();

	// �Խ��� ����Ʈ ����¡ ó��
	public List getQnAPagingList(Paging paging);

	// Param ��������
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp);
	
	public int getCurPage(HttpServletRequest req);
}
