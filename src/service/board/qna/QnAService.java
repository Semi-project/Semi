package service.board.qna;

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
	public void writeQnA(HttpServletRequest req) throws SQLException, Exception;
	
	// �����Խ��� ����
	public int deleteQnA(QnA qna) throws SQLException, Exception ;
	
	// �����Խ��� ����
	public void updateQnA(HttpServletRequest req , QnA qna) throws SQLException, Exception;// , File file);

	// �Խ��� ����Ʈ ����¡ ó��
	public List getQnAPagingList(Paging paging, String search, String searchVal);

	// Param ��������
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp);
	
	public int getCurPage(HttpServletRequest req);
	
	// 전체 게시글 수 조회
	public int getTotalCount(String searchVal, String search);
	
	//첨부파일 얻기
	public QnA_Filetb viewFile(QnA qna) throws Exception;
}
