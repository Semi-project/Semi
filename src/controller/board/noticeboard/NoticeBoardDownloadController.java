package controller.board.noticeboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.notice.Notice_BoardDao;
import dao.board.notice.Notice_BoardDaoImpl;
import dao.file.notice.Notice_FileDao;
import dao.file.notice.Notice_FileDaoImpl;
import dto.file.Notice_Filetb;

@WebServlet("/noticeboardfile/download")
public class NoticeBoardDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Notice_BoardDao notice_boardDao = new Notice_BoardDaoImpl();
	private Notice_FileDao notice_fileDao = new Notice_FileDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String param = req.getParameter("fileno");
	
		int notice_boardfileno = 0;
		if( param!=null && !"".equals(param) ) {
			notice_boardfileno = Integer.parseInt(param);
		}
		
		// 다운로드 대상 파일 정보 찾기;
		Notice_Filetb notice_filetb = notice_fileDao.selectByFileno(notice_boardfileno);
		
		//다운로드 파일 찾기
		String path = getServletContext().getRealPath("upload");
		File file = new File(
		path,notice_filetb.getFile_SaveName());
		
//		System.out.println(file);
		
		// 파일이 존재할 때만 동작
		if( file.exists() && file.isFile() ) {
			System.out.println("진행중"+notice_boardfileno);
			
			//응답 정보 설정 ( Response 메시지 header 수정 )
			
			//응답 본문 길이
			resp.setHeader(
				"Content-Length", String.valueOf(file.length()));
			
			//응답 파일 저장 위치 지정
			resp.setHeader(
				"Content-Disposition", 
				"attachment;fileName=" + new String(notice_filetb.getFile_OriginName().getBytes("UTF-8"), "8859_1" ) + ";" );
			
			// text/html;charset=utf-8
			//다운 받는 내용을 바이너리파일로 인식시키기
			resp.setContentType("application/octet-stream");
			
			
			//파일 입력 스트림 (서버 로컬 저장소(하드디스크))
			InputStream is = new FileInputStream(file);
			
			//파일 출력 스트림 (브라우저)
			OutputStream os = resp.getOutputStream();
			
			byte[] buf = new byte[4096];
			int len = -1;
			
		while( (len=is.read(buf)) != -1 ) {
				os.write(buf, 0, len);
			}
			
		os.flush();
		is.close();		
		os.close();
			
		}
}
}
