package file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.file.FileUpload;
import service.file.FileUploadImpl;


/**
 * Servlet implementation class Test
 */
@WebServlet("/file/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L ;
	private FileUpload fileUpload = new FileUploadImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("/s/1/1-1.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴

		// System.out.println("boardcate:" + req.getParameter("boardcate"));
		fileUpload.write(req, resp);

	}
}
