package service.board.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.board.qna.QnADao;
import dao.board.qna.QnADaoImpl;
import dao.board.review.BoardCateDao;
import dao.board.review.BoardCateDaoImpl;
import dao.comment.qna.QnA_CommentDao;
import dao.comment.qna.QnA_CommentDaoImpl;
import dao.file.qna.QnA_FileDao;
import dao.file.qna.QnA_FileDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.board.QnA;
import dto.file.QnA_Filetb;
import util.Paging;

public class QnAServiceImpl implements QnAService {

	private MemberDao memberDao = new MemberDaoImpl();
	private BoardCateDao boardCateDao = new BoardCateDaoImpl();
	private QnA_CommentDao qna_CommentDao = new QnA_CommentDaoImpl();
	private QnA_FileDao qna_fileDao = new QnA_FileDaoImpl();
	private QnADao qnaDao = new QnADaoImpl();

	@Override
	public List<QnA> selectQnA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeQnA(QnA qna, QnA_Filetb QnA_File) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteQnA(QnA qna) {
		// TODO Auto-generated method stub

	}

	@Override

	public void updateQnA(QnA qna) {
		// TODO Auto-generated method stub
	}

	public void updateQnA(QnA qna, QnA_Filetb QnA_File) {
		// TODO Auto-generated method stub

	}

	@Override
	public String searchQnABytitle(QnA qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchQnABycontent(QnA qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchQnAByuserid(QnA qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selecntQnACntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<QnA> selectQnAPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecommend(QnA qna) {
		// TODO Auto-generated method stub

	}

	@Override
	public QnA searchByComment_no(QnA qna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
