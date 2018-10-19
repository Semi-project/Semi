package service.board;

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

import dao.board.BoardCateDao;
import dao.board.BoardCateDaoImpl;
import dao.board.QnADao;
import dao.board.QnADaoImpl;
import dao.comment.QnA_CommentDao;
import dao.comment.QnA_CommentDaoImpl;
import dao.file.QnA_FileDao;
import dao.file.QnA_FileDaoImpl;
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
	public void writeQnA(HttpServletRequest req) {
		QnA qna = null;
		QnA_Filetb qna_Filetb = null;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {

			// 파일 첨부가 없을 경우
			qna = new QnA();

			qna.setTitle(req.getParameter("title"));
			qna.setUserid((String) req.getSession().getAttribute("userid"));
			qna.setContent(req.getParameter("content"));
		} else {

			qna = new QnA();

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
					qna_Filetb.setFile_OriginName(item.getName());
					qna_Filetb.setFile_SaveName(save);
				//	qna_Filetb.setFilesize(item.getSize());

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
//			qna.setBoardno(boardno);
			qnaDao.insertQnA(qna);
		}
		if (qna_Filetb != null) {
			qna_Filetb.setBoardno(boardno);
			qna_fileDao.insertFiletb(qna_Filetb);
		}

	}

	@Override
	public void deleteQnA(QnA qna) {
		qnaDao.deleteQnA(qna);

	}

	@Override
	public void updateQnA(HttpServletRequest req) {
		QnA qna = null;
		QnA_Filetb qna_Filetb = new QnA_Filetb();

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
					qna_Filetb.setFile_OriginName(item.getName());
					qna_Filetb.setFile_SaveName(save);
					//qna_Filetb.setFilesize(item.getSize());

					try {
						item.write(up);

						item.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
		if (qna != null) {
			qnaDao.updateQnA(qna);

		}
		if (qna_Filetb != null) {
			qna_Filetb.setBoardno(qna.getBoardno());
			qna_fileDao.insertFiletb(qna_Filetb);
		}

	}

	@Override
	public QnA searchQnABytitle(QnA qna) {

		return qnaDao.selectQnABytitle(qna);
	}

	@Override
	public QnA searchQnABycontent(QnA qna) {

		return qnaDao.selectQnABycontent(qna);
	}

	@Override
	public QnA searchQnAByuserid(QnA qna) {

		return qnaDao.selectByuserid(qna);
	}

	@Override
	public int selecntQnACntAll() {
		return qnaDao.selectQnACntAll();
	}

	@Override
	public List getQnAPagingList(Paging paging) {

		return qnaDao.selectQnAPagingList(paging);
	}

	@Override
	public QnA getParam(HttpServletRequest req, HttpServletResponse resp) {
		// 요청파라미터 정보를 저장할 DTO객체
		QnA qna = new QnA();

		qna.setUserid(req.getParameter("userid"));
		qna.setContent(req.getParameter("content"));

		// 요청파라미터가 객체로 변환된 DTO 반환
		return qna;

	}

	@Override
	public QnA viewQnA(QnA qnaView) {
		return qnaDao.selectQnAByBoardno(qnaView);
	}

	@Override
	public int getCurPage(HttpServletRequest req) {

		String curPage = req.getParameter("curPage");

		if (curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}

		return 0;
	}

}
