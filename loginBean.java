package sessionbeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class loginBean implements loginBeanRemote {
    
    @PersistenceContext private EntityManager em;
    
    @Override
    public List findCurrentUser(String username, String password){
        Users user;
        List a = new ArrayList();
        List users = em.createNamedQuery("Users.findLogin").setParameter("name",username).setParameter("password", password).getResultList();                   
        
       if(!users.isEmpty())
       {
           return users;    
       }
       else
       {
           return a;
       }  
    }
    
    @Override
    public List allUsers(){
        List users = em.createNamedQuery("Users.findAll").getResultList();
        return users;
    }
}
