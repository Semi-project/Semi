package service.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileUpload {

	public void write(HttpServletRequest req, HttpServletResponse resp);
	
}
