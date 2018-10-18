package dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	public void insertSubscription(Subscription sub) {

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
