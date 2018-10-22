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
		
		// 일단 허가된 동물 리스트 가져오기
		List<Animal> animalList = animalService.selectAnimal();		
		
		// 허가 되지 않은 동물 리스트 가져오기
//		List<Animal> animalList = animalService.selectAnimalnotAutho();
		
		System.out.println(animalList.size());
		
		req.setAttribute("animalList", animalList);
		req.getRequestDispatcher("/view/adoption/send/adoptionSendList.jsp").forward(req, resp);
		
	}
	
}
