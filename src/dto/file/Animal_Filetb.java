package dto.file;

public class Animal_Filetb {


	//파일번호
	private int fileno;

	//게시글코드
	private int animal_Code;

	//파일원본이름
	private String file_OriginName;

	//파일저장이름
	private String file_SaveName;

	@Override
	public String toString() {
		return "Animal_Filetb [fileno=" + fileno + ", animal_Code=" + animal_Code + ", file_OriginName=" + file_OriginName
				+ ", file_SaveName=" + file_SaveName + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getAnimal_Code() {
		return animal_Code;
	}

	public void setAnimal_Code(int animal_Code) {
		this.animal_Code = animal_Code;
	}

	public String getFile_OriginName() {
		return file_OriginName;
	}

	public void setFile_OriginName(String file_OriginName) {
		this.file_OriginName = file_OriginName;
	}

	public String getFile_SaveName() {
		return file_SaveName;
	}

	public void setFile_SaveName(String file_SaveName) {
		this.file_SaveName = file_SaveName;
	}


}