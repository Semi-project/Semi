package dto.charity;

public class Charity_Cate {

	private int charity_Cate_Code;
	private String charity_Name;
	
	@Override
	public String toString() {
		return "charity_cate (charity_Cate_Code=" + charity_Cate_Code
				+ ", charity_Name=" + charity_Name + ")";
	}
	
	public int getCharity_Cate_Code() {
		return charity_Cate_Code;
	}
	public void setCharity_Cate_Code(int charity_Cate_Code) {
		this.charity_Cate_Code = charity_Cate_Code;
	}
	public String getCharity_Name() {
		return charity_Name;
	}
	public void setCharity_Name(String charity_Name) {
		this.charity_Name = charity_Name;
	}
	
}
