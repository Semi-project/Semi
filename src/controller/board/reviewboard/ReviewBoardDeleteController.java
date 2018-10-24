package controller.board.reviewboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import dto.board.Review_Board;
import service.board.review.Review_BoardService;
import service.board.review.Review_BoardServiceImpl;

@WebServlet("/review/delete")
public class ReviewBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_BoardService review_BoardService = new Review_BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Review_Board review_Board = new Review_Board();
		
		review_Board=review_BoardService.getParam(req, resp);
		// 로그인한 사람의 글이 아니면 중단하고 리스트로
		if (!review_BoardService.checkWriter(req, review_Board)) {

		//	System.out.println("2");	
			resp.sendRedirect("/review/list");
			return;
		}
		//System.out.println("1");
		review_BoardService.delete(req,review_Board);/// 구현하기
		resp.sendRedirect("/review/list");
	}
}
