package service.member;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.member.Member;
import util.Paging;

public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Member selectMemberByUserId(Member member) {
		return memberDao.selectMemberByUserId(member);
	}

	@Override
	public void updateMember(HttpServletRequest req, Member member) throws Exception {
		memberDao.updateMember(member);

	}

	@Override
	public int updateMember(Member member) throws Exception {
		int result = 0;

		result = memberDao.updateMember(member);

		return result;

	}

	@Override
	public int updateMemberPassword(Member member) {

		int result = 0;

		result = memberDao.updateMemberPassword(member);

		return result;

	}

	@Override
	public void deleteMemberByUserId(Member member) throws Exception {
		memberDao.deleteMemberByUserId(member);

	}

	@Override
	public List<Member> searchUserId(Member member) {
		int cnt = memberDao.selectCntMemberByUserEmailAndName(member);
		List<Member> m = new ArrayList<Member>();
		if (cnt > 0) {
			m = memberDao.selectMemberByUserEmailAndName(member);
		} else {

			return m;
		}
		return m;
	}

	@Override
	public Member searchUserPw(Member member) {
		int cnt = memberDao.selectCntMemberPwByuserInfo(member);
		Member m = new Member();
		if (cnt == 1) {
			m = memberDao.selectMemberPwByuserInfo(member);
			return m;
		} else {
			return null;
		}

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

	@Override
	public List<Member> getAllList(Paging paging) {

		return memberDao.selectMemberAll(paging);
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}

	@Override
	public int getCurPage(HttpServletRequest req) {
		// 요청파라미터 받기
		String curPage = req.getParameter("curPage");

		// null이나 ""이 아니면 int로 리턴
		if (curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}

		// null이나 "" 면 0으로 반환
		return 0;
	}

	@Override
	public int getTotalCount(String search) {
		return memberDao.selectCntAll(search);
	}

	@Override
	public boolean selectUserPwCheck(Member member) {
		boolean flag = false;

		flag = memberDao.selectUserPwCheck(member);
		return flag;
	}

}
