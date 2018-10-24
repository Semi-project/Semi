package dao.adoption;

import java.util.List;

import dto.adoption.Adoption;
import util.Paging;

public class AdoptionDaoImpl implements AdoptionDao {

	@Override
	public List<Adoption> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adoption> selectPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectAdoptionCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Adoption selectAdoptionByAnimal_Code(Adoption adoption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adoption selectAdoptionByUserId(Adoption adoption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdoptionByUserId(Adoption adoption) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAdoptionByAnimal_Code(Adoption adoption) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAdoptionByUserId(Adoption adoption) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertAdoption(Adoption adoption) {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectSeqNextval() {
		// TODO Auto-generated method stub
		return 0;
	}

}
