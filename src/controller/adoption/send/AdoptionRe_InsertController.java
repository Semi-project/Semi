package controller.adoption.send;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.animal.Animal;
import service.animal.AnimalService;
import service.animal.AnimalServiceImpl;

// 입양 보내기

@WebServlet("/adoption/send/insert")
public class AdoptionRe_InsertController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   // service
   private AnimalService animalService = new AnimalServiceImpl();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setCharacterEncoding("UTF-8");

      Animal animal = animalService.getParam(req, resp);

      PrintWriter out = resp.getWriter();

      // naver smart Editor 2.0에서 GET으로 보낸 값 가져오는거 확인
//      System.out.println(animal.getAnimal_Feature());

      req.getRequestDispatcher("/view/adoption/send/adoptionSend.jsp").forward(req, resp);


   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String sFileInfo = "";

      // 파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴

      String name = req.getHeader("file-name");

      String ext = name.substring(name.lastIndexOf(".") + 1);

      // 파일 기본경로

      String defaultPath = req.getServletContext().getRealPath("/");

      // 파일 기본경로 _ 상세경로

      String path = defaultPath + "upload" + File.separator;
      System.out.println(path);
      File file = new File(path);
      if (!file.exists()) {

         file.mkdirs();

      }

      String realname = UUID.randomUUID().toString() + "." + ext;

      InputStream is = req.getInputStream();

      String storedFileName = path + realname;

      OutputStream os = new FileOutputStream(storedFileName);

      int numRead;

      // 파일쓰기

      byte b[] = new byte[Integer.parseInt(req.getHeader("file-size"))];

      while ((numRead = is.read(b, 0, b.length)) != -1) {

         os.write(b, 0, numRead);

      }

      if (is != null) {
         is.close();
      }

      os.flush();

      os.close();

      String root = req.getContextPath();

      sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + root + "/upload/" + realname;
      
      //파일 저장경로
      System.out.println(sFileInfo);
      resp.getWriter().println(sFileInfo);

   }

}
