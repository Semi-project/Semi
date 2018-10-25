package controller.adoption.send;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Species;
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

		List<Species> speciesList = animalService.getSpecies();

		req.setAttribute("speciesList", speciesList);

		req.getRequestDispatcher("/view/board/adoption/send/adoptionSend.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");
		// 응답 객체 MIME타입(Content-Type) 설정
		resp.setContentType("text/html;charset=UTF-8");
		
		// null 떠야함
		System.out.println(req.getAttribute("gender Attribute : "));
		System.out.println(req.getParameter("gender Parameter : "));
		
		animalService.write(req, resp);
		
		resp.sendRedirect("/adoption/send/list");

	}

}
