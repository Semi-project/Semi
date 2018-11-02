package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.board.Review_Board;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;
import service.board.review.Review_BoardService;
import service.board.review.Review_BoardServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Review_BoardService reviewService = new Review_BoardServiceImpl();
	private AnimalService animalService = new AnimalServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Review_Board> list = new ArrayList<Review_Board>();
		list = reviewService.getList();
		req.setAttribute("list", list);
		List<Animal>animallist = animalService.selectAllAnimal();
		req.setAttribute("animalList", animallist);
		
		req.getRequestDispatcher("/view/main.jsp").forward(req, resp);
	}

}
