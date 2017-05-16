/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import amm.nerdbook.Classi.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            //Controllo se visit_user ha un valore. Così reindirizzo alla bacheca da visitare
            String user = request.getParameter("visit_user");
            int id;
            if(user !=null)
                id = Integer.parseInt(user);
            else
                id=(int)s.getAttribute("user_id");
               PostFactory pf = PostFactory.getInstance();
                 UserFactory mu = UserFactory.getInstance();
                 List<Post> posts;
                 User u = mu.getUserById(id);
       //DEVO PASSARE ALLA PROSSSIMA JSP GLI OGGETTI DA STAMPARE
                 posts = pf.getPostById(id);
                 request.setAttribute("user", u);
                 request.setAttribute("visit_user", user);
                 request.setAttribute("posts", posts);
                 request.setAttribute("users",mu.getUserList(id));
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
