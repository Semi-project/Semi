package service.comment.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.qna.QnADao;
import dao.board.qna.QnADaoImpl;
import dao.comment.qna.QnA_CommentDao;
import dao.comment.qna.QnA_CommentDaoImpl;
import dto.board.QnA;
import dto.comment.QnA_Comments;

public class QnA_CommentServiceImpl implements QnA_CommentService {

	private QnADao qnaDao = new QnADaoImpl();
	private QnA_CommentDao qna_CommentDao = new QnA_CommentDaoImpl();

	@Override
	public void insertComment(QnA_Comments qna_Comment) throws Exception {
		
		qna_CommentDao.insert(qna_Comment);
	
	}

	@Override
	public List getCommentList(int boardno) {
		
		
		return qna_CommentDao.selectQnA_Comments(boardno);
	}

	@Override
	public boolean deleteComment(QnA_Comments qna_Comment) throws Exception {
		
		qna_CommentDao.deleteQnA_Comment(qna_Comment);
		
		if(qna_CommentDao.selectQnA_CommentCntAll(qna_Comment)<0) {
			return false;
		}else {
			
			return true;
		}
		
		
	}

	

}
