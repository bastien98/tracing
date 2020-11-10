package sessionbeans;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface contactBeanRemote {
    public List allUsers();
    public Contacts addContact(String currentUserId, String selectedSort);
    public void addHistory(int idContact, String selectedUsername, String currentUsername);
    public List checkHistory(String currentUsername, String selectedUsername);
    public void updateContact(Contacts contact, String selectedSort,String currentUsername);
}