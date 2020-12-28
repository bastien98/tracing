/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author karim
 */
@Local
public interface ContactBeanLocal {
    public List allUsers();
    public Contacts addContact(String currentUserId, String selectedSort);
    public void addHistory(int idContact, String selectedUsername, String currentUsername);
    public List checkHistory(String currentUsername, String selectedUsername);
    public void updateContact(Contacts contact, String selectedSort,String currentUsername);
    public Long findNauw(String currentUsername);
    public Long findMiddelmatig(String currentUsername);
    public Long findVeilig(String currentUsername);
    public List allDoctors();
    public void updateCseen(int contactId);
    public Contacts getContact(String currentUsername);
}
