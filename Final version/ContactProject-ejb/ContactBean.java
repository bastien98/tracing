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
public class ContactBean implements ContactBeanLocal {

    @PersistenceContext private EntityManager em;
    
    @Override
    public List allUsers(){
        List users = em.createNamedQuery("Users.findAll").getResultList();
        return users;
    }
    @Override
    public void addHistory(int idContact, String selectedUsername, String currentUsername){
        em.joinTransaction();
        History history = new History();
        History history1 = new History();
        history.setIdcontact(idContact);
        history.setUsername(currentUsername);
        history1.setIdcontact(idContact);
        history1.setUsername(selectedUsername);
        em.persist(history1);
        em.persist(history); 
    }
    @Override
    public List checkHistory(String currentUsername, String selectedUsername){
        List c = em.createNamedQuery("History.findEntity").setParameter("currentUsername", currentUsername).setParameter("selectedUsername", selectedUsername).getResultList();
        return c;
    }
    
   
    @Override
    public Contacts addContact(String currentUsername, String selectedSort){
        em.joinTransaction();
        Contacts contact = new Contacts();
        contact.setSort(selectedSort);
        contact.setAddedby(currentUsername);
        contact.setCseen("no");
        em.persist(contact);
        return contact; 
    }
    @Override
    public void updateContact(Contacts contact, String selectedSort,String currentUsername)
    {
        Contacts c =em.find(Contacts.class, contact.getId());
        em.remove(c);
        
        em.joinTransaction();
        c.setSort(selectedSort);
        c.setAddedby(currentUsername);
        em.persist(c);
    }
    
    public void updateCseen(int contactId)
    {
        try{
          em.createNamedQuery("Contacts.updateCseenToYes").setParameter("id", contactId).executeUpdate();
        }
        catch(Exception e)
        {
            
    }
        
      
    }
    
    @Override
    public Long findNauw(String currentUsername)
    {
        return (Long)em.createNamedQuery("Contacts.hvlNauw").setParameter("currentUsername",currentUsername).getSingleResult();
    }
    
    @Override
    public Long findMiddelmatig(String currentUsername)
    {
        return (Long)em.createNamedQuery("Contacts.hvlMiddelmatig").setParameter("currentUsername",currentUsername).getSingleResult();
    }
    
    @Override
    public Long findVeilig(String currentUsername)
    {
        return (Long)em.createNamedQuery("Contacts.hvlVeilig").setParameter("currentUsername",currentUsername).getSingleResult();
    }

    @Override
    public List allDoctors() {
        List doctors = em.createNamedQuery("Groups.findByGroupp").setParameter("groupp", "docter").getResultList();
        return doctors;
    }

    @Override
    public Contacts getContact(String currentUsername) {
        Contacts contact = (Contacts) em.createNamedQuery("Contacts.findByAddedbyWithSeenNo").setParameter("addedby", currentUsername).getSingleResult();
        return contact;
    }
}
