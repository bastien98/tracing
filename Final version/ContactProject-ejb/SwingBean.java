/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author karim
 */
@Stateless
public class SwingBean implements SwingBeanRemote {

    @PersistenceContext private EntityManager em;
    
    @Override
    public Long getAantalBurgers() {
        Long count = (Long)em.createNamedQuery("Users.countUsersRows").getSingleResult();
        return count;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Long getAantalTests() {
        return (Long)em.createNamedQuery("Tests.CountTestsRows").getSingleResult();
    }

    @Override
    public Long getAantalNauweContacten() {
        return (Long) em.createNamedQuery("Contacts.countNauwRows").getSingleResult();
        
    }

    @Override
    public Long getAantalGewoneContacten() {
        return (Long) em.createNamedQuery("Contacts.countGewoonRows").getSingleResult();
    }

    @Override
    public Long getAantalVeiligeContacten() {
        return (Long) em.createNamedQuery("Contacts.countVeiligRows").getSingleResult();
    }
    
    
}
