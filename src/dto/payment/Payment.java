package dto.payment;

public class Payment {
	private int charity_Payment_Code;
	private String payment_Date;
	
	
	
	@Override
	public String toString() {
		return "Payment [charity_Payment_Code=" + charity_Payment_Code
				+ ", payment_Date=" + payment_Date + "]";
	}

	public int getCharity_Payment_Code() {
		return charity_Payment_Code;
	}

	public void setCharity_Payment_Code(int charity_Payment_Code) {
		this.charity_Payment_Code = charity_Payment_Code;
	}

	public String getPayment_Date() {
		return payment_Date;
	}

	public void setPayment_Date(String payment_Date) {
		this.payment_Date = payment_Date;
	}
	
	
	
}
