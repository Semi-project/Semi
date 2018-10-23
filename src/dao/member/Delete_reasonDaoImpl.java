package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.member.Delete_reason;
import util.DBConn;

public class Delete_reasonDaoImpl implements Delete_reasonDao {

	private Delete_reason delete_Reason = new Delete_reason();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public void insertDeleteReason(Delete_reason delete_Reason) {
		// TODO Auto-generated method stub

	}

	@Override
	public Delete_reason selectDeleteReason(Delete_reason delete_Reason) {
		// TODO Auto-generated method stub
		return null;
	}

}
