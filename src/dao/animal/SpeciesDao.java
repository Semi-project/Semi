package dao.animal;

import java.util.List;

import dto.animal.Species;

public interface SpeciesDao {
	 
	// 전체 품종 조회
	public List selectSpeciesAll();
	
	// 멍멍이 조회
	public List<Species> selectDogs();
	
	// 냥냥이 조회
	public List<Species> selectCats();
	
	// 기타 조회
	public List<Species> selectEtc();
	
}
