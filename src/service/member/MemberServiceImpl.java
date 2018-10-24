package service.member;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.member.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Member login(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member selectMemberByUserId(Member member) {
		return memberDao.selectMemberByUserId(member);
	}

	@Override
	public void updateMember(HttpServletRequest req) throws Exception {
			Member member = null;
		if (member != null ) {
			memberDao.updateMember(member);
		}
	}

	@Override
	public void updateMemberPassword(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMemberByUserId(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member searchUserId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member searchUserPw(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getParam(HttpServletRequest req, HttpServletResponse resp) {
		Member member = new Member();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

		member.setUserid(req.getParameter("userid"));
		try {
			if ((req.getParameter("userbirth") != null))
				member.setUserbirth(sdFormat.parse((req.getParameter("userbirth"))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		member.setName(req.getParameter("username"));
		// System.out.println(member.getName());
		member.setUserpw(req.getParameter("userpw"));
		member.setGender(req.getParameter("gender"));
		member.setAddress(req.getParameter("address"));
		member.setPhone(req.getParameter("smartPhone"));
		member.setEmail(req.getParameter("email"));

//		System.out.println(req.getParameter("subscriptionNews"));
//		System.out.println(req.getParameter("subscriptionSms"));
//		
		return member;
	}

	@Override
	public int join(Member param) {

		return memberDao.insertMember(param);

	}

	@Override
	public int checkUserId(Member member) {

		return memberDao.selectCntMemberByUserId(member);
	}

	@Override
	public int checkUserEmail(Member member) {
		// TODO Auto-generated method stub
		return memberDao.selectCntMemberByUserEmail(member);
	}

	@Override
	public int checkUser(Member member) {

		return memberDao.selectCntMemberByUser(member);
	}

}
