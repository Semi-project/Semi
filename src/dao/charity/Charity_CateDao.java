package dao.charity;

import dto.charity.Charity_Cate;

public interface Charity_CateDao {

	// 선택된 후원종류로 후원이름 조회
	public Charity_Cate selectCharityNameByCharityCateCode(Charity_Cate charity_Cate);
	
}
