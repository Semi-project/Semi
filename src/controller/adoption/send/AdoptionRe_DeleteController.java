package controller.adoption.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import dto.file.Animal_Filetb;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;


@WebServlet("/adoption/send/delete")
public class AdoptionRe_DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AnimalService animalService = new AnimalServiceImpl();
	private Animal_Filetb animal_filetb =  new Animal_Filetb();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		boolean sent = false;
		
		Animal animal = animalService.getParam(req, resp);
		
		animal_filetb.setAnimal_Code(animal.getAnimal_Code());

		animalService.deleteAnimalByAnimal_Code(animal, animal_filetb);

//		삭제 확인 alert 띄우기
		
//		PrintWriter out = resp.getWriter();
//
//		out.println("<script type=\"text/javascript\">");
//		out.println("alert('해당 동물이 목록에서 삭제되었습니다.');");
//		out.println("location='/view/adoption/send/adoptionSendView.jsp';");
//		out.println("</script>");

		resp.sendRedirect("/adoption/send/list");

	}
}
