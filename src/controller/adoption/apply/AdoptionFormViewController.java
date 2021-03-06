package controller.adoption.apply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;

/**
 * Servlet implementation class AdoptionFormView
 */
@WebServlet("/adoption/application/view")
public class AdoptionFormViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Adoption adoption = adoptionService.getParam(req, resp);
		Adoption adoptionView= adoptionService.view(adoption);
		req.setAttribute("adoptionView",adoptionView);
		
		req.getRequestDispatcher("/view/board/adoption/apply/view.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
