package controller.adoption.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

// 입양 보내기

@WebServlet("/adoption/send/insert")
public class AdoptionRe_InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// service
	private AnimalService animalService = new AnimalServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		
		Animal animal = animalService.getParam(req, resp);
		
		PrintWriter out = resp.getWriter();
		
		// naver smart Editor 2.0에서 GET으로 보낸 값 가져오는거 확인
//		System.out.println(animal.getAnimal_Feature());
		
		req.getRequestDispatcher("/view/adoption/send/adoptionSend.jsp").forward(req, resp);
		
		
	}
	
}
