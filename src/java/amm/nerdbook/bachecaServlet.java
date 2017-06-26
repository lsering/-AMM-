/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.GroupsFactory;
import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.PostFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi Serreli
 */
public class bachecaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //DEVO CONTROLLARE CHE LA SESSIONE SIA OK, NEL CASO ORGANIZZO TUTTO PER MANDARE LA BACHECA
        HttpSession s = request.getSession(false);
        if(s!=null && s.getAttribute("log")!=null &&
           s.getAttribute("log").equals(true))
        {
           UserFactory uf = UserFactory.getInstance();
           GroupsFactory gf = GroupsFactory.getInstance();
           PostFactory pf = PostFactory.getInstance();
           int id_current_user_logged = (Integer)s.getAttribute("user_id");
           request.setAttribute("user_logged",uf.getUserById(id_current_user_logged));
           if(request.getParameter("visit_user") != null)
           {
               //ricavo l'id dell'utente da visualizzare
               int id=Integer.parseInt(request.getParameter("visit_user"));
               request.setAttribute("user",uf.getUserById(id));
               request.setAttribute("posts", pf.getPostById(id));
               request.setAttribute("visit_user", id);

           }else
           {
               if(request.getParameter("visit_group")!=null)//STO VISITANDO UN GRUPPO
               {
                   //devo passare il gruppo e i post
                   int id=Integer.parseInt(request.getParameter("visit_group"));
                   request.setAttribute("group",gf.getGroupById(id));
                   request.setAttribute("posts", pf.getPostListByGroup(id));
                   request.setAttribute("visit_group", id);
                   
               }
               else //ALLORA STO VISITANDO LA BACHECA PERSONALE
               {
                   //Lancio i post dell'utente loggato
                   int id = (Integer)(s.getAttribute("user_id"));
                   request.setAttribute("user",uf.getUserById(id));
                   request.setAttribute("posts",pf.getPostById(id));
                   
               }
           }
                          //Mando i followed dell'utente loggato
               request.setAttribute("users",uf.getUserList((Integer)s.getAttribute("user_id")));
                         //Mando i gruppi dell'utente loggato
               request.setAttribute("groups",gf.getGroupByUser(uf.getUserById((Integer)s.getAttribute("user_id"))));
             request.getRequestDispatcher("/M2/bacheca.jsp").forward(request, response);
        }      
            else{ //UTENTE NON LOGGATO
                        request.setAttribute("ErrorLog",true);
                        request.getRequestDispatcher("Login").forward(request, response);
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
