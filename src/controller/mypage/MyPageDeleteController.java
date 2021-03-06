package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Deactivation;
import dto.member.Member;
import service.member.DeactivationService;
import service.member.DeactivationServiceImpl;
import service.member.MemberService;
import service.member.MemberServiceImpl;

/**
 * Servlet implementation class MyPageDeleteController
 */
@WebServlet("/mypage/delete")
public class MyPageDeleteController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private MemberService memberService = new MemberServiceImpl();
   private DeactivationService deactivationService = new DeactivationServiceImpl();
   
   @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      req.setAttribute("userid", (String)req.getSession().getAttribute("userid"));

      
      req.getRequestDispatcher("/view/mypage/delete.jsp").forward(req, resp);
   
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      req.setCharacterEncoding("UTF-8");
      
      
      Member member = new Member();
      member.setUserid((String)req.getSession().getAttribute("userid"));
      
      
      Deactivation deactivation = new Deactivation();
   
      
      deactivation.setDeactSugg(req.getParameter("deactSugg"));
      
      deactivation.setUserid((String)req.getSession().getAttribute("userid"));
      
      
      try {
         deactivationService.insert(deactivation);
      
         
            memberService.deleteMemberByUserId(member);
            


         
      
      } catch (Exception e) {
   
         req.getRequestDispatcher("/view/mypage/delete.jsp").forward(req, resp);
      }
      
   
      resp.sendRedirect("/member/logout");
   }
}