package service.board.notice;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.board.notice.Notice_BoardDao;
import dao.board.notice.Notice_BoardDaoImpl;
import dao.file.notice.Notice_FileDao;
import dao.file.notice.Notice_FileDaoImpl;
import dto.board.Notice_Board;
import dto.file.Free_Filetb;
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
//글쓰기---------------------------------------------------------------------------------
	@Override
	public void write(HttpServletRequest req) {// a
		Notice_Board noticeboard=null;
		Notice_Filetb noticefiletb=null;

		boolean isMultipart= ServletFileUpload.isMultipartContent(req);

		if(!isMultipart) {
			//파일 첨부가 없을 경우 
			noticeboard = new Notice_Board();

			noticeboard.setTitle(req.getParameter("title"));
			noticeboard.setUserid((String)req.getSession().getAttribute("userid"));
			noticeboard.setContent(req.getParameter("content"));
			
		}else {
			//파일 업로드를 사용하고 있을 경우 
			noticeboard = new Notice_Board();

			//디스크 팩토리 
			DiskFileItemFactory factory=new DiskFileItemFactory();

			//메모리처리 사이즈 
			factory.setSizeThreshold(1 * 1024 * 1024); //10mb

			File repository=new File(req.getServletContext().getRealPath("tmp0"));
			factory.setRepository(repository);

			//업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);

			//용량 제한 설정 
			upload.setFileSizeMax(50 * 1024 * 1024);

			//form-data 추출
			List<FileItem> items=null;

			try {
				items = upload.parseRequest(req);

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator<FileItem> iter=items.iterator();

			//요청정보 차리 
			while(iter.hasNext()) {
				FileItem item = iter.next();
				//빈 파일 처리 
				if(item.getSize() <=0) continue;

				//빈 파일이 아닐 경우 
				if(item.isFormField()) {
					try {
						if("title".equals(item.getFieldName())) {
							noticeboard.setTitle(item.getString("UTF-8"));
						}
						if("content".equals(item.getFieldName())) {
							noticeboard.setContent(item.getString("UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {

						e.printStackTrace();
					}

					noticeboard.setUserid((String) req.getSession().getAttribute("userid"));
				}else {
					UUID uuid = UUID.randomUUID();
					System.out.println(uuid);

					String u =uuid.toString().split("-")[4];
					System.out.println(u);
					//-------------------

					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
							req.getServletContext().getRealPath("upload"),stored);

					noticefiletb = new Notice_Filetb();
					noticefiletb.setFile_OriginName(item.getName());
					noticefiletb.setFile_SaveName(stored);
					noticefiletb.setFilesize(item.getSize());
					try {
						// 실제 업로드
						item.write(up);

						// 임시 파일 삭제
						item.delete();

					} catch (Exception e) {
						e.printStackTrace();
					} // try end
				} //if end
			} //while end
		} //if(!isMultipart) end

		int boardno= notice_BoardDao.selectNotice_Boardno();

		if(noticeboard!=null) {
			noticeboard.setBoardno(boardno);
			notice_BoardDao.insertNoticeBoard(noticeboard);
		}
		if(noticefiletb !=null) {
			noticefiletb.setBoardno(boardno);
			notice_BoardFileDao.insertFiletb(noticefiletb);
		}

	}
//-------------------------------------------------------------------------
	@Override
	public Notice_Filetb viewFile(Notice_Board noticeboard) {
		
		return notice_BoardFileDao.selectFiletb(noticeboard);
	}
//글 수정----------------------------------------------------------------------
	@Override
	public void update(HttpServletRequest req) {
		Notice_Board notice_board = null;
		Notice_Filetb notice_Filetb = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {
			// 파일 첨부가 없을 경우
			notice_board = new Notice_Board();

			notice_board.setTitle(req.getParameter("title"));
			notice_board.setUserid((String) req.getSession().getAttribute("userid"));
			notice_board.setContent(req.getParameter("content"));

		} else {
			// 파일업로드를 사용하고 있을 경우
			notice_board = new Notice_Board();

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
				if(item.isFormField()) {
					try {
						if( "boardno".equals( item.getFieldName() ) ) {
							notice_board.setBoardno( Integer.parseInt(item.getString()) );
						}

						if("title".equals(item.getFieldName())) {
							notice_board.setTitle(item.getString("UTF-8"));
						}
						if("content".equals(item.getFieldName())) {
							notice_board.setContent(item.getString("UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {

						e.printStackTrace();
					}

					notice_board.setUserid((String) req.getSession().getAttribute("userid"));

				} else {
					UUID uuid = UUID.randomUUID();
					//					System.out.println(uuid);

					String u = uuid.toString().split("-")[4];
					//					System.out.println(u);
					// -----------------

					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
							req.getServletContext().getRealPath("upload")
							, stored);

					notice_Filetb = new Notice_Filetb();
					notice_Filetb.setFile_OriginName(item.getName());
					notice_Filetb.setFile_SaveName(stored);
					notice_Filetb.setFilesize(item.getSize());

					try {
						// 실제 업로드
						item.write(up);

						// 임시 파일 삭제
						item.delete();

					} catch (Exception e) {
						e.printStackTrace();
					} // try end
				} //if end
			} //while end
		} //if(!isMultipart) end



		if(notice_board!=null) {

			notice_BoardDao.updateNoticeBoard(notice_board);
		}
		if(notice_Filetb !=null) {
			notice_Filetb.setBoardno(notice_board.getBoardno());
			notice_BoardDao.insertFile(notice_Filetb);
		}


	}
//글삭제-------------------------------------------------------------------------------
	@Override
	public void delete(Notice_Board notice_board) {

		notice_BoardDao.deleteNoticeBoard(notice_board);
		notice_BoardFileDao.deleteFiletbByboardno(notice_board);
	}
//-------------------------------------------------------------------------------
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
