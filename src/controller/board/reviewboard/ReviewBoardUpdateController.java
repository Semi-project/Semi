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

/**
 * Servlet implementation class NoticeBoardUpdateController
 */
@WebServlet("/review/update")
public class ReviewBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_BoardService review_BoardService = new Review_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review_Board board = review_BoardService.getParam(req, resp);
		if (!review_BoardService.checkWriter(req, board)) {
			resp.sendRedirect("/notice/list");
			return;
		}

		// 게시글 조회수행
		Review_Board boardView = review_BoardService.view(board);
		// MODEL 전달
		req.setAttribute("boardView", boardView);

		// 글 작성자 닉네임 전달
		req.setAttribute("writernick", review_BoardService.getNick(board));

		// 첨부파일 전달
		List<Review_Filetb> boardFile = review_BoardService.viewFile(board);
		req.setAttribute("boardFile", boardFile);

		// VIEW지정
		req.getRequestDispatcher("/view/board/review/update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		review_BoardService.update(req);

		resp.sendRedirect("/review/list");

	}

}
