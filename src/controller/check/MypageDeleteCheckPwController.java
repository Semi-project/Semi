package controller.check;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import service.member.MemberService;
import service.member.MemberServiceImpl;

@WebServlet("/mypage/deletcheck")
public class MypageDeleteCheckPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
       
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    req.setAttribute("userid",(String)req.getSession().getAttribute("userid"));
    
    req.getRequestDispatcher("/view/check/deleteuserpwcheck.jsp").forward(req, resp);
    
    
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    	Member member = new Member();
    	
    	member.setUserid(req.getParameter("userid"));
    	
    	member.setUserpw(req.getParameter("userpw"));
    	
    	boolean flag = false;
    	
    	flag = memberService.selectUserPwCheck(member);
    	
    	if(flag == true) {
    		
    	Member memberView = memberService.selectMemberByUserId(member);
    	
    	req.setAttribute("memberView", memberView);
    	resp.sendRedirect("/mypage/delete");
    	}
    	else {
    		String msg = " 비밀번호를 다시 입력해주세요 !! ";
    		String result = "fail";
    		
    		req.setAttribute("msg", msg);
    		req.setAttribute("result", result);
    		
    		req.getRequestDispatcher("/view/check/deleteuserpwcheck.jsp").forward(req , resp);
    	}
    
    }
}
