

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import javax.mail.Session;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbeans.ContactBeanLocal;
import sessionbeans.Users;
import sessionbeans.Contacts;
import sessionbeans.History;
import sessionbeans.TestBeanLocal;
import sessionbeans.Tests;

public class Controller extends HttpServlet {
    
    
    @EJB private ContactBeanLocal contactbean;
    @EJB private TestBeanLocal testbean;
    
    @Override
    public void init(){
        List users = contactbean.allUsers();
        
        getServletContext().setAttribute("users",users);
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessie = request.getSession();
        String submit = request.getParameter("submit");
        String currentUsername =  request.getUserPrincipal().getName();
        
        sessie.setAttribute("currentUsername", currentUsername);
        Contacts contact = new Contacts();
        
        PrintWriter out = response.getWriter();
        System.out.println("controllers.controller.processPostRequest() "+ currentUsername +" "+ submit );
        
        if(submit.equals("testAanvragen")) //CoronaTest.jsp
        {
            String doctorName = (String)request.getParameter("selectedDoctor");
            Tests test =testbean.addTest(currentUsername , doctorName);
            
            System.out.println(doctorName);
            sessie.setAttribute("testId", test.getId());
            sessie.setAttribute("doctorName", doctorName);
            
            gotoPage("users/TestAanvragen.jsp", request, response);
        }
        
        if(submit.equals("coronaTest")) //WlekomUser.jsp
        {
            boolean ifTest = testbean.findIfUserDidTest(currentUsername);
            if(ifTest)
            {
                System.out.println("he asked already ");
                sessie.setAttribute("testDone","yes"); 
               
            }
            else
            {
                System.out.println("he can ask for test");
                sessie.setAttribute("testDone","no");
                
            }
            
            List doctors=contactbean.allDoctors();
            sessie.setAttribute("doctors", doctors);
            
            gotoPage("users/CoronaTest.jsp", request, response);
            
        }
        
        
        if(submit.equals("naarWelkom")) // CoronaTest.jsp en AddContact.jsp
        {                            
            
            sessie.setAttribute("status", "null");
            gotoPage("users/WelkomUser.jsp", request, response);
            
        }
        
        
        if(submit.equals("naarWelkomFromStatusraadplegen")) //StatusRaadplegen.jsp
        {  
            try{
                String changeSeen= (String)sessie.getAttribute("changeSeen");
                String nogInUitvoering=(String )sessie.getAttribute("nogInUitvoering");
                
                if(nogInUitvoering.equals("no"))
                    {
                        if(changeSeen.equals("yes"))
                            {
                                try
                                {
                                    Tests t= testbean.getTest(currentUsername);
                                    testbean.updateSeen(t.getId());
                                    sessie.setAttribute("test", "yes");
                                }
                                catch(Exception e)
                                {
                                    System.out.println("geen test");
                                    }
                            }
                    }
            }catch(Exception e)
                {}
           
            sessie.setAttribute("status", "null");
            
            gotoPage("users/WelkomUser.jsp", request, response);
           
        } 
        
        if(submit.equals("naarWelkomFromCoronaTest"))//TestAanvragen.jsp
        {
            try{
            String toRed = (String)sessie.getAttribute("toRed");
            if(toRed.equals("yes"))
            {
                Contacts cont = contactbean.getContact(currentUsername);
                contactbean.updateCseen(cont.getId());
                sessie.setAttribute("toRed", "no");
            }
           }catch(Exception e){}
            sessie.setAttribute("test", "no");
            gotoPage("users/WelkomUser.jsp", request, response);
            
        } 
        
        if(submit.equals("naarWelkomDrs")) //index.jsp
        {                            
            gotoPage("docters/WelkomDokters.jsp", request, response);
            
        }
        
        if(submit.equals("fromWelkomDrs")) // index.jsp
        {
            if(request.getParameter("testId").equals(""))
            {
                sessie.setAttribute("bevestigd", "niks");
                gotoPage("docters/WelkomDokters.jsp", request, response);
               
            }
            else
            {
                List t = testbean.findUsernamebyTestId(Integer.parseInt(request.getParameter("testId")));
                if(!t.isEmpty())
                {
                    Tests test = (Tests)t.get(0);
                    
                    sessie.setAttribute("testId", test.getId());
                    sessie.setAttribute("status", request.getParameter("status"));
                    sessie.setAttribute("testName",test.getUsername());
                    testbean.updateNotified(test.getId());
                    gotoPage("docters/Zeker.jsp", request, response);
                    
                }
                else
                {
                    sessie.setAttribute("bevestigd", "double");
                    gotoPage("docters/WelkomDokters.jsp", request, response);
                    
                }
            }
        }
        
        if(submit.equals("fromFooter")) //footer.jsp
        {
           sessie.invalidate();
           gotoPage("index.jsp", request, response);
           
       }
        
        
        if(submit.equals("naarZeker")) //docters/Zeker.jsp
        {
            testbean.updateTest((int)sessie.getAttribute("testId"),(String)sessie.getAttribute("status"));
            sessie.setAttribute("bevestigd", "ja");
            gotoPage("docters/WelkomDokters.jsp", request, response);
            
        }
        
        
        
        if(submit.equals("registreer"))//users/WelkomUser.jsp
        {                     
            gotoPage("users/AddContact.jsp", request, response);
            
        }
        
       if(submit.equals("voegToe")) // users/AddContact.jsp
        {
            String selectedContactSort = request.getParameter("selectedSort");
            String selectedUsername = request.getParameter("selectedUsername");
            List c = contactbean.checkHistory(currentUsername, selectedUsername);
            Contacts temp = null ;
            
            if(! c.isEmpty())
            {
                 temp = (Contacts)c.get(0);
            }
            if(temp != null)
            {
                if(temp.getSort().equals(selectedContactSort) == false )
                {
                    sessie.setAttribute("status", "updated");
                    contactbean.updateContact(temp,selectedContactSort,currentUsername);
                    gotoPage("users/WelkomUser.jsp", request, response);
                                    }
                else
                {
                      sessie.setAttribute("status", "double");
                      gotoPage("users/AddContact.jsp", request, response);
                      
                }
            }
            else
            {
                sessie.setAttribute("status", "new");
                contact = contactbean.addContact(currentUsername, selectedContactSort);
                contactbean.addHistory(contact.getId(), request.getParameter("selectedUsername"), currentUsername);
                gotoPage("users/WelkomUser.jsp", request, response);
                
            }
        }
       
       if(submit.equals("statusRaadplegen")) /// users/StatusRaadplegen.jsp
        {   
            List n = testbean.findNauw(currentUsername);
            List m = testbean.findMiddelmatig(currentUsername);
            sessie.setAttribute("nauwCount", contactbean.findNauw(currentUsername));
            sessie.setAttribute("middelmatigCount", contactbean.findMiddelmatig(currentUsername));
            sessie.setAttribute("veilgCount", contactbean.findVeilig(currentUsername));
            
            String seen =(String)sessie.getAttribute("seen"); // not used
            //check if the user have asked for a test
           // boolean test = testbean.findTest(currentUsername);
            boolean test = testbean.findTests(currentUsername);
            //if yes
            if(test)
            {   
                 //show the status of the test in uitvoeting if notifed in no ,, positive or negative if notified is yes
                //and dont't show the test button if one of the contact is infected
                
                Tests userTest = testbean.getTest(currentUsername);
                System.out.println(userTest.getSeen());
                sessie.setAttribute("notified", userTest.getNotified());
                sessie.setAttribute("testId", userTest.getId());
                sessie.setAttribute("status", userTest.getStatus());
                sessie.setAttribute("seen", userTest.getSeen());
                sessie.setAttribute("test", "no");
                
            }          
                    //if no
                        //show the test button if one of the contact is infected
                        //and don't show nothing about the status
            
                
            
            
            if(n.isEmpty() && m.isEmpty())
            {
                sessie.setAttribute("risico","LAAG");
                gotoPage("users/StatusRaadplegen.jsp", request, response);
                
            }
            else
            {
                sessie.setAttribute("risico","HOOG");
                gotoPage("users/StatusRaadplegen.jsp", request, response);
                //RequestDispatcher view = request.getRequestDispatcher("users/StatusRaadplegen.jsp");
                //view.forward(request,response);
            }
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public void gotoPage(String c ,HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher(c);
            view.forward(request, response);
    }
}
