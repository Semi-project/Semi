package service.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.animal.AnimalDao;
import dao.animal.AnimalDaoImpl;
import dao.animal.SpeciesDao;
import dao.animal.SpeciesDaoImpl;
import dao.file.Animal_FileDao;
import dao.file.Animal_FileDaoImpl;
import dto.animal.Animal;
import dto.file.Animal_Filetb;

public class AnimalServiceImpl implements AnimalService {

	// dao
	private AnimalDao animalDao = new AnimalDaoImpl();
	private SpeciesDao speciesDao = new SpeciesDaoImpl();
	private Animal_FileDao animal_fileDao = new Animal_FileDaoImpl();

	@Override
	public List<Animal> selectAnimal() {
		 
		return animalDao.selectAnimal();
	}

	@Override
	public List<Animal> selectAnimalnotAutho() {
		
		return animalDao.selectAnimalnotAutho();
	}
	
	@Override
	public Animal selectAnimalByanimal_Code(Animal animal) {
		
		return animalDao.selectAnimalByanimal_Code(animal);
		
		
	}

	@Override
	public void insertAnimal(Animal animal, Animal_Filetb animal_file) {
		
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
		
		animal.setAnimal_Feature(req.getParameter("feature"));
		
		return animal;
	}

	

}
