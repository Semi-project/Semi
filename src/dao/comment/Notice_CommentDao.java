package dao.comment;

import java.util.List;

import dto.comment.Notice_Comments;
import util.Paging;

public interface Notice_CommentDao {
//댓글 삽입 
	public void insert(Notice_Comments comment);

//모든 댓글 선택 (리스트 출력)
	public List<Notice_Comments> selectAll();

//댓글 검색 -내용으로 검색
	public Notice_Comments selectNotice_CommentByContent(Notice_Comments comment);

	// 댓글 검색 - userid 로 검색
	public Notice_Comments selectNotice_CommentByUserId(Notice_Comments comment);

	// 댓글 수 출력
	public int selectNotice_CommentCntAll();

	// 댓글 삭제 (userid 와 comment_no로 대조해서 삭제)
	public void deleteNotice_CommentByUserId(Notice_Comments comment);

	// 댓글 수정(userid 와 comment_no로 대조해서 수정)
	public void updateNotice_CommentByUserId(Notice_Comments comment);

	public List<Notice_Comments> selectNotice_CommentPagingList(Paging paging);
}
