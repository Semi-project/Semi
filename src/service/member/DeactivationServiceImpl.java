package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.member.DeactivationDao;
import dao.member.DeactivationDaoImpl;
import dao.member.Delete_reasonDao;
import dao.member.Delete_reasonDaoImpl;
import dao.member.MemberDao;
import dao.member.MemberDaoImpl;
import dto.member.Deactivation;
import dto.member.Member;

public class DeactivationServiceImpl implements DeactivationService {

   private MemberDao memberDao = new MemberDaoImpl();
   private DeactivationDao deactivationDao = new DeactivationDaoImpl();
   private Delete_reasonDao delete_ReasonDao= new Delete_reasonDaoImpl();
   

   @Override
   public void deleteMember(Member member) {

   }


   @Override
   public Member getParam(HttpServletRequest req, HttpServletResponse resp) {
      // TODO Auto-generated method stub
      return null;
   }


   @Override
   public void insert(Deactivation deactivation) {
      try {
         deactivationDao.insertDeactivation(deactivation);
      } catch (Exception e) {
         
         e.printStackTrace();
      }
      
   }

}