/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coronaproject;

import com.sun.glass.events.DndEvent;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import sessionbeans.SwingBeanRemote;




/**
 *
 * @author karim
 */
public class Main {

    @EJB
    private static SwingBeanRemote swingBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Long aantalBurgers = swingBean.getAantalBurgers();
        Long aantalTests = swingBean.getAantalTests();
        Long aantalNauweContacten = swingBean.getAantalNauweContacten();
        Long aantalGewoneContacten= swingBean.getAantalGewoneContacten();
        Long aantalVeiligeContacten= swingBean.getAantalVeiligeContacten();
        System.out.println("aantal "+ aantalBurgers);
        System.out.println("Aantal tests "+ aantalTests);
        System.out.println("Aantal nauwe contacten "+ aantalNauweContacten);
        System.out.println("Aantal gewone contacten "+ aantalGewoneContacten);
        System.out.println("Aantal veilige contacten "+ aantalVeiligeContacten);
        CoronaJFrame a = new CoronaJFrame();
        a.setTitle("Applicatiearchitectuur: project");
        a.setAantalburgers(aantalBurgers);
        a.setAantalNauweContacten(aantalNauweContacten);
        a.setAantalGewoneContacten(aantalGewoneContacten);
        a.setAantalVeiligeContacten(aantalVeiligeContacten);
        a.setAantalCoronaTests(aantalTests);
        a.setVisible(true);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
}
