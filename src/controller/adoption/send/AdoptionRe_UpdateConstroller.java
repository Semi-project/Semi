package controller.adoption.send;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

// 입양보내기 수정

@WebServlet("/adoption/send/update")
public class AdoptionRe_UpdateConstroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// service
	private AnimalService animalService = new AnimalServiceImpl();

}
