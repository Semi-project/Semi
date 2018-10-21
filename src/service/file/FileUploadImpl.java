package service.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadImpl implements FileUpload {

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String sFileInfo = "";

		// 파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴

		String name = req.getHeader("file-name");

		String ext = name.substring(name.lastIndexOf(".") + 1);

		// 파일 기본경로

		String defaultPath = req.getServletContext().getRealPath("/");

		System.out.println("defaultPath" + ":" + defaultPath);
		// 파일 기본경로 _ 상세경로

		String path = defaultPath + "upload" + File.separator;
		System.out.println("Path:" + path);
		File file = new File(path);

		if (!file.exists()) {

			file.mkdirs();

		}

		String realname = UUID.randomUUID().toString() + "." + ext;

		InputStream is = null;
		try {
			is = req.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String storedFileName = path + realname;

		OutputStream os = null;
		try {
			os = new FileOutputStream(storedFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int numRead;

		// 파일쓰기

		byte b[] = new byte[Integer.parseInt(req.getHeader("file-size"))];

		try {
			while ((numRead = is.read(b, 0, b.length)) != -1) {

				os.write(b, 0, numRead);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (is != null) {

			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String root = req.getContextPath();

		sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + root + "/upload/" + realname;
		// System.out.println(sFileInfo);
		try {
			resp.getWriter().println(sFileInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
