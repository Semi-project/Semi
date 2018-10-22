package controller.adoption.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;


@WebServlet("/adoption/send/delete")
public class AdoptionRe_DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/adoption/send/list").forward(req, resp);
		
		
	}
}
