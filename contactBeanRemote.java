package sessionbeans;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface contactBeanRemote {
    public Contacts addContact(int currentUserId, String selectedSort);
    public void addHistory(int contactId, int selectedUserId, int currentUserId);
    public List checkHistory(int currentUserId, int selectedUserId, String selectedSort );
    public void updateContact(Contacts contact, String selectedSort,int currentUserId);
}