package sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class contactBean implements contactBeanRemote {

    @PersistenceContext private EntityManager em;
   
    
    @Override
    public void addHistory(int contactId, int selectedUserId, int currentUserId){
        em.joinTransaction();
        History history = new History();
        History history1 = new History();
        history.setIdcontact(contactId);
        history.setIduser(currentUserId);
        history1.setIdcontact(contactId);
        history1.setIduser(selectedUserId);
        em.persist(history1);
        em.persist(history);   
    }
    
    @Override
    public void updateContact(Contacts contact, String selectedSort,int currentUserId)
    {
        //em.createNamedQuery("History.updateContact").setParameter("contactId",contactId);
        Contacts c =em.find(Contacts.class, contact.getId());        
        em.remove(c);
       
        em.joinTransaction();
        c.setSort(selectedSort);
        c.setAddedby(currentUserId);
        em.persist(c);
        
        
        
        
    }
    
    @Override
    public Contacts addContact(int currentUserId, String selectedSort){
        em.joinTransaction();
        Contacts contact = new Contacts();
        contact.setSort(selectedSort);
        contact.setAddedby(currentUserId);
        em.persist(contact);
        return contact;       
    }
    
    
    @Override
    public List checkHistory(int currentUserId, int selectedUserId, String selectedSort ){
        List c = em.createNamedQuery("History.findEntity").setParameter("currentUserId", currentUserId).setParameter("selectedUserId", selectedUserId).getResultList();
        return c;
        
    }
            
    
    
}
