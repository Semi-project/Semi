package dao.payment;

import dto.payment.Payment;

public interface PaymentDao {

	// 선택된 결제날짜코드로 결제날짜 조회
	public Payment selectPaymentDateByPaymentCode(Payment payment);
	
}
