package controller.comments.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.comment.free.Free_CommentDao;
import dao.comment.free.Free_CommentDaoImpl;
import dto.comment.Free_Comments;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;
import service.comment.free.Free_CommentService;
import service.comment.free.Free_CommentServiceImpl;


@WebServlet("/freeboardcomment/delete")
public class FreeBoardCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private Free_BoardService freeboardservice = new Free_BoardServiceImpl();
	private Free_CommentService fcs= new Free_CommentServiceImpl();
	private Free_CommentDao fc= new Free_CommentDaoImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Free_Comments comment = new Free_Comments();
		/*
		 * 
		 */
		
		String commentno = (String) req.getParameter("comment_no");
	
		comment.setComment_no( Integer.parseInt(commentno) );
	
		boolean success = fcs.deleteComment(comment);
		
		resp.getWriter().append("{\"success\":"+success+"}");
		
		//리스트 가져와
		
		
		
		
	}
}



