package controller.board.noticeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice_Board;
import dto.comment.Free_Comments;
import dto.comment.Notice_Comments;
import dto.file.Notice_Filetb;
import service.board.notice.Notice_BoardService;
import service.board.notice.Notice_BoardServiceImpl;
import service.comment.notice.Notice_CommentService;
import service.comment.notice.Notice_CommentServiceImpl;

@WebServlet("/noticeboard/view")
public class NoticeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Notice_BoardService notice_BoardService = new Notice_BoardServiceImpl();
	private Notice_CommentService notice_commentService = new Notice_CommentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Notice_Board notice_Board = notice_BoardService.getParam(req, resp);

		Notice_Board notice_boardView = notice_BoardService.view(notice_Board);
		req.setAttribute("notice_boardView", notice_boardView);

		Notice_Filetb notice_filetb = notice_BoardService.viewFile(notice_Board);
		req.setAttribute("fileno", notice_filetb);

		//댓글
		Notice_Comments comment = new Notice_Comments();
		List<Notice_Comments> commentList = notice_commentService.getCommentList(notice_Board);
		req.setAttribute("commentList", commentList);
		
		// VIEW지정
		req.getRequestDispatcher("/view/board/notice/view.jsp").forward(req, resp);
	}
}
