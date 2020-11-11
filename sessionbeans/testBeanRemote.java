package sessionbeans;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface testBeanRemote {
    public List findNauw(String currentUsername);
    public List findMiddelmatig(String currentUsername);
    public void updateTest(int selectedTestId,String status);
    public List findUsernamebyTestId(int selectedTestId);
}