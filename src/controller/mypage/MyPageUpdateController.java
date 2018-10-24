package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class MyPageUpdateController
 */
@WebServlet("/mypage/update")
public class MyPageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService= new MemberServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doPost(req, resp);
    }
}
