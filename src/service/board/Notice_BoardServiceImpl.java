package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardCateDao;
import dao.board.BoardCateDaoImpl;
import dao.board.Notice_BoardDao;
import dao.board.Notice_BoardDaoImpl;
import dao.comment.Notice_CommentDao;
import dao.comment.Notice_CommentDaoImpl;
import dao.file.Notice_FileDao;
import dao.file.Notice_FileDaoImpl;
import dto.board.Notice_Board;
import dto.file.Notice_Filetb;
import util.Paging;

public class Notice_BoardServiceImpl implements Notice_BoardService {

	private Notice_CommentDao notice_CommentDao = new Notice_CommentDaoImpl();
	private Notice_FileDao notice_fileDao = new Notice_FileDaoImpl();
	private Notice_BoardDao notice_boardDao = new Notice_BoardDaoImpl();
	private BoardCateDao board_cateDao = new BoardCateDaoImpl();

	@Override
	public List<Notice_Board> selectNotice_Board() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeNotice_Board(Notice_Board Notice_Board, Notice_Filetb noticeFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNotice_Board(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNotice_Board(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub

	}

	@Override
	public String searchNotice_BoardBytitle(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchNotice_BoardBycontent(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchNotice_BoardByuserid(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selecntNotice_BoardCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notice_Board> selectNotice_BoardPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecommend(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub

	}

	@Override
	public Notice_Board searchByComment_no(Notice_Board Notice_Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice_Board getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
