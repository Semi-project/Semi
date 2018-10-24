package dao.comment.qna;

import java.util.List;

import dto.comment.QnA_Comments;
import util.Paging;

public interface QnA_CommentDao {
//댓글 삽입 
	public void insert(QnA_Comments comment);

//모든 댓글 선택 (리스트 출력)
	public List<QnA_Comments> selectAll();

//댓글 검색 -내용으로 검색
	public QnA_Comments selectQnA_CommentByContent(QnA_Comments comment);

	// 댓글 검색 - userid 로 검색
	public QnA_Comments selectQnA_CommentByUserId(QnA_Comments comment);

	// 댓글 수 출력
	public int selectQnA_CommentCntAll();

	// 댓글 삭제 (userid 와 comment_no로 대조해서 삭제)
	public void deleteQnA_CommentByUserId(QnA_Comments comment);

	// 댓글 수정(userid 와 comment_no로 대조해서 수정)
	public void updateQnA_CommentByUserId(QnA_Comments comment);

	public List<QnA_Comments> selectQnA_CommentPagingList(Paging paging);
}
