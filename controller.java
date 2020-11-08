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
import sessionbeans.loginBeanRemote;
import sessionbeans.contactBeanRemote;
import sessionbeans.Users;
import sessionbeans.Contacts;
import sessionbeans.History;

public class controller extends HttpServlet {

    @EJB private loginBeanRemote loginbean;
    @EJB private contactBeanRemote contactbean;
    
    @Override
    public void init(){
        List users = loginbean.allUsers();
        getServletContext().setAttribute("users",users);
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession sessie = request.getSession();
        String submit = request.getParameter("submit");
        Users currentUser = new Users();
        PrintWriter out = response.getWriter();
        System.out.println("begin");

        if(submit.equals("fromLogin"))
        {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            List currentUsers = loginbean.findCurrentUser(username,password);
            sessie.setAttribute("currentUsers",currentUsers);
            
            if(!currentUsers.isEmpty())
            {
                sessie.setAttribute("invalidUser", "no");
                currentUser = (Users)currentUsers.get(0);
                sessie.setAttribute("currentUser",currentUser); 

                if( currentUser.getId() != 0)
                {                              
                    sessie.setAttribute("status", "null");
                    RequestDispatcher view = request.getRequestDispatcher("welkom.jsp");
                    view.forward(request,response);
                }
            }
            else
            {
                sessie.setAttribute("invalidUser", "yes");
                RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                view.forward(request,response);
            }           
        }
         if(submit.equals("naarWelkom"))
        {                            
            sessie.setAttribute("status", "null");
            RequestDispatcher view = request.getRequestDispatcher("welkom.jsp");
            view.forward(request,response);
        }
        if(submit.equals("registreer"))
        {                     
            
            RequestDispatcher view = request.getRequestDispatcher("addContact.jsp");
            view.forward(request,response);
        }
        
        if(submit.equals("voegToe"))
        {
            sessie.setAttribute("status", "null");
            Contacts temp = null ;
            currentUser = (Users)sessie.getAttribute("currentUser");
            String selectedContactSort = request.getParameter("selectedSort");
            Contacts contact = new Contacts();
            List c = contactbean.checkHistory(currentUser.getId(), Integer.parseInt(request.getParameter("selectedUser")), selectedContactSort);
            if(! c.isEmpty())
            {
                 temp = (Contacts)c.get(0);
            }
            if(temp != null)
            {
                if(temp.getSort().equals(selectedContactSort) == false )
                { 
                    sessie.setAttribute("status", "updated");
                    contactbean.updateContact(temp,selectedContactSort,currentUser.getId());
                    RequestDispatcher view = request.getRequestDispatcher("welkom.jsp");
                    view.forward(request,response);
                }
                else
                {
                      sessie.setAttribute("status", "double");
                      RequestDispatcher view = request.getRequestDispatcher("addContact.jsp");
                      view.forward(request,response);
                }
            } 
            else
            {
                sessie.setAttribute("status", "new");
                contact = contactbean.addContact(currentUser.getId(), selectedContactSort);
                contactbean.addHistory(contact.getId(), Integer.parseInt(request.getParameter("selectedUser")), currentUser.getId());
                RequestDispatcher view = request.getRequestDispatcher("welkom.jsp");
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
