package service.comment.qna;

import java.util.List;
import java.util.Map;

import dao.board.qna.QnADao;
import dao.board.qna.QnADaoImpl;
import dao.comment.qna.QnA_CommentDao;
import dao.comment.qna.QnA_CommentDaoImpl;
import dto.board.QnA;
import dto.comment.QnA_Comments;

public class QnA_CommentServiceImpl implements QnA_CommentService {

	private QnADao qnaDao = new QnADaoImpl();
	private QnA_CommentDao qna_CommentDao = new QnA_CommentDaoImpl();
	private QnA qna = new QnA();
		
	@Override
	public void insertComment(QnA_Comments qna_Comment) throws Exception {
		

		
		qna_CommentDao.insert(qna_Comment);
	
	}

	@Override
	public List getCommentList(QnA qna) {
		
		
		return qna_CommentDao.selectQnA_Comments(qna);
	}

	@Override
	public String deleteComment(QnA_Comments qna_Comment) throws Exception {
		
		String result = "";
		result =qna_CommentDao.deleteQnA_Comment(qna_Comment);
		
		return result;
	}

	
	


}