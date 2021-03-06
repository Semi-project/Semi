package controller.adoption.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import dto.animal.Animal;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

/**
 * Servlet implementation class AdoptionRe_ViewController
 */
@WebServlet("/adoption/send/view")
public class AdoptionRe_ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	private AdoptionService adoptionService = new AdoptionServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Animal animal = animalService.getParam(req, resp);
		animal = animalService.selectAnimalByanimal_Code(animal);
		req.setAttribute("animal", animal);
		
		req.getRequestDispatcher("/view/board/adoption/send/adoptionSendView.jsp").forward(req, resp);
		
	}

}
