package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.member.DeactivationService;
import service.member.DeactivationServiceImpl;

/**
 * Servlet implementation class MemberOutController
 */
@WebServlet("/member/out")
public class MemberOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeactivationService deactivationService = new DeactivationServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/member/out.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
