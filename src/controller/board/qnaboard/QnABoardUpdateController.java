package controller.board.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import service.board.QnAService;
import service.board.QnAServiceImpl;


@WebServlet("/qnaboard/update")
public class QnABoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		QnA qna = qnaService.getParam(req, resp);
		
//		if( !qnaService.che ) {
//			resp.sendRedirect("/qnaboard/list");
//			return;
		
		// 게시글 조회수행
		QnA qnaView = qnaService.viewQnA(qna);
	
		// MODEL 전달
		req.setAttribute("qnaView", qnaView);
		
		//글 작성자 닉네임 전달
		
		// 첨부파일 전달
//		QnA_Filetb qna_Filetb = qnaService.
		
		//view 지정
		req.getRequestDispatcher("/view/board/qna/update.jsp").forward(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		
		qnaService.updateQnA(req);
		
		resp.sendRedirect("/qnaboard/list");
		
	}
	
	
	
}
