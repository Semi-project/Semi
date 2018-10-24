package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.member.Member;
import dto.member.Subscription;
import util.DBConn;

public class SubscriptionDaoImpl implements SubscriptionDao {

	private Member member = new Member();
	private Subscription subScription = new Subscription();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = DBConn.getConnection();

	@Override
	public int insertSubscription(Subscription sub) {
		String sql = "";
		sql += "INSERT INTO subscription(SUBSCRIPTION_NO,EMAIL_SUBSCRIPTION,SMS_SUBSCRIPTION) VALUES(subscription_seq.nextval,?,?)";
		int cnt = -1;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sub.getEmailSubscription());
			ps.setInt(2, sub.getSmsSubscription());
			cnt = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cnt;
	}

	@Override
	public Subscription selectSubscriptionByuserId(Member member) {

		return null;
	}

	@Override
	public Subscription updateSubscription(Subscription sub) {

		return null;
	}

}
