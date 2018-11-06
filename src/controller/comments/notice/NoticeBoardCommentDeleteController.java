package controller.comments.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.Notice_Comments;
import service.comment.notice.Notice_CommentService;
import service.comment.notice.Notice_CommentServiceImpl;

@WebServlet("/notice/comment/delete")
public class NoticeBoardCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Notice_CommentService commentService = new Notice_CommentServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Notice_Comments comment = new Notice_Comments();
		String commentno = (String) req.getParameter("comment_no");

		comment.setComment_no(Integer.parseInt(commentno));

		boolean success = commentService.deleteComment(comment);

		resp.getWriter().append("{\"success\":" + success + "}");

	}
}
