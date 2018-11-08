package dao.board.notice;

import java.util.List;

import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.Paging;

public interface Notice_BoardDao {
	
		// 게시판 목록보기
		public List<Notice_Board> selectNoticeBoardAll();

		//게시판 글쓰기
		public void insertNoticeBoard(Notice_Board noticeBoard);

		// 게시판 글 수정
		public void updateNoticeBoard(Notice_Board noticeBoard);

		// 게시판 글 삭제
		public void deleteNoticeBoard(Notice_Board noticeBoard);
	
		//boardno로 게시물 보기 
		public Notice_Board selectNoticeBoardByBoardNo(Notice_Board noticeBoard);
		
		//게시판 제목으로 찾기
		public int selectNoticeBoardByTitle(String search);

		//게시판 내용으로 찾기
		public String selectNoticeBoardByContent(Notice_Board noticeBoard);

		// 게시판 아이디로 찾기
		public String selectNoticeBoardByuserId(Notice_Board noticeBoard);

		// 게시판 총 글수 출력
		public int selectNoticeBoardCntAll(); //2018-10-21 완료 

		// 게시판 리스트 페이징 처리
		public List<Notice_Board> selectNoticeBoardPagingList(Paging paging);
		
		//게시글 페이징 조회
		public List getPagingList(Paging paging);
		
		// 글쓰기
		public int selectNotice_Boardno();
		
		// 조회수 올려주기
		public void updateHit(Notice_Board noticeBoard);

		
		//댓글번호로 조회하기
		public Notice_Board selectByComment_No (Notice_Board noticeBoard);

		public String selectNickByBoardno(Notice_Board notice_Board);

		public void insertFile(Notice_Filetb notice_Filetb);


}
