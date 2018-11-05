package controller.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.member.Member;
import service.member.MemberService;
import service.member.MemberServiceImpl;
import util.SMTPAuthenticatior;

/**
 * Servlet implementation class SendMailController
 */
@WebServlet("/send/mail")
public class SendMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		Member member = new Member();
		member.setName(req.getParameter("username"));
		member.setEmail(req.getParameter("useremail"));
		member.setUserid(req.getParameter("userid"));
		
		Member m = memberService.searchUserPw(member);

		if (m == null) {
			System.out.println("일치하는 정보가 없습니다.");
			resp.getWriter().print("실패");
			return;
		} else {
			
			Properties p = new Properties(); // 정보를 담을 객체

			p.put("mail.smtp.host", "smtp.naver.com"); // 네이버 SMTP

			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			try {
				Authenticator auth = new SMTPAuthenticatior();
				Session ses = Session.getInstance(p, auth);

				ses.setDebug(true);

				MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
				msg.setSubject("비밀번호를 안내해드리겠습니다"); // 제목

				Address fromAddr = new InternetAddress("tig00028@naver.com");
				msg.setFrom(fromAddr); // 보내는 사람

				Address toAddr = new InternetAddress(m.getEmail());
				msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

				msg.setContent("비밀번호는" + m.getUserpw() + "입니다", "text/html;charset=UTF-8"); // 내용과 인코딩

				Transport.send(msg); // 전송
			} catch (Exception e) {
				e.printStackTrace();
				// 오류 발생시 뒤로 돌아가도록
				return;
			}
			resp.getWriter().print("성공");
		}// endof else

	}

}
