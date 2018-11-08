package dao.adoption;

import java.util.List;

import dto.adoption.Adoption;
import dto.animal.Animal;
import util.Paging;

public interface AdoptionDao {

   // ��ü ����Ʈ ����
   public int selectAdoptionCntAll(String search);

   // �Ϲ� ��ü ����Ʈ ���
   public List<Adoption> selectAll();

   // �Ϲ� ��ü ����Ʈ ����¡ ó�� �� ���
   public List<Adoption> selectPagingList(Paging paging);

   // ���� �̸��� �˻�
   public List<Adoption> selectAdoptionByAnimal_name(Adoption adoption);

   public Adoption selectAdoptionByAdoption_code(Adoption adoption);

   // �Ծ� ��û�ϱ�
   public void insertAdoption(Adoption adoption);

   // ������ �ѹ�
   public int selectSeqNextval();

   // �����Ѱ� ����
   public void deleteAdoptionList(String names);

   // �����Ѱ� update
   public void updateAdoptionList(String names);

   public void delete(Adoption adoption);

   public void update(Adoption adoption);

   public int selectStatusbyanimalName(Adoption adoption);
   // ���� �ڵ�� �Ծ� ��û�� ��������
   public Adoption getByanimalCode(Adoption adoption);

   public List<Adoption> selectAllByUserid(Adoption adoption);

   public List<Adoption> selectUseridPagingList(Paging paging);
   
   
   
   
}