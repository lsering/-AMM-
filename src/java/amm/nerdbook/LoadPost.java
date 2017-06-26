/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.GroupsFactory;
import amm.nerdbook.Classi.User;
import amm.nerdbook.Classi.UserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi Serreli
 */
public class LoadPost extends HttpServlet {

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
            HttpSession sessione = request.getSession(false);
            if(sessione!=null && sessione.getAttribute("log")!=null &&
           sessione.getAttribute("log").equals(true))
        {
            UserFactory uf = UserFactory.getInstance();
            User u_mittente = uf.getUserById((Integer)sessione.getAttribute("user_id"));
            User u_destinatario;
            request.setAttribute("is_bacheca",1);
            request.setAttribute("mit",u_mittente);
            String testo = request.getParameter("textPost");
            request.setAttribute("testo",testo);
            if(request.getParameter("gruppo")!=null)
            {

                //CASO GRUPPO
                    request.setAttribute("is_bacheca",0);
                    int id_bach_destinatario =Integer.parseInt(request.getParameter("gruppo"));
                    GroupsFactory gf = GroupsFactory.getInstance();
                    request.setAttribute("dest", gf.getGroupById(id_bach_destinatario));
            }else
            {
            if(Integer.parseInt(request.getParameter("bacheca"))==0)
            {
                u_destinatario = u_mittente;
                request.setAttribute("dest", u_destinatario);
            }else{
                if(request.getParameter("bacheca")!=null){
                  //CASO UTENTE 
                  int id_bach_destinatario =Integer.parseInt(request.getParameter("bacheca"));
                  u_destinatario = uf.getUserById(id_bach_destinatario);
                  request.setAttribute("dest", u_destinatario);
                }                     
                } 
            }
            request.getRequestDispatcher("ConfirmPage.jsp").forward(request, response);
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
