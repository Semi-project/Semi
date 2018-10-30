package dto.adoption;

public class Adoption {
	// 동물코드
	private int animalCode;
	private String animalName;

	// 입양결정사유
	private String adoptionReason;

	// 반려동물경험
	private String adoptionExp;

	// 현재키우고있는동물
	private String adoptionCuranimal;

	// 주거형태
	private String adoptionHousing;

	// 통화시간
	private String adoptionCalltime;

	// 회원아이디
	private String userid;

	// 입양신청서코드
	private int adoptionCode;

	// 승인상태
	private int status;

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public int getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(int animalCode) {
		this.animalCode = animalCode;
	}

	public String getAdoptionReason() {
		return adoptionReason;
	}

	public void setAdoptionReason(String adoptionReason) {
		this.adoptionReason = adoptionReason;
	}

	public String getAdoptionExp() {
		return adoptionExp;
	}

	public void setAdoptionExp(String adoptionExp) {
		this.adoptionExp = adoptionExp;
	}

	public String getAdoptionCuranimal() {
		return adoptionCuranimal;
	}

	public void setAdoptionCuranimal(String adoptionCuranimal) {
		this.adoptionCuranimal = adoptionCuranimal;
	}

	public String getAdoptionHousing() {
		return adoptionHousing;
	}

	public void setAdoptionHousing(String adoptionHousing) {
		this.adoptionHousing = adoptionHousing;
	}

	public String getAdoptionCalltime() {
		return adoptionCalltime;
	}

	public void setAdoptionCalltime(String adoptionCalltime) {
		this.adoptionCalltime = adoptionCalltime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAdoptionCode() {
		return adoptionCode;
	}

	public void setAdoptionCode(int adoptionCode) {
		this.adoptionCode = adoptionCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Adoption [animalCode=" + animalCode + ", animalName=" + animalName + ", adoptionReason="
				+ adoptionReason + ", adoptionExp=" + adoptionExp + ", adoptionCuranimal=" + adoptionCuranimal
				+ ", adoptionHousing=" + adoptionHousing + ", adoptionCalltime=" + adoptionCalltime + ", userid="
				+ userid + ", adoptionCode=" + adoptionCode + ", status=" + status + "]";
	}

}
