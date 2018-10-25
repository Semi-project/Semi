package controller.comments.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.QnA_Comments;
import service.comment.qna.QnA_CommentService;
import service.comment.qna.QnA_CommentServiceImpl;

@WebServlet("/qnacomment/delete")
public class QnA_CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnA_CommentService qna_CommentService = new QnA_CommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req , resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		QnA_Comments qna_Comment = qna_CommentService.deleteComment(qna_Comment);
		
		String comment_No = (String) req.getParameter("comment_no");
		
		qna_Comment.setComment_No(Integer.parseInt(comment_No));
		
		try {
			boolean success = qna_CommentService.deleteComment(qna_Comment);
		
			resp.getWriter().append("{\"success\":"+success+"}");
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
