package controller.adoption.send;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;


@WebServlet("/adoption/send/delete")
public class AdoptionRe_DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// service
	private AnimalService animalService = new AnimalServiceImpl();
	
}
