/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi Serreli
 */

public class SendPost extends HttpServlet {

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
        //Controllo la sessione:
        HttpSession sessione = request.getSession(false);
        if (sessione.getAttribute("log") != null &&
            sessione.getAttribute("log").equals(true)) {
            int id=(int)sessione.getAttribute("user_id");
            //Ottengo la data attuale
            //trovo l'id della bacheca. Se visit_user è settato prendo quello senno lo prendo dalla sessione
            String user = request.getParameter("visit_user");
            int id_bacheca;
            if(user != null && Integer.parseInt(user)!=0 )
            {
               
                try{
                    id_bacheca =Integer.parseInt(user); //POTREBBE DARE ERRORE CONVERSIONE
                }catch(Exception exc){
                    id_bacheca = id;
                }
            }
            else
              id_bacheca = id;
            Calendar c=Calendar.getInstance();
            UserFactory mu = UserFactory.getInstance();
            String text = request.getParameter("textPost");
            //La procedura per inserire allegati(file o link) verrà
            //implementata in seguito
            PostFactory pf = PostFactory.getInstance();
            if(text != null)
            {
                Post p = new Post(id,mu.getUserById(id),text,c.getTime().toString(),id_bacheca);
                pf.addPost(p);
                 request.setAttribute("user", mu.getUserById(id_bacheca));
                 request.setAttribute("posts", pf.getPostById(id_bacheca));
                 request.setAttribute("users",mu.getUserList(id));
                 request.setAttribute("visit_user", id_bacheca);
                 String path ="Bacheca?visit_user="+id_bacheca;
                request.getRequestDispatcher(path).forward(request, response);  
                
            }
            else // SE NON C'E' NIENTE NEL FORM
            {
                request.setAttribute("user", mu.getUserById(id_bacheca));
                 request.setAttribute("posts", pf.getPostById(id_bacheca));
                 request.setAttribute("users",mu.getUserList(id));
                 request.setAttribute("visit_user", user);
                 //PER ORA NON FACCIO NIENTE
                //request.getRequestDispatcher("/M2/bacheca.jsp").forward(request, response);  
            } 
            
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

