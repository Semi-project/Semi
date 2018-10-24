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

@WebServlet("/review/view")
public class ReviewBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_BoardService review_BoardService = new Review_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review_Board review_Board = new Review_Board();
		review_Board = review_BoardService.getParam(req, resp);
		Review_Board boardView = review_BoardService.view(review_Board);
		req.setAttribute("boardView", boardView);
		// 글 작성자 닉네임 전달 현재 nick=userid
		req.setAttribute("writernick", review_BoardService.getNick(review_Board));
		// 첨부파일 전달
		List<Review_Filetb> boardFile = review_BoardService.viewFile(review_Board);
		req.setAttribute("boardFile", boardFile);

		// VIEW지정
		req.getRequestDispatcher("/view/board/review/view.jsp").forward(req, resp);

	}
}
