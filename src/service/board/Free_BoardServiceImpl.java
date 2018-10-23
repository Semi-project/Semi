package service.board;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
	public void writeFreeboard(HttpServletRequest req) {
		//		//게시글 번호 얻어오기
		//		int boardno = boardDao.selectBoardno();
		//		
		//		//게시글 삽입
		//		board.setBoardno(boardno);
		//		boardDao.insert(board);
		//
		//		//첨부파일이 존재할 때만 파일 삽입
		//		if(file != null) {
		//			file.setBoardno(boardno);
		//			boardFileDao.insert(file);
		//		}

		Free_Board freeboard=null;
		Free_Filetb freefiletb=null;

		boolean isMultipart= ServletFileUpload.isMultipartContent(req);

		if(!isMultipart) {
			//파일 첨부가 없을 경우 
			freeboard = new Free_Board();

			freeboard.setTitle(req.getParameter("title"));
			freeboard.setUserid((String)req.getSession().getAttribute("userid"));
			freeboard.setContent(req.getParameter("content"));
			System.out.println("1");
		}else {
			//파일 업로드를 사용하고 있을 경우 
			freeboard = new Free_Board();

			//디스크 팩토리 
			DiskFileItemFactory factory=new DiskFileItemFactory();

			//메모리처리 사이즈 
			factory.setSizeThreshold(1 * 1024 * 1024); //10mb

			File repository=new File(req.getServletContext().getRealPath("tmp"));
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
							freeboard.setTitle(item.getString("UTF-8"));
						}
						if("content".equals(item.getFieldName())) {
							freeboard.setContent(item.getString("UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {

						e.printStackTrace();
					}

					freeboard.setUserid((String) req.getSession().getAttribute("userid"));
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

					freefiletb = new Free_Filetb();
					freefiletb.setFile_OriginName(item.getName());
					freefiletb.setFile_SaveName(stored);
					freefiletb.setFilesize((int) item.getSize());
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

		int boardno= freeboardDao.selectFreeboardno();

		if(freeboard!=null) {
			freeboard.setBoardno(boardno);
			freeboardDao.insertFreeBoard(freeboard);
		}
		if(freefiletb !=null) {
			freefiletb.setBoardno(boardno);
			freeboardDao.insertFreeBoard(freeboard);
		}

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
