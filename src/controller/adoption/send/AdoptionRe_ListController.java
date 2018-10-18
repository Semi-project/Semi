package controller.adoption.send;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

// 입양 보내기 리스트

@WebServlet("/Adoption_RegisterListController")
public class AdoptionRe_ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// service
	private AnimalService animalService = new AnimalServiceImpl();

}
