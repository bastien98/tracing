package sessionbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class testBean implements testBeanRemote {

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
}
