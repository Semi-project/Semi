package service.adoption;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.adoption.Adoption;
import dto.animal.Animal;
import dto.file.Animal_Filetb;
import util.Paging;

public interface AdoptionService {


   // �Ծ� ��û���� �Ķ���� ����
   public Adoption getParam(HttpServletRequest req, HttpServletResponse resp);

   // ��û�Ķ���Ϳ��� curPage ��ȯ
   public int getCurPage(HttpServletRequest req);
   public String getSearch(HttpServletRequest req);

   public int getTotalCount(String search) ;

   // �Խñ� ��ü ��ȸ
   public List<Adoption> getList();

   

   // �Ծ��û�� ����Ʈ ��� (����¡)
   public List<Adoption> getPagingList(Paging paging);
   
   
   public Adoption view(Adoption adoptionView);
   
   
   // �Խñ� ����
   public void updateList(String names);
   // ����
   public void delete(Adoption adoption);
   public void deleteAdoptionList(String names);
   public void write(HttpServletRequest req, HttpServletResponse resp);

   public int getStatus(Adoption adoption);
   // �����ڵ�� �Ծ� ��û�� ã��
   public Adoption getByanimalCode(Animal animal);

   List<Adoption> getPagingUseridList(Paging paging);
}