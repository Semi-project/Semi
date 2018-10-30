package service.animal;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.animal.AnimalDao;
import dao.animal.AnimalDaoImpl;
import dao.animal.SpeciesDao;
import dao.animal.SpeciesDaoImpl;
import dao.file.animal.Animal_FileDao;
import dao.file.animal.Animal_FileDaoImpl;
import dto.animal.Animal;
import dto.file.Animal_Filetb;
import util.Paging;

public class AnimalServiceImpl implements AnimalService {

	// dto
	private Animal animal;

	// dao
	private AnimalDao animalDao = new AnimalDaoImpl();
	private Animal_FileDao animal_fileDao = new Animal_FileDaoImpl();

	@Override
	public Animal getParam(HttpServletRequest req, HttpServletResponse resp) {

		animal = new Animal();

		animal.setAnimal_Code(Integer.parseInt(req.getParameter("animal_code")));

		return animal;
	}

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
	public void write(HttpServletRequest req, HttpServletResponse resp) {

		animal = new Animal();

		try {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			System.out.println("멀티파트 아님");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 임시저장소
		ServletContext context = req.getServletContext();
		File repository = new File(context.getRealPath("tmp"));
		factory.setRepository(repository);

		// 업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 용량 제한 설정 : 10MB
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		// 한글 처리
		upload.setHeaderEncoding("utf-8");

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 파싱된 데이터 처리
		Iterator<FileItem> iter = items.iterator();

		// iterator로 요청정보 처리
		while (iter.hasNext()) {

			FileItem item = iter.next();

			//빈 파일 처리
			if(item.getSize() <=0)
				continue;
			if(item.isFormField()) {

				try {
					if("name".equals(item.getFieldName())) {
						// 데이터 처리
						// 동물 이름 넣기
						animal.setAnimal_Name(item.getString("utf-8"));

					} else if ("age".equals(item.getFieldName())) {
						// 나이
						animal.setAnimal_Age(Integer.parseInt(item.getString("utf-8")));

					} else if ("gender".equals(item.getFieldName())) {
						// 성별
						animal.setAnimal_Gender_Code(item.getString("utf-8"));

					} else if ("weight".equals(item.getFieldName())) {
						// 체중
						animal.setAnimal_Gr(item.getString("utf-8"));

					} else if ("nueter".equals(item.getFieldName())) {
						// 중성화
						animal.setAnimal_Neuters(item.getString("utf-8"));

						// } else if ("species".equals(item.getFieldName())) {
						// 중성화
						// animal.setSpecies_Code(Integer.parseInt(item.getString("UTF-8")));

					}else if ("content".equals(item.getFieldName())) {
						// 특징
						String content = item.getString("utf-8");
						animal.setAnimal_Feature(content);					

						// 동물 코드 가져오기
						int animalCode = animalDao.selectSeqNextval();
						animal.setAnimal_Code(animalCode);
						animal.setStatus(0);

						// System.out.println("값을 받아올 수 있다?");


						//////////////////////////////////////////////
						animalDao.insertAnimal(animal);
						Animal_Filetb file = new Animal_Filetb();

						// 위에서 생성한 동물코드 입력
						file.setAnimal_Code(animalCode);

						StringTokenizer str = new StringTokenizer(content, " =><\"", false);

						while(str.hasMoreTokens()) {
							//						int i = 0;
							String data = str.nextToken();
							if(data.equals(" ")) {
							} else if (data.equals("=")) {
							} else if (data.equals(">")) {

							} else if (data.equals("<")) {

							} else if (data.equals("\"")) {

							} else {
								if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
										|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
										|| data.contains(".BMP") || data.contains(".bmp")) {

									// System.out.println(i+","+data);
									// System.out.println("---------");
									if (data.contains("&#10")) {
										String data2 = data.substring(0, data.length() - 5);
										if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
												|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
												|| data.contains(".BMP") || data.contains(".bmp")) {
											// System.out.println(data2);
											file.setFile_SaveName(data2);
										}
										// JPG, GIF, PNG, BMP
									} else {
										file.setFile_OriginName(data);
										//									System.out.println("1: " + file);
										if (file.getFile_SaveName() != null) {
											//										file.setAnimal_Code(animalDao.selectSeqNextval());
											file.setFileno(animal_fileDao.selectFileno());
											//										System.out.println("2:" + file);
											animal_fileDao.insertFiletb(file);

										}

									}
								}
								// i++;
							} // end of while
						}
//								i++;
					}
				} catch(UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}// end of if(item.isFormField())

			else {
				// 파일일 경우
				Animal_Filetb file = new Animal_Filetb();

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID();
				// System.out.println(uuid);

				String u = uuid.toString().split("-")[4];
				// System.out.println(u);
				// -----------------

				// 로컬 저장소 파일
				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);

				file.setAnimal_Code(animal.getAnimal_Code());
				file.setFile_SaveName(up.getName());
				file.setFile_OriginName(item.getName());
				file.setFileno(animal_fileDao.selectFileno());

				animal_fileDao.insertFiletb(file);

				try {
					// 실제 업로드
					item.write(up);

					// 임시 파일 삭제
					item.delete();


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

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
	public List getPagingListAdmin(Paging paging) {
		return animalDao.selectPagingListAdmin(paging);
	}

	@Override
	public List selectPagingListUser(Paging paging) {
		return animalDao.selectPagingListUser(paging);
	}

	@Override
	public List getSpecies() {

		SpeciesDao speciesDao = new SpeciesDaoImpl();

		return speciesDao.selectSpeciesAll();
	}

	@Override
	public void animalListDelete(String codes) {

		animal_fileDao.deleteAnimalListFile(codes);
		animalDao.deleteAnimalList(codes);

	}

	@Override
	public void animalListAccept(String codes) {

		animalDao.acceptAnimalList(codes);

	}

	@Override
	public int getCountAcpt() {
		return animalDao.selectCntAcpt();
	}

}
