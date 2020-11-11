package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbeans.contactBeanRemote;
import sessionbeans.testBeanRemote;
import sessionbeans.Users;
import sessionbeans.Contacts;
import sessionbeans.History;
import sessionbeans.Tests;

public class controller extends HttpServlet {

    @EJB private contactBeanRemote contactbean;
    @EJB private testBeanRemote testbean;
    
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
        
      
        if(submit.equals("testAanvragen"))
        {
            RequestDispatcher view = request.getRequestDispatcher("users/testAanvragen.jsp");
            view.forward(request,response);
        }
        
        if(submit.equals("naarWelkom"))
        {                            
            
            sessie.setAttribute("status", "null");
            RequestDispatcher view = request.getRequestDispatcher("users/welkomUser.jsp");
            view.forward(request,response);
        }
        
        if(submit.equals("naarWelkomDrs"))
        {                            
            RequestDispatcher view = request.getRequestDispatcher("docters/welkomDokters.jsp");
            view.forward(request,response);
        }
        if(submit.equals("fromWelkomDrs"))
        {
            if(request.getParameter("testId").equals(""))
            {
                sessie.setAttribute("bevestigd", "niks");
                RequestDispatcher view = request.getRequestDispatcher("docters/welkomDokters.jsp");
                view.forward(request,response);
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
                    RequestDispatcher view = request.getRequestDispatcher("docters/zeker.jsp");
                    view.forward(request,response);
            }
            else
            {
                sessie.setAttribute("bevestigd", "double");
                RequestDispatcher view = request.getRequestDispatcher("docters/welkomDokters.jsp");
                view.forward(request,response);
            }
            }
            
            
            
        }
        
        if(submit.equals("fromFooter")){
           sessie.invalidate();
           RequestDispatcher view = request.getRequestDispatcher("index.jsp");
           view.forward(request,response);
       }
        
        
        if(submit.equals("naarZeker"))
        {
            testbean.updateTest((int)sessie.getAttribute("testId"),(String)sessie.getAttribute("status"));
            sessie.setAttribute("bevestigd", "ja");
            RequestDispatcher view = request.getRequestDispatcher("docters/welkomDokters.jsp");
            view.forward(request,response);
        }
        
        
        
        if(submit.equals("registreer"))
        {                     
            RequestDispatcher view = request.getRequestDispatcher("users/addContact.jsp");
            view.forward(request,response);
        }
        
       if(submit.equals("voegToe"))
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
                    RequestDispatcher view = request.getRequestDispatcher("users/welkomUser.jsp");
                    view.forward(request,response);
                }
                else
                {
                      sessie.setAttribute("status", "double");
                      RequestDispatcher view = request.getRequestDispatcher("users/addContact.jsp");
                      view.forward(request,response);
                }
            }
            else
            {
                sessie.setAttribute("status", "new");
                contact = contactbean.addContact(currentUsername, selectedContactSort);
                contactbean.addHistory(contact.getId(), request.getParameter("selectedUsername"), currentUsername);
                RequestDispatcher view = request.getRequestDispatcher("users/welkomUser.jsp");
                view.forward(request,response);
            }
        }
       
       if(submit.equals("statusRaadplegen"))
        {   
            List n = testbean.findNauw(currentUsername);
            List m = testbean.findMiddelmatig(currentUsername);
            sessie.setAttribute("nauwCount", contactbean.findNauw(currentUsername));
            sessie.setAttribute("middelmatigCount", contactbean.findMiddelmatig(currentUsername));
            sessie.setAttribute("veilgCount", contactbean.findVeilig(currentUsername));
            if(n.isEmpty() && m.isEmpty())
            {
                sessie.setAttribute("risico","LAAG");
                RequestDispatcher view = request.getRequestDispatcher("users/statusRaadplegen.jsp");
                view.forward(request,response);
            }
            else
            {
                sessie.setAttribute("risico","HOOG");
                RequestDispatcher view = request.getRequestDispatcher("users/statusRaadplegen.jsp");
                view.forward(request,response);
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

}
