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

@WebServlet("/noticeboard/list")
public class NoticeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int curPage = notice_BoardService.getCurPage(req);
		int totalCount = notice_BoardService.getTotalCount();
		Paging paging = new Paging(totalCount, curPage);


		//검색어 얻기
		String search = notice_BoardService.getSearch(req);
		
		//페이징 객체에 검색어 적용
		paging.setSearch(search);
		System.out.println(search);
		
		// System.out.println(paging);
		List<Notice_Board> notice_boardList = notice_BoardService.getPagingList(paging);
		req.setAttribute("notice_boardList", notice_boardList);
		
		//게시글목록 MODEL로 추가
//		List<Notice_Board> boardList = notice_BoardService.getPagingList(paging);
//		req.setAttribute("boardList", boardList);
		
		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		req.getRequestDispatcher("/view/board/notice/list.jsp")
			.forward(req, resp);

	}
}
