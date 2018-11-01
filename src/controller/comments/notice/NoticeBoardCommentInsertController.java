package controller.comments.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.comment.Notice_Comments;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import service.comment.notice.Notice_CommentService;
import service.comment.notice.Notice_CommentServiceImpl;

@WebServlet("/noticeboardcomment/insert")
public class NoticeBoardCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardService notice_boardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_commentService = new Notice_CommentServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		Notice_Comments notice_comments = new Notice_Comments();
		System.out.println("코멘트"+notice_comments);
		String boardno = (String) req.getParameter("boardno");
		String content = (String) req.getParameter("content");
//System.out.println("코멘트보드넘버"+boardno);
//System.out.println("코멘트컨텐트"+content);
//		System.out.println(boardno + " " + content );
		notice_comments.setBoardno( Integer.parseInt(boardno) );
		notice_comments.setUserid((String)req.getSession().getAttribute("userid"));
		notice_comments.setContent(content);
//		System.out.println("코멘트2"+notice_comments);
		notice_commentService.insertComment(notice_comments);
		
		resp.sendRedirect("/noticeboard/view?boardno="+notice_comments.getBoardno());
	}
	
}
