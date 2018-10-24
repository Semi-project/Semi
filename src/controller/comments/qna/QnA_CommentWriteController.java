package controller.comments.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.QnA_Comments;
import service.comment.QnA_CommentService;
import service.comment.QnA_CommentServiceImpl;


@WebServlet("/qnacomment/write")
public class QnA_CommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnA_CommentService qna_CommentService = new QnA_CommentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req , resp);	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		QnA_Comments qna_Comment = new QnA_Comments();
		
		String boardno = (String) req.getParameter("boardno");
		String content = (String ) req.getParameter("content");
		
		qna_Comment.setBoardno(Integer.parseInt(boardno));
		qna_Comment.setUserid((String)req.getSession().getAttribute("userid"));
		qna_Comment.setContent(content);
		
		try {
			qna_CommentService.insertComment(qna_Comment);
		
			resp.sendRedirect("/qnaboard/view?boardno="+qna_Comment.getBoardno());
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}
	
}
