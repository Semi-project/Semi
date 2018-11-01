package controller.charity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/charity/start")
public class Charitystart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doGet");
		System.out.println("animalCode");
		System.out.println(req.getParameter("animalCode"));
		System.out.println("animalName");
		System.out.println(req.getParameter("animalName"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().getAttribute("animalCode");
		req.getSession().getAttribute("animalName");
		System.out.println("doPost");
		System.out.println("animalCode");
		System.out.println(req.getSession().getAttribute("animalCode"));
		System.out.println("animalName");
		System.out.println(req.getSession().getAttribute("animalName"));
		
	}
	
}
