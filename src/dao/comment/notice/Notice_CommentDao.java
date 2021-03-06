package dao.comment.notice;

import java.util.List;

import dto.board.Notice_Board;
import dto.comment.Notice_Comments;

public interface Notice_CommentDao {

	// 댓글조회
	public List<Notice_Comments> select(Notice_Board board);

	// 댓글 삽입
	public void insert(Notice_Comments comment);

	// 댓글 삭제하기
	public void deleteComment(Notice_Comments comment);

	// 댓글 카운트 - 댓글 존재 여부 확인
	public int countComment(Notice_Comments comment);
	
	//댓글 검색 - userid
	public Notice_Comments selectNotice_CommentByUserId(Notice_Comments comment);

	// 코멘트 조회 - 코멘트 번호를 rnum을 통해 같이 조회한다
	public List selectComment(Notice_Board noticeboard);
	
}
