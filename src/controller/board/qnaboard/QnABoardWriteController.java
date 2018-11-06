package controller.board.qnaboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.QnA;
import service.board.qna.QnAService;
import service.board.qna.QnAServiceImpl;

@WebServlet("/qnaboard/write")
public class QnABoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = (String) req.getSession().getAttribute("userid");
		
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/member/login");
			return;
		}
		req.setAttribute("userid", userid);
		req.getRequestDispatcher("/view/board/qna/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
		
		QnA qna = qnaService.getParam(req, resp);
		
		/*System.out.println(qna);*/
		
		//System.out.println(qna.getBoardno());
		try {
			qnaService.writeQnA(req);
			
			System.out.println("첨부파일"+req);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		resp.sendRedirect("/qnaboard/list");
	}
}
