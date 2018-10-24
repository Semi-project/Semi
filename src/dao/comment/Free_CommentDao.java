package dao.comment;

import java.util.List;

import dto.comment.Free_Comments;
import util.Paging;

public interface Free_CommentDao {
//댓글 삽입 
	public void insert(Free_Comments comment);

//모든 댓글 선택 (리스트 출력)
	public List<Free_Comments> selectAll();

//댓글 검색 -내용으로 검색
	public Free_Comments selectFree_CommentByContent(Free_Comments comment);

	// 댓글 검색 - userid 로 검색
	public Free_Comments selectFree_CommentByUserId(Free_Comments comment);

	// 댓글 수 출력
	public int selectFree_CommentCntAll();

	// 댓글 삭제 (userid 와 comment_no로 대조해서 삭제)
	public void deleteFree_CommentByUserId(Free_Comments comment);

	// 댓글 수정(userid 와 comment_no로 대조해서 수정)
	public void updateFree_CommentByUserId(Free_Comments comment);

	public List<Free_Comments> selectFree_CommentPagingList(Paging paging);
}
