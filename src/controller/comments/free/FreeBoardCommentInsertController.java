package controller.comments.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.Free_Comments;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;
import service.comment.free.Free_CommentService;
import service.comment.free.Free_CommentServiceImpl;


@WebServlet("/freeboardcomment/insert")
public class FreeBoardCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	

	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	private Free_CommentService fcs= new Free_CommentServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		Free_Comments comment = new Free_Comments();
//		System.out.println("코멘트"+comment);
		String boardno = (String) req.getParameter("boardno");
		String content = (String) req.getParameter("content");
//System.out.println("코멘트보드넘버"+boardno);
//System.out.println("코멘트컨텐트"+content);
//		System.out.println(boardno + " " + content );
		comment.setBoardno( Integer.parseInt(boardno) );
		comment.setUserid((String)req.getSession().getAttribute("userid"));
		comment.setContent(content);
//		System.out.println("코멘트2"+comment);
		fcs.insertComment(comment);
		
		resp.sendRedirect("/freeboard/view?boardno="+comment.getBoardno());
	}
}
