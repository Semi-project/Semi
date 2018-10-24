package service.comment.review;

import java.util.List;

import dao.comment.review.Review_CommentDao;
import dao.comment.review.Review_CommentDaoImpl;
import dto.board.Review_Board;
import dto.comment.Review_Comments;


public class Review_CommentServiceImpl implements Review_CommentService {
	Review_CommentDao review_CommentDao = new Review_CommentDaoImpl();

	@Override
	public void insert_Comments(Review_Comments comment) {
		review_CommentDao.insert(comment);

	}

	@Override
	public List<Review_Comments> getCommentList(Review_Board board) {
		return review_CommentDao.select(board);

	}

	@Override
	public boolean deleteComment(Review_Comments comment) {
		review_CommentDao.deleteComment(comment);

		if (review_CommentDao.countComment(comment) > 0) {
			return false;
		} else {
			return true;
		}
	}

}
