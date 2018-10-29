package controller.adoption.apply;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		URLConnection conn = null;
		BufferedWriter bw = null;
		BufferedReader br = null;

		try {
			conn = new URL(
					"http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20180101&endde=20181030&pageNo=2&numOfRows=10&ServiceKey=cOxNjTFSNpe%2Bzduu3wvca3a%2F5O7nWgtqy0M%2FBQD6s6oMIMLL0bFMiFfHKhf9aCuYqMvYJ78h1GJUKZTA7z8l0g%3D%3D")
							.openConnection();
			// conn.addRequestProperty("Referer", "http://www.naver.com");

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			bw = new BufferedWriter(resp.getWriter());

			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);
			}

			System.out.println(line);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
