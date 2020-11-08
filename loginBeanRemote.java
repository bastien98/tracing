/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface loginBeanRemote {
    public List findCurrentUser(String username, String password);
    public List allUsers();
            
}