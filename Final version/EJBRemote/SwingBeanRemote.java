/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import javax.ejb.Remote;

/**
 *
 * @author karim
 */
@Remote
public interface SwingBeanRemote {
    public Long getAantalBurgers() ;
    public Long getAantalTests();
    public Long getAantalNauweContacten();
    public Long getAantalGewoneContacten();
    public Long getAantalVeiligeContacten();
}
