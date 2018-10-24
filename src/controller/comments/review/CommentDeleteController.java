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
 * Servlet implementation class CommentDeleteController
 */
@WebServlet("/review/comment/delete")
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_CommentService commentService = new Review_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Review_Comments comment = new Review_Comments();
		String commentno = (String) req.getParameter("commentno");

		comment.setCommentNo(Integer.parseInt(commentno));

		boolean success = commentService.deleteComment(comment);

		resp.getWriter().append("{\"success\":" + success + "}");

	}

}
