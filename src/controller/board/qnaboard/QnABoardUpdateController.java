package controller.board.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import dto.file.QnA_Filetb;
import service.board.qna.QnAService;
import service.board.qna.QnAServiceImpl;


@WebServlet("/qnaboard/update")
public class QnABoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		/*QnA qna = qnaService.getParam(req, resp);*/
	QnA qna2=qnaService.getParam2(req, resp);
		
		if (!qnaService.checkWriter(req, qna2)) {
			resp.sendRedirect("/qnaboard/paginglist");
			return;
		}
		int boardNo = Integer.parseInt(req.getParameter("boardno"));
		
//		if( !qnaService.che ) {
//			resp.sendRedirect("/qnaboard/list");
//			return;
		
		// 게시글 조회수행
		QnA qnaView = qnaService.viewQnA(boardNo);
	
		// MODEL 전달
		req.setAttribute("qnaView", qnaView);
		
		//글 작성자 닉네임 전달
		
		
		try {
			QnA_Filetb qna_Filetb = qnaService.viewFile(qna2);
			req.setAttribute("qnafile", qna_Filetb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//view 지정
		req.getRequestDispatcher("/view/board/qna/update.jsp").forward(req, resp);
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		
		QnA qna = qnaService.getParam(req, resp);
		
		try {
			qnaService.updateQnA(req , qna);
			
			int boardNo = Integer.parseInt(req.getParameter("boardno"));	
			
			QnA qnaView = qnaService.viewQnA(boardNo);
			
			req.setAttribute("qnaView", qnaView);
			
			if(qnaView != null) {
				String result = "success";
				req.setAttribute("result", result);
			}
			String successMsg = "";
			successMsg = "수정에 성공했습니다.";
			req.setAttribute("successMsg", successMsg);
			
		} catch (Exception e) {
			e.printStackTrace();
			String result = "fail";
			
			String failMsg = "";
			failMsg = "수정에 실패했습니다";
			req.setAttribute("result", result);
			req.setAttribute("failMsg", failMsg);
			
		}
			
			req.getRequestDispatcher("/view/board/qna/view.jsp").forward(req, resp);
	}
	
	
	
}
