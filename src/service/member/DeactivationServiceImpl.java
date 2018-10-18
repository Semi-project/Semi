package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.member.Delete_reasonDao;
import dao.member.Delete_reasonDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.member.Member;

public class DeactivationServiceImpl implements DeactivationService {

	private MemberDao memberDao = new MemberDaoImpl();

	private Delete_reasonDao delete_ReasonDao= new Delete_reasonDaoImpl();
	

	@Override
	public void deleteMember(Member member) {

	}


	@Override
	public Member getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
