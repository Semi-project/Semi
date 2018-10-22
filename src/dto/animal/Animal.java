package dto.animal;

public class Animal {

	//동물코드
	private int animal_Code;

	//동물나이
	private int animal_Age;

	//성별코드
	private String animal_Gender_Code;

	//체중
	private String animal_Gr;

	//중성화
	private String animal_Neuters;

	//특징
	private String animal_Feature;

	//승인상태
	private int status;

	// 품종코드
	private int species_Code;
	
	// 품종명
	private String species;

	//동물 이름
	private String animal_Name;

	

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getAnimal_Code() {
		return animal_Code;
	}

	public void setAnimal_Code(int animal_Code) {
		this.animal_Code = animal_Code;
	}

	public int getAnimal_Age() {
		return animal_Age;
	}

	public void setAnimal_Age(int animal_Age) {
		this.animal_Age = animal_Age;
	}

	public String getAnimal_Gender_Code() {
		return animal_Gender_Code;
	}

	public void setAnimal_Gender_Code(String animal_Gender_Code) {
		this.animal_Gender_Code = animal_Gender_Code;
	}

	public String getAnimal_Gr() {
		return animal_Gr;
	}

	public void setAnimal_Gr(String animal_Gr) {
		this.animal_Gr = animal_Gr;
	}

	public String getAnimal_Neuters() {
		return animal_Neuters;
	}

	public void setAnimal_Neuters(String animal_Neuters) {
		this.animal_Neuters = animal_Neuters;
	}

	public String getAnimal_Feature() {
		return animal_Feature;
	}

	public void setAnimal_Feature(String animal_Feature) {
		this.animal_Feature = animal_Feature;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSpecies_Code() {
		return species_Code;
	}

	public void setSpecies_Code(int species_Code) {
		this.species_Code = species_Code;
	}

	public String getAnimal_Name() {
		return animal_Name;
	}

	public void setAnimal_Name(String animal_Name) {
		this.animal_Name = animal_Name;
	}

	@Override
	public String toString() {
		return "Animal [animal_Code=" + animal_Code + ", animal_Age=" + animal_Age + ", animal_Gender_Code="
				+ animal_Gender_Code + ", animal_Gr=" + animal_Gr + ", animal_Neuters=" + animal_Neuters
				+ ", animal_Feature=" + animal_Feature + ", status=" + status + ", species_Code=" + species_Code
				+ ", animal_Name=" + animal_Name + ", species=" + species + "]";
	}


}

