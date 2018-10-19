package controller.adoption.send;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

// 입양 보내기 리스트

@WebServlet("/adoption/send/list")
public class AdoptionRe_ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// service
	private AnimalService animalService = new AnimalServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Animal> list = animalService.selectAnimal();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/view/adoption/send/adoptionSendList.jsp").forward(req, resp);
		
		
	}
	
}
