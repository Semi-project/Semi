package controller.board.qnaboard;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import dto.file.QnA_Filetb;
import service.board.QnAService;
import service.board.QnAServiceImpl;

/**
 * Servlet implementation class FreeBoardList
 */
@WebServlet("/qnaboard/list")
public class QnABoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
}
