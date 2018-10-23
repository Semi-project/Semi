package dao.animal;

import java.util.List;

import dto.animal.Species;

public interface SpeciesDao {
	public List<Species> selectSpeciesAll();
}
