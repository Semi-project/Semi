package dao.comment.review;

import java.util.List;

import dto.board.Review_Board;
import dto.comment.Review_Comments;

public interface Review_CommentDao {
	// 댓글조회
		public List<Review_Comments> select(Review_Board board);

		// 댓글 삽입
		public void insert(Review_Comments comment);

		// 댓글 삭제하기
		public void deleteComment(Review_Comments comment);

		// 댓글 카운트 - 댓글 존재 여부 확인
		public int countComment(Review_Comments comment);
}
