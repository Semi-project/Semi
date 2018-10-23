package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dto.member.Deactivation;
import dto.member.Member;
import util.DBConn;

public class DeactivationDaoImpl implements DeactivationDao {

	private Member member = new Member();
	private Deactivation deactivation = new Deactivation();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public DeactivationDao insertDeactivation(Deactivation deactivation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deactivation> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeactivationDao selectDeactivationByUserId(Deactivation deactivation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
