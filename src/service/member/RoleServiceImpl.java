package service.member;

import dao.member.RoleDao;
import dao.member.RoleDaoImpl;
import dto.member.Member;
import dto.member.Role;

public class RoleServiceImpl implements RoleService {

	@Override
	public Role selectByUserId(Member member) {
		RoleDao roleDao = new RoleDaoImpl();

		return null;
	}

}
