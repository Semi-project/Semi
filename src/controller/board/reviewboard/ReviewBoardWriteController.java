package controller.board.reviewboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.review.Review_BoardService;
import service.board.review.Review_BoardServiceImpl;
import service.comment.review.Review_CommentService;
import service.comment.review.Review_CommentServiceImpl;

@WebServlet("/reviewer/write")
public class ReviewBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Review_BoardService review_BoardService = new Review_BoardServiceImpl();
	private Review_CommentService review_CommentService = new Review_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("login") == null || req.getSession().getAttribute("role_id")=="1") {
			resp.sendRedirect("/main"); //2018- 10- 23 일 정리 
			return;
		}
		//System.out.println();
		req.getRequestDispatcher("/view/board/review/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");
		// 응답 객체 MIME타입(Content-Type) 설정
		resp.setContentType("text/html;charset=UTF-8");
		/////////////////////////////////////////////////
		review_BoardService.write(req, resp);
		// resp.getWriter().println("1");
		resp.sendRedirect("/review/list");
	}
}
