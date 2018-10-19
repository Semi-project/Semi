package dto.file;

public class Animal_Filetb {


   //파일번호
   private int fileno;
   
   //게시글코드
   private int animal_Code;
   
   //파일원본이름
   private String file_OriginName;
   
   //파일저장경로
   private String filepath;
   
   //파일크기
   private int filesize;
   
   //파일타입(확장자)
   private String filetype;
   
   //파일저장이름
   private String file_SaveName;

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

   public String getFilepath() {
      return filepath;
   }

   public void setFilepath(String filepath) {
      this.filepath = filepath;
   }

   public int getFilesize() {
      return filesize;
   }

   public void setFilesize(int filesize) {
      this.filesize = filesize;
   }

   public String getFiletype() {
      return filetype;
   }

   public void setFiletype(String filetype) {
      this.filetype = filetype;
   }

   public String getFile_SaveName() {
      return file_SaveName;
   }

   public void setFile_SaveName(String file_SaveName) {
      this.file_SaveName = file_SaveName;
   }

   @Override
   public String toString() {
      return "Animal_Filetb [fileno=" + fileno + ", animal_Code=" + animal_Code + ", file_OriginName="
            + file_OriginName + ", filepath=" + filepath + ", filesize=" + filesize + ", filetype=" + filetype
            + ", file_SaveName=" + file_SaveName + "]";
   }

   
   
   

	// 파일번호
	private int fileno;

	// 게시글코드
	private int animal_Code;

	// 파일원본이름
	private String file_OriginName;

	// 파일저장경로
	private String filepath;

	// 파일크기
	private int filesize;

	// 파일타입(확장자)
	private String filetype;

	// 파일저장이름
	private String file_SaveName;

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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFile_SaveName() {
		return file_SaveName;
	}

	public void setFile_SaveName(String file_SaveName) {
		this.file_SaveName = file_SaveName;
	}

	@Override
	public String toString() {
		return "Animal_Filetb [fileno=" + fileno + ", animal_Code=" + animal_Code + ", file_OriginName="
				+ file_OriginName + ", filepath=" + filepath + ", filesize=" + filesize + ", filetype=" + filetype
				+ ", file_SaveName=" + file_SaveName + "]";
	}


}