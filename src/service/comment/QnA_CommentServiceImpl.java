package service.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.QnADao;
import dao.board.QnADaoImpl;
import dao.comment.QnA_CommentDao;
import dao.comment.QnA_CommentDaoImpl;
import dto.comment.QnA_Comments;

public class QnA_CommentServiceImpl implements QnA_CommentService {

	private QnA_CommentDao qna_CommentDao = new QnA_CommentDaoImpl();

	@Override
	public QnA_Comments view(QnA_Comments qna_Comments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QnA_Comments getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeQnA_Comments(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQnA_Comments(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWriter(HttpServletRequest req, QnA_Comments qna_Comments) {
		// TODO Auto-generated method stub
		return false;
	}

}
