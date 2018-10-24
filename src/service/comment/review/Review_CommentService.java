package service.comment.review;

import java.util.List;

import dto.board.Review_Board;
import dto.comment.Review_Comments;


public interface Review_CommentService {
	public void insert_Comments(Review_Comments comment);

	public List<Review_Comments> getCommentList(Review_Board board);

	public boolean deleteComment(Review_Comments comment);
}
