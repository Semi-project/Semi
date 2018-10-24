package dao.board.notice;

import java.util.List;

import dto.board.Notice_Board;
import util.Paging;

public interface Notice_BoardDao {
	
		// 게시판 목록보기
		public List<Notice_Board> selectNoticeBoard();

		//게시판 글쓰기
		public void insertNoticeBoard(Notice_Board noticeBoard);

		// 게시판 글읽기
		public Notice_Board viewNoticeBoard(Notice_Board noticeBoard);

		// 게시판 글 수정
		public void updateNoticeBoard(Notice_Board noticeBoard);

		// 게시판 글 삭제
		public void deleteNoticeBoard(Notice_Board noticeBoard);

		//게시판 제목으로 찾기
		public String selectNoticeBoardByTitle(Notice_Board noticeBoard);

		//게시판 내용으로 찾기
		public String selectNoticeBoardByContent(Notice_Board noticeBoard);

		// 게시판 아이디로 찾기
		public String selectNoticeBoardByuserId(Notice_Board noticeBoard);

		// 게시판 총 글수 출력
		public int selectNoticeBoardCntAll();

		// 게시판 리스트 페이징 처리
		public List<Notice_Board> selectNoticeBoardPagingList(Paging paging);

		// 조회수 올려주기
		public void updateHit(Notice_Board noticeBoard);

		
		//댓글번호로 조회하기
		public Notice_Board selectByComment_No (Notice_Board noticeBoard);


}
