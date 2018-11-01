package dao.comment.free;

import java.util.List;

import dto.board.Free_Board;
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

	// 댓글 카운트 - 댓글 존재 여부 확인 
		public int countComment(Free_Comments comment);

	// 댓글 삭제하기
		public void deleteComment(Free_Comments comment);

	// 댓글 수정(userid 와 comment_no로 대조해서 수정)
	public void updateFree_CommentByUserId(Free_Comments comment);

	public List<Free_Comments> selectFree_CommentPagingList(Paging paging);
	
	// 코멘트 조회 - 코멘트 번호를 rnum을 통해 같이 조회한다
		public List selectComment(Free_Board freeboard);
	
	
}
