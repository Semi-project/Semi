package service.board.notice;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		notice_BoardDao.updateNoticeBoard(boardView);

		return notice_BoardDao.selectNoticeBoardByBoardNo(boardView);
	}

	@Override
	public void write(HttpServletRequest req) {
	//	String boardcate = req.getParameter("boardcate");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

	//	System.out.println(boardcate);
		System.out.println(title);
		System.out.println(content);
		Notice_Board board = new Notice_Board();
		
		board.setCateno(1001);
		board.setBoardno(notice_BoardDao.selectNotice_Boardno());
		board.setHit(0);
		board.setRecomend(0);
		board.setUserid((String)req.getSession().getAttribute("userid"));
		board.setTitle(title);
		board.setContent(content);
		notice_BoardDao.insertNoticeBoard(board);
		Notice_Filetb file = new Notice_Filetb();
		
		StringTokenizer str = new StringTokenizer(content, " =><\"", false);
//		int i = 0;
		while (str.hasMoreTokens()) {
			String data = str.nextToken();
			if (data.equals(" ")) {
			} else if (data.equals("=")) {
			} else if (data.equals(">")) {

			} else if (data.equals("<")) {

			} else if (data.equals("\"")) {

			} else {
				if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg") || data.contains(".JPG")
						|| data.contains(".GIF") || data.contains(".gif") || data.contains(".BMP")
						|| data.contains(".bmp")) {

//					 System.out.println(i+","+data);
					// System.out.println("---------");
					if (data.contains("&#10")) {
						String data2 = data.substring(0, data.length() - 5);
						if (data.contains(".png") || data.contains(".PNG") || data.contains(".jpg")
								|| data.contains(".JPG") || data.contains(".GIF") || data.contains(".gif")
								|| data.contains(".BMP") || data.contains(".bmp")) {
//							System.out.println(data2);
							file.setFile_SaveName(data2);
						}
						// JPG, GIF, PNG, BMP
					} else {
//						System.out.println(data);
						file.setFile_OriginName(data);
						if (file.getFile_SaveName() != null) {
							file.setBoardno(board.getBoardno());
							file.setFileno(notice_BoardFileDao.selectFileno());
							
							
							notice_BoardFileDao.insertFiletb(file);
						}
					}

				}

			}
//			i++;

		}

	}

	@Override
	public Notice_Filetb viewFile(Notice_Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Notice_Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkWriter(HttpServletRequest req, Notice_Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
