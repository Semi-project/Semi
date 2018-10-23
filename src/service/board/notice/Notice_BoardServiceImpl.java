package service.board.notice;

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

import dao.board.Notice_BoardDao;
import dao.board.Notice_BoardDaoImpl;
import dao.file.Notice_FileDao;
import dao.file.Notice_FileDaoImpl;
import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.Paging;

public class Notice_BoardServiceImpl implements Notice_BoardService {

	private Notice_BoardDao notice_BoardDao = new Notice_BoardDaoImpl();
	private Notice_FileDao notice_BoardFileDao = new Notice_FileDaoImpl();

	@Override
	public Notice_Board getParam(HttpServletRequest req, HttpServletResponse resp) {

		Notice_Board notice_Board = new Notice_Board();

		String boardno = req.getParameter("boardno");
		// null이나 ""이 아니면 int로 변환하여 DTO에 저장
		if (boardno != null && !"".equals(boardno)) {
			notice_Board.setBoardno(Integer.parseInt(boardno));
		}

		// 요청파라미터가 객체로 변환된 DTO 반환
		return notice_Board;
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
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return notice_BoardDao.selectNoticeBoardCntAll();
	}

	@Override
	public List<Notice_Board> getList() {
		// TODO Auto-generated method stub
		return notice_BoardDao.selectNoticeBoardAll();
	}

	@Override
	public List<Notice_Board> getPagingList(Paging paging) {

		return notice_BoardDao.selectNoticeBoardPagingList(paging);
	}

	@Override
	public Notice_Board view(Notice_Board boardView) {
		notice_BoardDao.updateHit(boardView);

		return notice_BoardDao.selectNoticeBoardByBoardNo(boardView);

	}

	public void write(HttpServletRequest req, HttpServletResponse resp) {// a
		// 파일 업로드 처리
		Notice_Board board = new Notice_Board();
		board.setCateno(1001);
		board.setBoardno(notice_BoardDao.selectNotice_Boardno());
		board.setHit(0);
		board.setRecomend(0);
		board.setUserid((String) req.getSession().getAttribute("userid"));

		try {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////////////

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			System.out.println("멀티파트 아님");
			return;
		}
		///////////////////////////////////////////////////////
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();

		// 메모리 처리 사이즈
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 디스크 처리 사이즈
		ServletContext context = req.getServletContext();
		File repository = new File(context.getRealPath("tmp"));
		factory.setRepository(repository);

		// 업로드 객체 생성
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);

		// 용량 제한 설정 : 10MB
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 파싱된 데이터 처리
		Iterator<FileItem> iter = items.iterator();
		////////////////////////////////////////////

		while (iter.hasNext()) { // while문 시작

			FileItem item = iter.next();// 요청정보 하나씩얻기

			// 빈 파일 처리
			if (item.getSize() <= 0)
				continue;
			if (item.isFormField()) {
				if ("title".equals(item.getFieldName())) {
					String data = item.getString();
					// 데이터 처리
					// System.out.println("타이틀 넣음");
					board.setTitle(data);

				} else if ("content".equals(item.getFieldName())) {
					String content = item.getString();
					board.setContent(content);
					notice_BoardDao.insertNoticeBoard(board);
					Notice_Filetb file = new Notice_Filetb();

					StringTokenizer str = new StringTokenizer(content, " =><\"", false);
//					int i = 0;
					while (str.hasMoreTokens()) {
						String data = str.nextToken();
						if (data.equals(" ")) {
						} else if (data.equals("=")) {
						} else if (data.equals(">")) {

						} else if (data.equals("<")) {

						} else if (data.equals("\"")) {

						} else {
							if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
									|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
									|| data.contains(".BMP") || data.contains(".bmp")) {

//								 System.out.println(i+","+data);
								// System.out.println("---------");
								if (data.contains("&#10")) {
									String data2 = data.substring(0, data.length() - 5);
									if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
											|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
											|| data.contains(".BMP") || data.contains(".bmp")) {
//										System.out.println(data2);
										file.setFile_SaveName(data2);
									}
									// JPG, GIF, PNG, BMP
								} else {
//									System.out.println(data);
									file.setFile_OriginName(data);
									System.out.println("1: " + file);
									if (file.getFile_SaveName() != null) {
										file.setBoardno(board.getBoardno());
										file.setFileno(notice_BoardFileDao.selectFileno());
										System.out.println("2 :" + file);
										notice_BoardFileDao.insertFiletb(file);
									}
								}

							}

						}
//						i++;

					}
				}

			} // endof isFormFiled()
			else { // 파일일 경우
					// 파일일 경우
				Notice_Filetb file = new Notice_Filetb();

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID();
				// System.out.println(uuid);

				String u = uuid.toString().split("-")[4];
				// System.out.println(u);
				// -----------------

				// 로컬 저장소 파일
				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);
				// System.out.println(up);
				// System.out.println("boardno 찾아주세요" + board.getBoardno());
				file.setBoardno(board.getBoardno());
				file.setFile_SaveName(up.getName());
				file.setFile_OriginName(item.getName());
				file.setFileno(notice_BoardFileDao.selectFileno());

				notice_BoardFileDao.insertFiletb(file);
				try {
					// 실제 업로드
					item.write(up);

					// 임시 파일 삭제
					item.delete();

				} catch (Exception e) {
					e.printStackTrace();
				}

			} // 파일

		} // endofwhile 종료

	}// a

	@Override
	public List<Notice_Filetb> viewFile(Notice_Board board) {

		return notice_BoardFileDao.selectFiletb(board);
	}

	@Override
	public void update(HttpServletRequest req) {
		Notice_Board board = null;
		Notice_Filetb boardFile = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			// 파일 첨부가 없을 경우
			board = new Notice_Board();

			board.setTitle(req.getParameter("title"));
			board.setUserid((String) req.getSession().getAttribute("userid"));
			board.setContent(req.getParameter("content"));

		} else {
			// 파일업로드를 사용하고 있을 경우
			board = new Notice_Board();

			// 디스크팩토리
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 메모리처리 사이즈
			factory.setSizeThreshold(1 * 1024 * 1024); // 1MB

			// 임시 저장소
			File repository = new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);

			// 업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 용량 제한 설정 : 10MB
			upload.setFileSizeMax(10 * 1024 * 1024);

			// form-data 추출
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(req);

			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			// 파싱된 데이터 처리 반복자
			Iterator<FileItem> iter = items.iterator();

			// 요청정보 처리
			while (iter.hasNext()) {
				FileItem item = iter.next();

				// 빈 파일 처리
				if (item.getSize() <= 0)
					continue;

				// 빈 파일이 아닐 경우
				if (item.isFormField()) {
					
					if ("boardno".equals(item.getFieldName())) {
						board.setBoardno(Integer.parseInt(item.getString()));
					}

					if ("title".equals(item.getFieldName())) {
						board.setTitle(item.getString());
					}
					///
					else if ("content".equals(item.getFieldName())) {
						String content = item.getString();
						board.setContent(content);
						notice_BoardDao.insertNoticeBoard(board);
						Notice_Filetb file = new Notice_Filetb();

						StringTokenizer str = new StringTokenizer(content, " =><\"", false);
//						int i = 0;
						while (str.hasMoreTokens()) {
							String data = str.nextToken();
							if (data.equals(" ")) {
							} else if (data.equals("=")) {
							} else if (data.equals(">")) {

							} else if (data.equals("<")) {

							} else if (data.equals("\"")) {

							} else {
								if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
										|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
										|| data.contains(".BMP") || data.contains(".bmp")) {

//									 System.out.println(i+","+data);
									// System.out.println("---------");
									if (data.contains("&#10")) {
										String data2 = data.substring(0, data.length() - 5);
										if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
												|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
												|| data.contains(".BMP") || data.contains(".bmp")) {
//											System.out.println(data2);
											file.setFile_SaveName(data2);
										}
										// JPG, GIF, PNG, BMP
									} else {
//										System.out.println(data);
										file.setFile_OriginName(data);
										//System.out.println("1: " + file);
										if (file.getFile_SaveName() != null) {
											file.setBoardno(board.getBoardno());
											file.setFileno(notice_BoardFileDao.selectFileno());
											System.out.println("2 :" + file);
											notice_BoardFileDao.insertFiletb(file);
										}
									}

								}

							}
//							i++;

						}
					}
//////
				} else {
					UUID uuid = UUID.randomUUID();
//					System.out.println(uuid);

					String u = uuid.toString().split("-")[4];
//					System.out.println(u);
					// -----------------

					// 로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(req.getServletContext().getRealPath("upload"), stored);

					boardFile = new Notice_Filetb();
					boardFile.setFile_OriginName(item.getName());
					boardFile.setFile_SaveName(stored);
					boardFile.setFileno(notice_BoardFileDao.selectFileno());

					try {
						// 실제 업로드
						item.write(up);

						// 임시 파일 삭제
						item.delete();

					} catch (Exception e) {
						e.printStackTrace();
					} // try end
				} // if end
			} // while end
		} // if(!isMultipart) end

//		System.out.println(board);
//		System.out.println(boardFile);

		if (board != null) {
			notice_BoardDao.updateNoticeBoard(board);
		}

		if (boardFile != null) {
			boardFile.setBoardno(board.getBoardno());
			notice_BoardFileDao.insertFiletb(boardFile);
		}

	}

	@Override
	public void delete(HttpServletRequest req, Notice_Board board) {

		String path = req.getServletContext().getRealPath("/");

		List<Notice_Filetb> list = notice_BoardFileDao.selectFiletb(board);
		// System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			File file = new File(path + list.get(i).getFile_SaveName());
			file.delete();

			// System.out.println("4");
			// System.out.println("삭제됨");
			// System.out.println(list.get(i).getFile_SaveName());
		}
		notice_BoardDao.deleteNoticeBoard(board);
		notice_BoardFileDao.deleteFiletbByboardno(board);
	}

	@Override
	public boolean checkWriter(HttpServletRequest req, Notice_Board board) {

		String loginId = (String) req.getSession().getAttribute("userid");

		board = notice_BoardDao.selectNoticeBoardByBoardNo(board);

		if (!loginId.equals(board.getUserid())) {
			return false;
		}

		return true;
	}

	@Override
	public String getNick(Notice_Board notice_Board) {

		return notice_BoardDao.selectNickByBoardno(notice_Board);
	}

	@Override
	public int deleteByfileno(int fileno, Notice_Board notice_Board) {

		return notice_BoardFileDao.deleteFiletbByfileno(fileno, notice_Board);
	}

	@Override
	public int updateFile(HttpServletRequest req) {

		int boardno = Integer.parseInt(req.getParameter("boardno"));
		int fileno = notice_BoardFileDao.selectFileno();

		// notice_BoardFileDao.insertFiletb(notice_filetb);

		return 0;
	}

}
