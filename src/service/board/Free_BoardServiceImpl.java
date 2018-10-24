package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardCateDao;
import dao.board.BoardCateDaoImpl;
import dao.board.Free_BoardDao;
import dao.board.Free_BoardDaoImpl;
import dao.board.RecommendDao;
import dao.board.RecommendDaoImpl;
import dao.file.Free_FileDao;
import dao.file.Free_FileDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.board.Free_Board;
import dto.comment.Free_Comments;
import dto.file.Free_Filetb;
import util.Paging;

public class Free_BoardServiceImpl implements Free_BoardService {

	private Free_BoardDao freeboardDao = new Free_BoardDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
	private BoardCateDao boardCateDao = new BoardCateDaoImpl();
	private RecommendDao recommentsDao = new RecommendDaoImpl();
	private Free_FileDao free_fileDao = new Free_FileDaoImpl();
	
	@Override
	public List<Free_Board> selectFreeboard() {
		
		return freeboardDao.selectFreeBoard();
	}
	@Override
	public void writeFreeboard(Free_Board freeBoard, Free_FileDao freeFileTb) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteFreeboard(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Free_Board updateFreeboard(Free_Board freeBoard) {
	
		return null;
		
	}
	@Override
	public String searchFreeBoardBytitle(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String searchFreeBoardBycontent(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String searchFreeBoardByuserid(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int selecntFreeBoardCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Free_Board> selectFreeBoardPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateRecommend(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Free_Board searchByComment_no(Free_Board freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Free_Board> selectByBoardno(Free_Board freeBoard, Free_Comments freeComments, Free_Filetb freeFileTb) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Free_Board getParam(HttpServletRequest req, HttpServletResponse resp) {
		Free_Board fb = new Free_Board();
		
		String boardno = req.getParameter("boardno");
		//null이나 ""이 아니면 int로 변환하여 DTO에 저장
				if( boardno != null && !"".equals(boardno) ) {
					fb.setBoardno(Integer.parseInt(boardno));
				}
	
		return fb;
	}
	@Override
	public Free_Board view(Free_Board freeBoard) {
	freeboardDao.updateHit(freeBoard);
		
		return freeboardDao.viewFreeBoard(freeBoard);
		
	}


}
