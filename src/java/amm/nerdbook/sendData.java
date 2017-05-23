/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi Serreli
 */
public class sendData extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sessione = request.getSession(false);
        if (sessione.getAttribute("log") != null &&
            sessione.getAttribute("log").equals(true)) {
            int id=(int)sessione.getAttribute("user_id");
            if(request.getParameter("delete_User")!=null)
            { //delete User
               UserFactory uf = UserFactory.getInstance();
               boolean del = uf.RemoveUser(id);
               if(del == true)
                   request.getRequestDispatcher("Login?log-out=1").forward(request, response);
               else
               {
                   request.setAttribute("Errordel",true);
                   request.getRequestDispatcher("profilo.html").forward(request, response);
               }

                   }
            else
            {
                UserFactory mu = UserFactory.getInstance();
                User u = mu.getUserById(id);
                User recoveryUser = u;
                String name = request.getParameter("nome"); 
                String surname = request.getParameter("cognome");
                String frase = request.getParameter("presentazione");
                String email = request.getParameter("email");
                String psw = request.getParameter("password");
                String pswconf = request.getParameter("confpassword");
                if(name != null && surname != null && frase!= null )
            {
                u.setName(name);
                u.setSurname(surname);
                u.setFrase(frase);
                u.setEmail(email);
                if(psw.equals(pswconf))
                {
                mu.updateData(u);
                request.setAttribute("user", u);
                request.setAttribute("ErrorData",false);
                }
                else
                {
                    request.setAttribute("ErrorData",true);
                    request.setAttribute("user", recoveryUser);
                }
                request.getRequestDispatcher("/M2/profilo.jsp").forward(request, response);
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sendData.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(sendData.class.getName()).log(Level.SEVERE, null, ex);
        }
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
