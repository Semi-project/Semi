package service.adoption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.adoption.AdoptionDao;
import dao.adoption.AdoptionDaoImpl;
import dao.animal.AnimalDao;
import dao.animal.AnimalDaoImpl;
import dto.adoption.Adoption;
import dto.animal.Animal;
import dto.file.Animal_Filetb;
import util.Paging;

public class AdoptionServiceImpl implements AdoptionService {
	private AdoptionDao adoptionDao = new AdoptionDaoImpl();
	private AnimalDao animalDao = new AnimalDaoImpl();

	@Override
	public Adoption getParam(HttpServletRequest req, HttpServletResponse resp) {
		// 요청파라미터 정보를 저장할 DTO객체
		Adoption adoption = new Adoption();

		// 요청파라미터 받기
		String adoption_code = req.getParameter("adoption_code");
		String animal_name = req.getParameter("animal_name");
		// null이나 ""이 아니면 int로 변환하여 DTO에 저장
		if (adoption_code != null && !"".equals(adoption_code)) {
			adoption.setAdoptionCode(Integer.parseInt(adoption_code));
		}
		if (animal_name != null && !"".equals(animal_name)) {
			adoption.setAnimalName(animal_name);
		}
		// 요청파라미터가 객체로 변환된 DTO 반환
		return adoption;

	}

	@Override
	public int getCurPage(HttpServletRequest req) {

		// 요청파라미터 받기
		String curPage = req.getParameter("curPage");

		// null이나 ""이 아니면 int로 리턴
		if (curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}

		// null이나 "" 면 0으로 반환
		return 0;
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		// 요청파라미터 받기
		String search = req.getParameter("search");

		return search;
	}

	@Override
	public List<Adoption> getList() {
		return adoptionDao.selectAll();
	}

	@Override
	public int getTotalCount(String search) {
		return adoptionDao.selectAdoptionCntAll(search);
	}

	@Override
	public List<Adoption> getPagingList(Paging paging) {
		return adoptionDao.selectPagingList(paging);
	}

	@Override
	public Adoption view(Adoption adoptionView) {
		return adoptionDao.selectAdoptionByAdoption_code(adoptionView);
	}

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {

		String animal_name = req.getParameter("q1");
		String adoption_reason = req.getParameter("q2");
		String adoption_exp = req.getParameter("q3");
		String adoption_curanimal = req.getParameter("q4");
		String adoption_housing = req.getParameter("q5");
		String adoption_CallTime = req.getParameter("q6");
		int adoption_code = adoptionDao.selectSeqNextval();
		Animal animal = new Animal();
		animal.setAnimal_Name(animal_name);
		int animal_code = animalDao.animal_code(animal);
		Adoption adoption = new Adoption();
		adoption.setAdoptionCalltime(adoption_CallTime);
		adoption.setAdoptionCode(adoption_code);
		adoption.setAdoptionCuranimal(adoption_curanimal);
		adoption.setAdoptionExp(adoption_exp);
		adoption.setAdoptionHousing(adoption_housing);
		adoption.setAdoptionReason(adoption_reason);

		adoption.setAnimalCode(animal_code);
		adoption.setAnimalName(animal_name);
		adoption.setUserid((String) req.getSession().getAttribute("userid"));

		adoptionDao.insertAdoption(adoption);
	}

	@Override
	public void delete(Adoption adoption) {
		adoptionDao.delete(adoption);
	}

	@Override
	public void deleteAdoptionList(String names) {
		adoptionDao.deleteAdoptionList(names);
	}

	@Override
	public void update(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
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
	public void updateList(String names) {
		// TODO Auto-generated method stub
		adoptionDao.updateAdoptionList(names);
	}

	@Override
	public int getStatus(Adoption adoption) {
		return adoptionDao.selectStatusbyanimalName(adoption);
	}

	@Override
	public Adoption getByanimalCode(Animal animal) {
		
		Adoption adoption = new Adoption();
		
		adoption.setAnimalCode(animal.getAnimal_Code());
		
		adoption = adoptionDao.getByanimalCode(adoption);
		
		return adoption;
	}

}
