package service.animal;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.animal.AnimalDao;
import dao.animal.AnimalDaoImpl;
import dao.animal.SpeciesDao;
import dao.animal.SpeciesDaoImpl;
import dao.file.Animal_FileDao;
import dao.file.Animal_FileDaoImpl;
import dto.animal.Animal;
import dto.animal.Species;
import dto.file.Animal_Filetb;
import util.Paging;

public class AnimalServiceImpl implements AnimalService {

	// dao
	private AnimalDao animalDao = new AnimalDaoImpl();
	private SpeciesDao speciesDao = new SpeciesDaoImpl();
	private Animal_FileDao animal_fileDao = new Animal_FileDaoImpl();
	
	@Override
	public Animal selectAnimalByanimal_Code(Animal animal) {
		// 동물 정보 전체 조회
		return animalDao.selectAnimalByanimal_Code(animal);
		
	}

	@Override
	public void insertAnimal(Animal animal, Animal_Filetb animal_file) {
		
		String aa = "<img src=\"/test/upload/8694caea-c578-4475-8e72-e3071d9a32be.png&#10;\" "
				+ "title=\"KakaoTalk_20180704_142633880.png\" sqeid=\"QE_154001706426841272\" "
				+ "style=\"border-color: rgb(0, 0, 0);\">"
				+ "<br style=\"clear:both;\">"
				+ "<img src=\"/test/upload/0fa34ef2-9762-42e7-9801-60c09d02e621.png&#10;\" "
				+ "title=\"KakaoTalk_20180704_142650792.png\">"
				+ "<br style=\"clear:both;\">"
				+ "<img src=\"/test/upload/3dce59c0-946d-48a3-a7c2-01095af20a7e.png&#10;\" "
				+ "title=\"KakaoTalk_20180703_095638354.png\">"
				+ "<br style=\"clear:both;\">"
				+ "<img src=\"/test/upload/778df83d-ae6d-480f-b65d-d130bc1567eb.png&#10;\" "
				+ "title=\"KakaoTalk_20180703_124235592.png\">"
				+ "<br style=\"clear:both;\">"
				+ "<img src=\"/test/upload/7392b60d-c086-4969-9d1b-4cb4641c4f28.png&#10;\" "
				+ "title=\"KakaoTalk_20180704_142340732.png\">"
				+ "<br style=\"clear:both;\">"
				+ "<img src=\"/test/upload/c2df374d-ecaa-4a7f-9b62-20548ae71a2a.jpg&#10;\" "
				+ "title=\"adopt.jpg\" sqeid=\"QE_154001656851262227\" "
				+ "style=\"border-color: rgb(0, 0, 0);\">"
				+ "<br style=\"clear:both;\"><p><br></p>";

	      StringTokenizer str = new StringTokenizer(aa, " =><\"", false);
	      int i = 0;
	      while (str.hasMoreTokens()) {
	         String data = str.nextToken();
	         if (data.equals(" ")) {
	         } else if (data.equals("=")) {
	         } else if (data.equals(">")) {

	         } else if (data.equals("<")) {

	         } else if (data.equals("\"")) {

	         } else {
	            if (data.contains(".png") || data.contains(".jpg") || data.contains(".GIF") || data.contains(".BMP")) {

//	                System.out.println(i+","+data);
	               // System.out.println("---------");
	               if (data.contains("&#10")) {
	                  String data2 = data.substring(0, data.length() - 5);
	                  if (data2.contains(".png") || data2.contains(".jpg") || data2.contains(".GIF")
	                        || data2.contains(".BMP")) {
	                     System.out.println(data2);
	                  }
	                  // JPG, GIF, PNG, BMP
	               }else {
	                  System.out.println(data);
	               }
	            }
	         }
	         i++;
	      }
	      
		// 동물정보 입력
		animalDao.insertAnimal(animal);
		
		// 동물 사진 입력
		animal_fileDao.insertFiletb(animal_file);
		

	}

	@Override
	public void updateAnimalByAnimal_Code(Animal animal) {
		 
		animalDao.updateAnimalByAnimal_Code(animal);

	}

	@Override
	public void deleteAnimalByAnimal_Code(Animal animal, Animal_Filetb animal_filetb) {

		// 해당 동물사진 삭제
		animal_fileDao.deleteFiletbByAnimalCode(animal_filetb);
		
		// 해당 동물정보 삭제
		animalDao.deleteAnimalByAnimal_Code(animal);
		
	}

	@Override
	public Animal getParam(HttpServletRequest req, HttpServletResponse resp) {

		Animal animal = new Animal();
		
		int code = Integer.parseInt(req.getParameter("animal_code"));
		int age = Integer.parseInt(req.getParameter("age"));
		int species = Integer.parseInt(req.getParameter("species"));
		
		animal.setAnimal_Code( code);
		animal.setAnimal_Name( req.getParameter("name"));
		animal.setAnimal_Age( age);
		animal.setAnimal_Gender_Code( req.getParameter("gender"));
		animal.setAnimal_Gr( req.getParameter("weight"));
		animal.setAnimal_Neuters( req.getParameter("neuter"));
		animal.setSpecies_Code( species);
		animal.setAnimal_Feature( req.getParameter("content"));
		
		return animal;
	}

	@Override
	public int getTotalCount() {
		return animalDao.selectCntAll();
	}

	@Override
	public int getCurPage(HttpServletRequest req) {

		String curPage = req.getParameter("curPage");
		
		if (curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
		
		return 0;
	}

	@Override
	public List getPagingListAuth(Paging paging) {
		return animalDao.selectPagingListAuth(paging);
	}

	@Override
	public List getPagingListUnauth(Paging paging) {
		return animalDao.selectPagingListUnauth(paging);
	}

	@Override
	public List<Species> getSpecies() {
		
		SpeciesDao speciesDao = new SpeciesDaoImpl();
		
		return speciesDao.selectSpeciesAll();
	}
	
	

	

}
