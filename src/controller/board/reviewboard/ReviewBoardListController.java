package controller.board.reviewboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Review_Board;
import dto.file.Review_Filetb;
import service.board.review.Review_BoardService;
import service.board.review.Review_BoardServiceImpl;
import util.Paging;

@WebServlet("/review/list")
public class ReviewBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_BoardService review_BoardService = new Review_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int curPage = review_BoardService.getCurPage(req);
		int totalCount = review_BoardService.getTotalCount();
		Paging paging = new Paging(totalCount, curPage);

		// System.out.println(paging);
		List<Review_Board> boardList = review_BoardService.getPagingList(paging);
		req.setAttribute("boardList", boardList);
		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);
		List<Review_Filetb> fileList=review_BoardService.thumbnail(req);
		req.setAttribute("fileList", fileList);
		
		req.getRequestDispatcher("/view/board/review/list.jsp").forward(req, resp);

	}
}
