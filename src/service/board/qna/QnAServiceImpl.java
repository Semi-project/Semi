package service.board.qna;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
import dto.board.Notice_Board;
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
		System.out.println("첨부파일 서비스"+req);



		QnA_Filetb qna_Filetb = null;
		QnA qna = null;


		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if(!isMultipart) {

			qna = new QnA();

			qna.setCateno(1002);
			qna.setUserid((String)req.getSession().getAttribute("userid"));
			qna.setTitle(req.getParameter("title"));
			qna.setContent(req.getParameter("content"));
			System.out.println("첨부파일 서비스2+"+qna);
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
					try {
						if ("title".equals(item.getFieldName())) {
							qna.setTitle(item.getString("UTF-8"));
						}
						if ("content".equals(item.getFieldName())) {
							qna.setContent(item.getString("UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


					qna.setUserid((String) req.getSession().getAttribute("userid"));
				} else {
					UUID uuid = UUID.randomUUID();

					String u = uuid.toString().split("-")[4];
					//-----------------------------
					String save = item.getName() + "_" + u;
					File up = new File(req.getServletContext().getRealPath("upload"), save);

					qna_Filetb = new QnA_Filetb();
					System.out.println("qna파일 작동여부:"+qna_Filetb);
					qna_Filetb.setFile_originname(item.getName());
					qna_Filetb.setFile_savename(save);
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
		System.out.println("첨부파일확인1:"+boardno);

		if (qna != null) {
			qna.setBoardno(boardno);
			qnaDao.insertQnA(qna);
			System.out.println("첨부파일확인2:"+qna);
		}
		System.out.println("qna파일 작동여부1:"+qna_Filetb);
		if (qna_Filetb != null) {
			qna_Filetb.setBoardno(boardno);
			System.out.println("첨부파일확인3.5:"+qna_Filetb);
			qna_fileDao.insertFile(qna_Filetb);
			System.out.println("첨부파일확인3:"+qna_Filetb);
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


		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {

			qna = new QnA();

			qna.setTitle(req.getParameter("title"));
			qna.setUserid((String) req.getSession().getAttribute("userid"));
			qna.setContent(req.getParameter("content"));

		} else {

			qna = new QnA();

			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(1 * 1024 * 1024);

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
					if ("boardno".equals(item.getFieldName())) {
						qna.setBoardno(Integer.parseInt(item.getString()));
					}
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
					qna_Filetb.setFile_originname(item.getName());
					qna_Filetb.setFile_savename(save);
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



		if (qna_Filetb != null) {
			qna_Filetb.setBoardno(qna.getBoardno());
			qna_fileDao.insertFile(qna_Filetb);
		}

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
		String boardno = req.getParameter("boardno");
		System.out.println("겟파람 보드값"+boardno);
		qna.setTitle(req.getParameter("title"));
		qna.setContent(req.getParameter("content"));

		if( boardno != null && !"".equals(boardno) ) {
			qna.setBoardno(Integer.parseInt(boardno));
		}

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

	@Override
	public QnA_Filetb viewFile(QnA qna){

		return  qna_fileDao.selectFile(qna);

	}

	@Override
	public boolean checkWriter(HttpServletRequest req, QnA qna) {
		System.out.println("qan 처음 확인 "+qna);
		String loginId = (String) req.getSession().getAttribute("userid");
		System.out.println(loginId);
		qna = qnaDao.selectqnaBoardByBoardNo(qna);

		if (!loginId.equals(qna.getUserid())) {
			System.out.println("qna확인"+qna);
			return false;
		}

		return true;
	}

	@Override
	public QnA getParam2(HttpServletRequest req, HttpServletResponse resp) {

		QnA qna = new QnA();

		String boardno = req.getParameter("boardno");
		// null이나 ""이 아니면 int로 변환하여 DTO에 저장
		if (boardno != null && !"".equals(boardno)) {
			qna.setBoardno(Integer.parseInt(boardno));
		}

		// 요청파라미터가 객체로 변환된 DTO 반환
		return qna;
	}
}


