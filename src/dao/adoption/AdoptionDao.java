package dao.adoption;

import java.util.List;

import dto.adoption.Adoption;
import dto.animal.Animal;
import util.Paging;

public interface AdoptionDao {

   // 전체 리스트 갯수
   public int selectAdoptionCntAll(String search);

   // 일반 전체 리스트 출력
   public List<Adoption> selectAll();

   // 일반 전체 리스트 페이징 처리 후 출력
   public List<Adoption> selectPagingList(Paging paging);

   // 동물 이름로 검색
   public List<Adoption> selectAdoptionByAnimal_name(Adoption adoption);

   public Adoption selectAdoptionByAdoption_code(Adoption adoption);

   // 입양 신청하기
   public void insertAdoption(Adoption adoption);

   // 시퀀스 넘버
   public int selectSeqNextval();

   // 선택한거 삭제
   public void deleteAdoptionList(String names);

   // 선택한거 update
   public void updateAdoptionList(String names);

   public void delete(Adoption adoption);

   public void update(Adoption adoption);

   public int selectStatusbyanimalName(Adoption adoption);
   // 동물 코드로 입양 신청서 가져오기
   public Adoption getByanimalCode(Adoption adoption);

   public List<Adoption> selectAllByUserid(Adoption adoption);

   public List<Adoption> selectUseridPagingList(Paging paging);
   
   
   
   
}