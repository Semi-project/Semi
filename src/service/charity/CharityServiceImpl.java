package service.charity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.payment.PaymentDao;
import dao.payment.PaymentDaoImpl;
import dto.charity.Charity;

public class CharityServiceImpl implements CharityService {
   
   private PaymentDao paymentDao = new PaymentDaoImpl();
      

   @Override
   public List<Charity> selectAllByUserId(Charity charity) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void selectByCharityCode(Charity charity) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void insertCharity(Charity charity) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void deleteCharityByCharityCode(Charity charity) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public Charity getParam(HttpServletRequest req, HttpServletResponse resp) {
      // TODO Auto-generated method stub
      return null;
   }

}