package service.adoption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.adoption.AdoptionDao;
import dao.adoption.AdoptionDaoImpl;
import dao.animal.AnimalDao;
import dao.animal.AnimalDaoImpl;
import dto.adoption.Adoption;
import dto.file.Animal_Filetb;
import util.Paging;

public class AdoptionServiceImpl implements AdoptionService {
	   private AdoptionDao adoptionDao = new AdoptionDaoImpl();
	   private AnimalDao animalDao = new AnimalDaoImpl();

	   @Override
	   public List<Adoption> getPagingList(Paging paging) {
	      // TODO Auto-generated method stub
	      return null;
	   }

	   @Override
	   public Adoption getParam(HttpServletRequest req, HttpServletResponse resp) {
	      // TODO Auto-generated method stub
	      return null;
	   }

	   

	   @Override
	   public void delete(Adoption adoption) {
	      // TODO Auto-generated method stub

	   }

	   @Override
	   public void update(HttpServletRequest req) {
	      // TODO Auto-generated method stub
	      
	   }

	@Override
	public int getCurPage(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Adoption> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNick(Adoption adoption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal_Filetb viewFile(Adoption adoption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}


}
