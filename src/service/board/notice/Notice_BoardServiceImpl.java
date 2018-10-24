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

import dao.board.notice.Notice_BoardDao;
import dao.board.notice.Notice_BoardDaoImpl;

import dao.board.review.BoardCateDao;
import dao.board.review.BoardCateDaoImpl;
import dao.comment.notice.Notice_CommentDao;
import dao.comment.notice.Notice_CommentDaoImpl;

import dao.file.notice.Notice_FileDao;
import dao.file.notice.Notice_FileDaoImpl;
import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.Paging;

public class Notice_BoardServiceImpl implements Notice_BoardService {

	private Notice_CommentDao notice_CommentDao = new Notice_CommentDaoImpl();
	private Notice_FileDao notice_fileDao = new Notice_FileDaoImpl();
	private Notice_BoardDao notice_boardDao = new Notice_BoardDaoImpl();
	private BoardCateDao board_cateDao = new BoardCateDaoImpl();

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
	public void writeNotice_Board(HttpServletRequest req, HttpServletResponse resp) {

		Notice_Board notice_board = null;
		Notice_Filetb notice_Filetb = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if(!isMultipart) {
			//파일 첨부가 없을 경우
			notice_board = new Notice_Board();

			notice_board.setTitle(req.getParameter("title"));
			notice_board.setUserid((String) req.getSession().getAttribute("userid"));
			notice_board.setContent(req.getParameter("content"));

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


			//업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);

			//용량 제한 설정 : 10MB
			upload.setFileSizeMax(10 * 1024 * 1024);

			//form-data 추출 
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(req);

			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			//파싱된 데이터 처리 반복자
			Iterator<FileItem> iter = items.iterator();

			//요청정보 처리
			while( iter.hasNext() ) {
				FileItem item = iter.next();

				// 빈 파일 처리
				if( item.getSize() <= 0 )   continue;

				// 빈 파일이 아닐 경우
				if (item.isFormField()) {
					
					if ("boardno".equals(item.getFieldName())) {
						board.setBoardno(Integer.parseInt(item.getString()));
					}

					if ("title".equals(item.getFieldName())) {
						board.setTitle(item.getString());
					}

					notice_board.setUserid((String) req.getSession().getAttribute("writer"));

				} else {
					UUID uuid = UUID.randomUUID();
					//               System.out.println(uuid);

					String u = uuid.toString().split("-")[4];
					//               System.out.println(u);
					// -----------------

					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
							req.getServletContext().getRealPath("upload")
							, stored);

					notice_Filetb = new Notice_Filetb();
					notice_Filetb.setFile_OriginName(item.getName());
					notice_Filetb.setFile_SaveName(stored);
					// notice_Filetb.setFilesize(item.getSize());

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
	}

	//      int boardno = boardDao.selectBoardno();
	//      
	//      if(board != null) {
	//         board.setBoardno(boardno);
	//         boardDao.insert(board);
	//      }
	//      
	//      if(boardFile != null) {
	//         boardFile.setBoardno(boardno);
	//         boardFileDao.insertFile(boardFile);
	//      }
	//   }

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

	@Override
	public Notice_Board getParam(HttpServletRequest req, HttpServletResponse resp) {
		Notice_Board Notice_Board = new Notice_Board();
		String boardno = req.getParameter("boardno");
		if( boardno != null && !"".equals(boardno) ) {
			Notice_Board.setBoardno(Integer.parseInt(boardno));
		}

		return Notice_Board;

	}

	@Override
	public int updateFile(HttpServletRequest req) {

		int boardno = Integer.parseInt(req.getParameter("boardno"));
		int fileno = notice_BoardFileDao.selectFileno();

		// notice_BoardFileDao.insertFiletb(notice_filetb);

		return 0;
	}

	@Override
	public void writeNotice_Board(Notice_Board Notice_Board, Notice_Filetb noticeFile) {
		// TODO Auto-generated method stub

	}
}
