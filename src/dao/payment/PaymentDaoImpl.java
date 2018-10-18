package dao.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.payment.Payment;
import util.DBConn;

public class PaymentDaoImpl implements PaymentDao{

	private Connection conn = DBConn.getConnection();
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private String sql;
	
	@Override
	public Payment selectPaymentDateByPaymentCode(Payment payment) {

		sql = "SELECT * FROM Payment WHERE charity_Payment_Code=?";

		// Payment의 charity_payment_code 가져오기
		int charity_Payment_Code = payment.getCharity_Payment_Code();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, charity_Payment_Code);
			
			rs = ps.executeQuery();
			
			while(rs.next())
				payment.setPayment_Date(rs.getString("payment_Date"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return payment;
	}

}
