package controller.adoption.send;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.file.animal.Animal_FileDao;
import dao.file.animal.Animal_FileDaoImpl;
import dto.animal.Animal;
import dto.file.Animal_Filetb;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

/**
 * Servlet implementation class AdoptionRe_DeleteListController
 */
@WebServlet("/adoption/send/deleteList")
public class AdoptionRe_DeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	private Animal_Filetb animal_filetb =  new Animal_Filetb();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String codes = req.getParameter("codes");

		if(!"".equals(codes) && codes != null) {
			animalService.animalListDelete(codes);
		}

		resp.sendRedirect("/adoption/send/list");

	}

}
