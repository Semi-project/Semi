package controller.comments.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.Review_Comments;
import service.comment.review.Review_CommentService;
import service.comment.review.Review_CommentServiceImpl;

/**
 * Servlet implementation class ReviewCommentInsertController
 */
@WebServlet("/review/comment/insert")
public class ReviewCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_CommentService commentService = new Review_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Review_Comments comment = new Review_Comments();

		String boardno = (String) req.getParameter("boardno");
		String content = (String) req.getParameter("content");

		System.out.println(boardno + " " + content);
		comment.setBoardno(Integer.parseInt(boardno));
		comment.setUserid((String) req.getSession().getAttribute("userid"));
		comment.setContent(content);
		commentService.insert_Comments(comment);
		resp.sendRedirect("/review/view?boardno=" + comment.getBoardno());
	}

}
