package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.member.Deactivation;
import dto.member.Member;
import util.DBConn;

public class DeactivationDaoImpl implements DeactivationDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public void insertDeactivation(Deactivation deactivation) throws Exception {

		String sql = "";
		sql += "INSERT INTO DEACTiVATION (";
		sql += " USERID ,DEACT_SUGG , DEACT_DATE) ";
		sql += " VALUES (? , ? , sysdate )";

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			ps.setString(1, deactivation.getUserid());
			ps.setString(2, deactivation.getDeactSugg());

			ps.executeQuery();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}

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