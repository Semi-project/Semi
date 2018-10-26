package service.board.qna;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.board.cate.BoardCateDao;
import dao.board.cate.BoardCateDaoImpl;
import dao.board.qna.QnADao;
import dao.board.qna.QnADaoImpl;
import dao.comment.qna.QnA_CommentDao;
import dao.comment.qna.QnA_CommentDaoImpl;
import dao.file.qna.QnA_FileDao;
import dao.file.qna.QnA_FileDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.board.QnA;
import dto.file.QnA_Filetb;
import util.Paging;

public class QnAServiceImpl implements QnAService {

	private MemberDao memberDao = new MemberDaoImpl();
	private BoardCateDao boardCateDao = new BoardCateDaoImpl();
	private QnA_CommentDao qna_CommentDao = new QnA_CommentDaoImpl();
	private QnA_FileDao qna_fileDao = new QnA_FileDaoImpl();
	private QnADao qnaDao = new QnADaoImpl();

	@Override
	public List<QnA> selectQnA() {

		return qnaDao.selectQnA();
	}

	@Override
	public void writeQnA(HttpServletRequest req) throws Exception {
		
		QnA_Filetb qna_Filetb = null;
		QnA qna = null;
	
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) {
		
	 qna = new QnA();
		
		qna.setCateno(1002);
		qna.setUserid((String)req.getSession().getAttribute("userid"));
		qna.setTitle(req.getParameter("title"));
		qna.setContent(req.getParameter("content"));

		} else {
			
		 qna = new QnA();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// �޸�ó�� ������
			factory.setSizeThreshold(1 * 1024 * 1024); // 1MB

			// �ӽ� �����
			File repository = new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);

			// ���ε� ��ü ����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �뷮 ���� ���� : 10MB
			upload.setFileSizeMax(10 * 1024 * 1024);

			// form-data ����
			List<FileItem> items = null;

			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator<FileItem> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.getSize() <= 0)
					continue;

				if (item.isFormField()) {
					if ("title".equals(item.getFieldName())) {
						qna.setTitle(item.getString());
					}
					if ("content".equals(item.getFieldName())) {
						qna.setContent(item.getString());
					}
					qna.setUserid((String) req.getSession().getAttribute("userid"));
				} else {
					UUID uuid = UUID.randomUUID();

					String u = uuid.toString().split("-")[4];

					String save = item.getName() + "_" + u;
					File up = new File(req.getServletContext().getRealPath("upload"), save);

					qna_Filetb = new QnA_Filetb();
					qna_Filetb.setOriginName(item.getName());
					qna_Filetb.setStoredName(save);
					qna_Filetb.setFilesize(item.getSize());

					try {
						item.write(up);

						item.delete();
					} catch (Exception e) {
						e.printStackTrace();
		

					
					}

		
		 }	

	}
}
		int boardno = qnaDao.selectBoardno();			

		if (qna != null) {
			qna.setBoardno(boardno);
			qnaDao.insertQnA(qna);
	 }
		if (qna_Filetb != null) {
			qna_Filetb.setBoardno(boardno);
			qna_fileDao.insertFile(qna_Filetb);
   }
}

	@Override
	public int deleteQnA(QnA qna) throws Exception{
		int result = 0; 
		result = qna_fileDao.delete(qna);
		result = qnaDao.deleteQnA(qna);
		return result;

	}

	@Override
	public void updateQnA(HttpServletRequest req , QnA qna) throws Exception{
	
		
		QnA_Filetb qna_Filetb = null;
		
		if (qna != null) {
			qnaDao.updateQnA(qna);

		}
	

		//boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		//if (!isMultipart) {

			qna = new QnA();

			qna.setTitle(req.getParameter("title"));
			qna.setUserid((String) req.getSession().getAttribute("userid"));
			qna.setContent(req.getParameter("content"));

		//} else {
//
//			qna = new QnA();
//
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//
//			factory.setSizeThreshold(1 * 1024 * 1024);
//
//			// �ӽ� �����
//			File repository = new File(req.getServletContext().getRealPath("tmp"));
//			factory.setRepository(repository);
//
//			// ���ε� ��ü ����
//			ServletFileUpload upload = new ServletFileUpload(factory);
//
//			// �뷮 ���� ���� : 10MB
//			upload.setFileSizeMax(10 * 1024 * 1024);
//
//			// form-data ����
//			List<FileItem> items = null;
//
//			try {
//				items = upload.parseRequest(req);
//			} catch (FileUploadException e) {
//				e.printStackTrace();
//			}
//			Iterator<FileItem> iter = items.iterator();
//
//			while (iter.hasNext()) {
//				FileItem item = iter.next();
//
//				if (item.getSize() <= 0)
//					continue;
//
//				if (item.isFormField()) {
//					if ("boardno".equals(item.getFieldName())) {
//						qna.setBoardno(Integer.parseInt(item.getString()));
//					}
//					if ("title".equals(item.getFieldName())) {
//						qna.setTitle(item.getString());
//					}
//					if ("content".equals(item.getFieldName())) {
//						qna.setContent(item.getString());
//
//					}
//					qna.setUserid((String) req.getSession().getAttribute("userid"));
//
//				} else {
//					UUID uuid = UUID.randomUUID();
//
//					String u = uuid.toString().split("-")[4];
//
//					String save = item.getName() + "_" + u;
//					File up = new File(req.getServletContext().getRealPath("upload"), save);
//
//					qna_Filetb = new QnA_Filetb();
//					qna_Filetb.setOriginName(item.getName());
//					qna_Filetb.setStoredName(save);
//					/*qna_Filetb.setFilesize(item.getSize());*/
//
//					try {
//						item.write(up);
//
//						item.delete();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
//
//			}
//		}
//


//		if (qna_Filetb != null) {
//			qna_Filetb.setBoardno(qna.getBoardno());
//			qna_fileDao.insertFile(qna_Filetb);
//	}

	}

	

	

	@Override
	public List getQnAPagingList(Paging paging, String search, String searchVal) {

		return qnaDao.selectQnAPagingList(paging, search, searchVal);
	}

	@Override
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp) {
		// ��û�Ķ���� ������ ������ DTO��ü
		QnA qna = new QnA();
		qna.setCateno(1002);
		qna.setUserid(req.getParameter("userid"));
		qna.setBoardno(Integer.parseInt(req.getParameter("boardno")));
		qna.setTitle(req.getParameter("title"));
		qna.setContent(req.getParameter("content"));

		// ��û�Ķ���Ͱ� ��ü�� ��ȯ�� DTO ��
		return qna;

	}

	@Override
	public QnA viewQnA(int boardNo) {
		return qnaDao.selectQnAByBoardno(boardNo);
	}

	@Override
	public int getCurPage(HttpServletRequest req) {

		//요청파라미터 받기
				String curPage = req.getParameter("curPage");
				
				//null이나 ""이 아니면 int로 리턴
				if( curPage != null && !"".equals(curPage) ) {
					return Integer.parseInt( curPage );
				}

				//null이나 "" 면 0으로 반환
				return 0;
	}

	@Override
	public int getTotalCount(String searchVal, String search) {
		
		return qnaDao.selectQnACntAll(searchVal,search);
	}

}
