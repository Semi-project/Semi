package controller.board.noticeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import util.Paging;

@WebServlet("/noticeboard/paginglist")
public class NoticeBoardPagingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService boardService = new Notice_BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		//현재 페이지 번호 얻기
		int curPage = boardService.getCurPage(req);
		
		//페이징 객체
		int totalCount = boardService.getTotalCount();
		Paging paging = new Paging(totalCount, curPage);
	
		
		//게시글목록 MODEL로 추가
		List<Notice_Board> boardList = boardService.getPagingList(paging);
		req.setAttribute("boardList", boardList);
		
	//	System.out.println(paging);
		
		
		//페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);
		
		//VIEW지정
		req.getRequestDispatcher("/view/board/notice/list.jsp")
			.forward(req, resp);
		
	}
	
}











