package controller.adoption.apply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;

/**
 * Servlet implementation class AdoptionFormDeleteController
 */
@WebServlet("/adoption/application/deletelist")
public class AdoptionFormDeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String names = req.getParameter("names");

		if (!"".equals(names) && names != null) {
			adoptionService.deleteAdoptionList(names);
		}

		resp.sendRedirect("/adoption/application/list");

	}

}
