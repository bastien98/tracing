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
public interface TestBeanLocal {
    public List findNauw(String currentUsername);
    public List findMiddelmatig(String currentUsername);
    public void updateTest(int selectedTestId,String status);
    public List findUsernamebyTestId(int selectedTestId);
    public Tests addTest(String currentUsername, String doctorName);
    public boolean findTest(String currentUsername);
    public boolean findTests(String currentUsername);
    public Tests getTest(String cuurentUsername);
    public boolean findUserByUsername(String currentUsername);
    public boolean findIfUserDidTest(String currentUsername);
    public boolean updateNotified(int TestId);
    public boolean updateSeen(int TestId);
}
