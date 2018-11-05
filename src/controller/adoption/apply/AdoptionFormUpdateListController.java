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
 * Servlet implementation class AdoptionFormUpdate
 */
@WebServlet("/adoption/application/updatelist")
public class AdoptionFormUpdateListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdoptionService adoptionService = new AdoptionServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String names = req.getParameter("names");
		//System.out.println(names);

		if (!"".equals(names) && names != null) {
			 adoptionService.updateList(names);
		}

		resp.sendRedirect("/adoption/application/list");

	}

}
