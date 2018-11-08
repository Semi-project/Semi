package controller.charity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CharityFinishController
 */
@WebServlet("/charity/finish")
public class CharityFinishController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/member/login"); // 2018- 10- 23 일 정리
			return;
		}
		if (req.getSession().getAttribute("charity") == null) {
			resp.sendRedirect("/main"); // 2018- 10- 23 일 정리
			return;
		} else {
			req.getSession().removeAttribute("charity");

		}
		req.getRequestDispatcher("/view/charity/charityfinish.jsp").forward(req, resp);

	}

}
