package controller.board.qnaboard;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import service.board.QnAService;
import service.board.QnAServiceImpl;

@WebServlet("/qnaboard/view")
public class QnABoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
}
