package controller.charity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.charity.CharityService;
import service.charity.CharityServiceImpl;

@WebServlet("/charity/select")
public class ChraitySelectController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CharityService charityService =new CharityServiceImpl();

}
