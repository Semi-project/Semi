package controller.board.reviewboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class NoticeBoardWriteUpdate
 */
@WebServlet("/review/file/write")
public class ReviewBoardWriteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 업로드 처리
		System.out.println("통신중");
		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");

		// 응답 객체 MIME타입(Content-Type) 설정
		resp.setContentType("text/html;charset=UTF-8");

		// 응답 객체 출력 스트림
		PrintWriter out = resp.getWriter();

		// ----- 파일 업로드 처리 시작 -----

		// 1. 파일업로드 형태가 맞는지 확인
		// enctype이 multipart/form-data인지 확인

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data가 아닐 경우 처리
		if (!isMultipart) {
			out.println("<h1>encType이 multipart/form-data가 아님</h1>");
			System.out.println("해당 데이터가 아닙니다");
			return;
		}

		// 1-2. multipart/form-data로 요청이 이루어졌을 경우
		// 파일이 전송된 것으로 판단한다

		// 2.업로드된 파일을 처리하는 아이템팩토리 객체 생성
		// DiskFileItemFactory
		// 디스크기반의 파일아이템을 처리하는 API
		// 업로드파일을 디스크에 임시저장하고 후처리한다
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();
		//System.out.println("찍히나");
		// 3. 업로드아이템의 용량이 적당히 작으면 메모리로 처리
		int maxMem = 1 * 1024 * 1024; // 1MB
		factory.setSizeThreshold(maxMem);

		// 4. 용량이 적당히 크면 임시파일 만들어서 처리(디스크)
		ServletContext context = getServletContext();
		File repository = new File(context.getRealPath("tmp"));
//		System.out.println(repository);
		factory.setRepository(repository);

		// 5. 업로드 허용기준을 넘지 않으면 파일업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB

		// 업로드 객체 생성
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);

		// 용량 제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);

		// ----- 업로드 준비 완료 -----

		// 6. 업로드 데이터 파싱(추출) - 임시파일 업로드 수행
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 7. 파싱된 데이터 처리
		Iterator<FileItem> iter = items.iterator();

		// 요청정보 전부 처리하기
		while (iter.hasNext()) {
			FileItem item = iter.next();// 요청정보 하나씩얻기

			// ** 3가지 형태의 요청정보를 처리한다
			// 1. 빈 파일
			// 2. form-data (일반적인 요청파라미터)
			// 3. 파일

			// 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			// 빈 파일이 아닐 경우
			if (item.isFormField()) {
				// form-data일 경우
				// 키:값 쌍으로 전달된 요청파라미터

				// getFieldName() : 키
				// getString() : 값

				// out.println("폼 필드 키 : " + item.getFieldName());
				// out.println("값 : " + item.getString());

				// 활용 ex)
				System.out.println("빈파일이 아닐경우");
				if ("title".equals(item.getFieldName())) {
					String data = item.getString();
					System.out.println("title :" + data);
					// 데이터 처리
				} else if ("content".equals(item.getFieldName())) {
					String content = item.getString();
					System.out.println("content : " + content);

				} // endof content if
			} else {
				// 파일일 경우
				// 웹서버 로컬디스크에 저장
				// (다른방법으로는 DB에 저장할 수 있다)

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID();
				System.out.println(uuid);

				String u = uuid.toString().split("-")[4];
				System.out.println(u);
				// -----------------

				// 로컬 저장소 파일
				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);
				// item.getName() //파일이름
				System.out.println(item.getName());
				// --- 업로드 기록 DB에 저장하기 ---

				// 업로드 파일의 PK ( fileno )
				// 연결된 테이블의 FK ( boardno, userid )

				// 업로드 파일의 원본 이름
				// getString()

				// 업로드 파일의 저장 이름
				// 코드로 바꾼이름 ( uuid 추가된 이름 )

				// 업로드한 사람 ( userid )

				// 업로드한 시간 ( 생략가능 )

				// 업로드 파일 크기 ( 생략가능 )

				// ex)
				// boardno fileno storedName originName
				// 454 67 image.jpg image.jpg_e2143f

				// ---------------------------------

				// --- 업로드 파일 DB 기록하기 ---
				// -------------------------------

				try {
					// 실제 업로드
					item.write(up);

					// 임시 파일 삭제
					item.delete();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}
}
