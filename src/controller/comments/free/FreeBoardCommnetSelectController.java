package controller.comments.free;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Free_Board;
import dto.comment.Free_Comments;
import service.board.free.Free_BoardService;
import service.board.free.Free_BoardServiceImpl;
import service.comment.free.Free_CommentService;
import service.comment.free.Free_CommentServiceImpl;


@WebServlet("/freecomment/select")
public class FreeBoardCommnetSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Free_CommentService fcs= new Free_CommentServiceImpl();
	private Free_BoardService freeboardService = new Free_BoardServiceImpl();
	
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	Free_Board fb=freeboardService.getParam(req, resp);
	Free_Comments comment = new Free_Comments();
	
	List<Free_Comments> commentList = fcs.getCommentList(fb);
	
	req.setAttribute("commentList", commentList);
	
	
}
}
