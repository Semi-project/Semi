package controller.adoption.apply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import service.adoption.AdoptionService;
import service.adoption.AdoptionServiceImpl;

/**
 * Servlet implementation class AdoptionFormDeleteController
 */
@WebServlet("/adoption/application/delete")
public class AdoptionFormDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdoptionService adoptionService = new AdoptionServiceImpl();

    @Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    	//요청파라미터 -> MODEL
    			Adoption adoption= adoptionService.getParam(req, resp);
    			
    			//게시글 삭제
    			adoptionService.delete(adoption);
    			
    			//리스트로 리다이렉트
    			resp.sendRedirect("/adoption/application/list");
    	}
}
