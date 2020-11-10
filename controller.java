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
import sessionbeans.Users;
import sessionbeans.Contacts;
import sessionbeans.History;

public class controller extends HttpServlet {

    @EJB private contactBeanRemote contactbean;
    
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
        PrintWriter out = response.getWriter();
        
      
        
        
        if(submit.equals("naarWelkom"))
        {                            
            
            sessie.setAttribute("status", "null");
            RequestDispatcher view = request.getRequestDispatcher("users/welkomUser.jsp");
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
            Contacts contact = new Contacts();
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
