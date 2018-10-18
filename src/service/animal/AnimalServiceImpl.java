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

public class AnimalServiceImpl implements AnimalService {

	// dao
	private AnimalDao animalDao = new AnimalDaoImpl();
	private SpeciesDao speciesDao = new SpeciesDaoImpl();
	private Animal_FileDao animal_fileDao = new Animal_FileDaoImpl();

	@Override
	public List<Animal> selectAnimal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal selectAnimalByanimal_Code(Animal animal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAnimal(Animal animal, Animal_FileDao animal_file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAnimalByAnimal_Code(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAnimalByAnimal_Code(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public Animal getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
