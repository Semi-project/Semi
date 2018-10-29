package controller.adoption.apply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

/**
 * Servlet implementation class AdoptionForm
 */
@WebServlet("/adoption/application/insert")
public class AdoptionFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/main");
			return;
		}
		req.getRequestDispatcher("/view/board/adoption/apply/application.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("들어옴");
		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");

		adoptionService.write(req, resp);

		resp.sendRedirect("/main");

	}
}
