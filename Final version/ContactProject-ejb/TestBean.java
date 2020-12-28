/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author karim
 */
@Stateless
public class TestBean implements TestBeanLocal {

    @PersistenceContext private EntityManager em;
    
    @Override
    public List findNauw(String currentUsername){
        List users = em.createNamedQuery("Tests.findNauw").setParameter("currentUsername", currentUsername).getResultList();
        
        return users;
    }
    
    @Override
    public List findMiddelmatig(String currentUsername){
        List users = em.createNamedQuery("Tests.findMiddelmatig").setParameter("currentUsername", currentUsername).getResultList();
        return users;
    }
    
    @Override
    public void updateTest(int selectedTestId,String status)
    {
        Tests t =em.find(Tests.class, selectedTestId);
        
        em.joinTransaction();
        t.setStatus(status);
        em.persist(t);
    }
    
    @Override
    public List findUsernamebyTestId(int selectedTestId){
        List users = em.createNamedQuery("Tests.findById").setParameter("id", selectedTestId).getResultList();
        return users;
    }

    @Override
    public Tests addTest(String currentUsername, String doctorName) {
        
        Tests test = new Tests();
        Users doc = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", doctorName).getSingleResult();
        test.setUsername(currentUsername);
        test.setDocname(doc);
        test.setNotified("no");
        test.setSeen("no");
        em.persist(test);
        return  test;
    }

    @Override
    public boolean findTest(String currentUsername) {
        try{
            Tests test = (Tests) em.createNamedQuery("Tests.findByUsername").setParameter("username", currentUsername).getSingleResult();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean findUserByUsername(String currentUsername) {
        try{
        Users u = (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", currentUsername).getSingleResult();
        }
        catch(Exception e)
        {
            return false;
        }
         return true;
    }

    @Override
    public boolean findIfUserDidTest(String currentUsername) {
        
            
            int didTest = 1;
            
            List testsList = em.createNamedQuery("Tests.findByUsername").setParameter("username", currentUsername).getResultList();
            if(testsList.isEmpty())
                return false;
            
            for(int i = 0 ; i < testsList.size(); ++i)
            {   
                
                Tests t = (Tests)testsList.get(i);
                if(  t.getSeen().equals("yes"))
                {
                    didTest =0;
                }
                else
                {
                    didTest =1;
                    break;
                }
            }
            
            if(didTest == 1)
            {
                return true;
            }
             else
            {
                return false;
            }
        
       
        
    }
     
    
    @Override
    public boolean updateNotified(int TestId) {
        try{
            
         em.createNamedQuery("Tests.updateNotifedToYes").setParameter("id", TestId).executeUpdate();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
      }

    @Override
    public Tests getTest(String cuurentUsername) {
        Tests test = (Tests) em.createNamedQuery("Tests.findByUsernameWithSeenNo").setParameter("username", cuurentUsername).getSingleResult();
        return test;
    }

    @Override
    public boolean updateSeen(int TestId) {
        try{
            
         em.createNamedQuery("Tests.updateSeenToYes").setParameter("id", TestId).executeUpdate();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean findTests(String currentUsername) {
        List TestsList = em.createNamedQuery("Tests.findByUsername").setParameter("username", currentUsername).getResultList();
        for(int i = 0 ;  i < TestsList.size() ; i++)
        {
            Tests t=(Tests)TestsList.get(i);
            if(t.getSeen().equals("no"))
                return true;
            
        }
        return false;
    }
}
