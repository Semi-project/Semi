package controller.charity;

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
 * Servlet implementation class CharityOneOnOneList
 */
@WebServlet("/charity/oneonone/list")
public class CharityOneOnOneList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	private AdoptionService adoptionService = new AdoptionServiceImpl();
  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	  req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

	    Animal animal = animalService.getParam(req, resp);
	      animal = animalService.selectAnimalByanimal_Code(animal);
	      
	      Adoption adoption = adoptionService.getByanimalCode(animal);
	      
	      req.setAttribute("adoption", adoption);
	      req.setAttribute("animal", animal);
		
		req.getRequestDispatcher("/view/charity/charityoneononeview.jsp").forward(req, resp);
		
  }
  

}
