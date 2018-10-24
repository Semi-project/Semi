package dao.board.review;

import java.util.List;

import dto.board.Review_Board;
import util.Paging;



public interface Review_BoardDao {
	// 게시판 목록보기
	public List<Review_Board> selectReviewBoardAll();

	// 게시판 글쓰기
	public void insertReviewBoard(Review_Board noticeBoard);

	// 게시판 글 수정
	public void updateReviewBoard(Review_Board noticeBoard);

	// 게시판 글 삭제
	public void deleteReviewBoard(Review_Board noticeBoard);

	// boardno로 게시물 보기
	public Review_Board selectReviewBoardByBoardNo(Review_Board noticeBoard);

	// 게시판 제목으로 찾기
	public String selectReviewBoardByTitle(Review_Board noticeBoard);

	// 게시판 내용으로 찾기
	public String selectReviewByContent(Review_Board noticeBoard);

	// 게시판 아이디로 찾기
	public String selectReviewByuserId(Review_Board noticeBoard);

	// 게시판 총 글수 출력
	public int selectReviewCntAll(); // 2018-10-21 완료

	// 게시판 리스트 페이징 처리
	public List<Review_Board> selectReviewPagingList(Paging paging);
	// 2018-10-21 완료

	public int selectReview_Boardno();

	// 조회수 올려주기
	public void updateHit(Review_Board noticeBoard);

	// 댓글번호로 조회하기
	public Review_Board selectByComment_No(Review_Board noticeBoard);

	public String selectNickByBoardno(Review_Board notice_Board);

}
