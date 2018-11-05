package service.board.free;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.board.free.Free_BoardDao;
import dao.board.free.Free_BoardDaoImpl;
import dao.board.recommend.RecommendDao;
import dao.board.recommend.RecommendDaoImpl;
import dao.comment.free.Free_CommentDao;
import dao.comment.free.Free_CommentDaoImpl;
import dao.file.free.Free_FileDao;
import dao.file.free.Free_FileDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.board.Free_Board;
import dto.board.Free_Board_param;
import dto.comment.Free_Comments;
import dto.file.Free_Filetb;

public class Free_BoardServiceImpl implements Free_BoardService {

	private Free_BoardDao freeboardDao = new Free_BoardDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
	private RecommendDao recommentsDao = new RecommendDaoImpl();
	private Free_FileDao free_fileDao = new Free_FileDaoImpl();
	private Free_CommentDao freecommentdao = new Free_CommentDaoImpl();
	@Override
	public List<Free_Board> selectFreeboard(Free_Board_param fbp) {

		return freeboardDao.selectFreeBoard(fbp);
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
			//			System.out.println("1");
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
					freefiletb.setFilesize(item.getSize());
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
			free_fileDao.insertFile(freefiletb);
		}

	}
	@Override
	public void deleteFreeboard(Free_Board freeBoard) {
		free_fileDao.delete(freeBoard);
		freeboardDao.deleteFreeBoard(freeBoard);

	}
	@Override
	public void updateFreeboard(HttpServletRequest req) {
		System.out.println("서비스업데이트"+req);
		Free_Board freeboard = null;
		Free_Filetb freefiletb = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if(!isMultipart) {
			//파일 첨부가 없을 경우
			freeboard = new Free_Board();

			freeboard.setTitle(req.getParameter("title"));
			freeboard.setUserid((String) req.getSession().getAttribute("userid"));
			freeboard.setContent(req.getParameter("content"));

		} else {
			//파일업로드를 사용하고 있을 경우
			freeboard = new Free_Board();

			//디스크팩토리
			DiskFileItemFactory factory = new DiskFileItemFactory();

			//메모리처리 사이즈
			factory.setSizeThreshold(1 * 1024 * 1024); //1MB

			//임시 저장소
			File repository=new File(req.getServletContext().getRealPath("tmp"));
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
				if( item.getSize() <= 0 )	continue;

				// 빈 파일이 아닐 경우
				if(item.isFormField()) {
					try {
						if( "boardno".equals( item.getFieldName() ) ) {
							freeboard.setBoardno( Integer.parseInt(item.getString()) );
						}

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

					freefiletb = new Free_Filetb();
					freefiletb.setFile_OriginName(item.getName());
					freefiletb.setFile_SaveName(stored);
					freefiletb.setFilesize(item.getSize());

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



		if(freeboard!=null) {

			freeboardDao.updateFreeBoard(freeboard);
		}
		if(freefiletb !=null) {
			freefiletb.setBoardno(freeboard.getBoardno());
			free_fileDao.insertFile(freefiletb);
		}


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

		return freeboardDao.selecntFreeBoardCntAll();
	
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
	@Override
	public Free_Filetb viewFile(Free_Board freeboard) {

		return  free_fileDao.selectFile(freeboard);
	}




	@Override
	public Free_Board_param getParampage(HttpServletRequest req, HttpServletResponse resp) {

		Free_Board_param fbp= new Free_Board_param();

		String pageNumber = req.getParameter("pageNumber");
		//System.out.println(pageNum);
		//null이나 ""이 아니면 int로 변환하여 DTO에 저장
		if( pageNumber != null && !"".equals(pageNumber) ) {
			fbp.setPageNumber(Integer.parseInt(pageNumber));
		}

		return fbp;
	}




	@Override
	public String getUserid(Free_Board freeboard) {

		return freeboardDao.selectUseridByBoardno(freeboard);
	}
	@Override
	public int getCurPage(HttpServletRequest req) {
	return 0;
	}
	
	
	
	
	@Override
	public String nameSearch(HttpServletRequest req) {
		//요청파라미터 받기
		String namesearch = req.getParameter("search");
	System.out.println(namesearch);
		return namesearch;
	}
	
	
	//추천
	
	
	@Override
	public boolean recommendCheck(Free_Board freeboard) {
		System.out.println(freeboard);
		if( recommentsDao.selectCountRecommend(freeboard) > 0 ) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean recommend(Free_Board board) {
		if( recommendCheck(board) ) {
			recommentsDao.deleteRecommend(board);
			return false;
		} else {
			recommentsDao.insertRecommend(board);
			return true;
		}
		
	}
	
	@Override
	public int getRecommend(Free_Board freeboard) {
		return recommentsDao.selectTotalRecommend(freeboard);
	
	}
	

	



}
