package dto.charity;

import java.util.Date;

public class Charity {

	private int charity_Code;
	private int charity_Cate_Code;
	private int charity_Amount;
	private String userId;
	private Date charity_Date;
	private int charity_Payment;
	
	@Override
	public String toString() {
		return "charity [charity_Code=" + charity_Code
				+ ", charity_Cate_Code=" + charity_Cate_Code
				+ ", charity_Amount=" + charity_Amount
				+ ", userId=" + userId
				+ ", charity_Date=" + charity_Date
				+ ", charity_Payment=" + charity_Payment + "]";
	}
	
	
	
	public int getCharity_Code() {
		return charity_Code;
	}
	public void setCharity_Code(int charity_Code) {
		this.charity_Code = charity_Code;
	}
	public int getCharity_Cate_Code() {
		return charity_Cate_Code;
	}
	public void setCharity_Cate_Code(int charity_Cate_Code) {
		this.charity_Cate_Code = charity_Cate_Code;
	}
	public int getCharity_Amount() {
		return charity_Amount;
	}
	public void setCharity_Amount(int charity_Amount) {
		this.charity_Amount = charity_Amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCharity_Date() {
		return charity_Date;
	}
	public void setCharity_Date(Date charity_Date) {
		this.charity_Date = charity_Date;
	}
	public int getCharity_Payment() {
		return charity_Payment;
	}
	public void setCharity_Payment(int charity_Payment) {
		this.charity_Payment = charity_Payment;
	}
	
	
}
