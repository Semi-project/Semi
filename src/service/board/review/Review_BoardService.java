package service.board.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Review_Board;
import dto.file.Review_Filetb;
import util.Paging;

public interface Review_BoardService {
	// 요청파라미터 데이터를 객체화하여 반환
		public Review_Board getParam(HttpServletRequest req, HttpServletResponse resp);

		// 요청파라미터에서 curPage 반환
		public int getCurPage(HttpServletRequest req);

		// 전체 게시글 수 조회
		public int getTotalCount();

		// 게시글 전체 조회
		public List<Review_Board> getList();

		// 게시글 페이징 조회
		public List<Review_Board> getPagingList(Paging paging);

		// 게시글 상세 조회
		// 조회된 게시글의 조회수도 증가시킨다
		public Review_Board view(Review_Board boardView);

		// 게시글 쓰기
		// 게시글 내용과 첨부파일을 함께 업로드한다
		public void write(HttpServletRequest req ,HttpServletResponse resp);

		// 첨부파일 얻기
		public List<Review_Filetb> viewFile(Review_Board board);
		// 게시판 썸네일 
		public List<Review_Filetb> thumbnail(HttpServletRequest req);
		// 게시글 수정
		// 게시글 내용과 첨부파일을 함께 적용한다
		public void update(HttpServletRequest req);
		
		public int  updateFile(HttpServletRequest req);
		// 게시글 삭제
		public void delete(HttpServletRequest req,Review_Board board);

		public int deleteByfileno(int fileno, Review_Board notice_Board);
		
		// 글 작성자인지 판단
		public boolean checkWriter(HttpServletRequest req, Review_Board board);

		public String getNick(Review_Board notice_Board);
}
