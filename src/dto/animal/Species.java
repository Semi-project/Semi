package dto.animal;

public class Species {

	//품종코드
	private int species_Code;
	
	//품종명
	private String species_Name;

	@Override
	public String toString() {
		return "Species [species_Code=" + species_Code + ", species_Name=" + species_Name + "]";
	}

	public int getSpecies_Code() {
		return species_Code;
	}

	public void setSpecies_Code(int species_Code) {
		this.species_Code = species_Code;
	}

	public String getSpecies_Name() {
		return species_Name;
	}

	public void setSpecies_Name(String species_Name) {
		this.species_Name = species_Name;
	}
	
	
}
