package controller.adoption.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

/**
 * Servlet implementation class AdoptionRe_AcceptListController
 */
@WebServlet("/adoption/send/accpetList")
public class AdoptionRe_AcceptListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String codes = req.getParameter("codes");

		if(!"".equals(codes) && codes != null) {
			animalService.animalListAccept(codes);
		}

		resp.sendRedirect("/adoption/send/list");

	}

}
