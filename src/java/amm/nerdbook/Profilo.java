/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.User;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Profilo extends HttpServlet {

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
        HttpSession s = request.getSession(false);
        PrintWriter out = response.getWriter();
        //CONTROLLO CHE L'UTENTE SIA LOGGATO
        if(s!=null && s.getAttribute("log")!=null &&
           s.getAttribute("log").equals(true)){
            //Caso utente autenticato
            int id=(int)s.getAttribute("user_id");
            //SE NON VOGLIO FARE MODIFICHE AL PROFILO MA SOLO CARICARLO:
            if(request.getParameter("delete")== null && request.getParameter("update")==null)
            {
            UserFactory mu = UserFactory.getInstance();
            User u = mu.getUserById(id);
            request.setAttribute("user", u);
            request.setAttribute("user_logged", u);
            request.getRequestDispatcher("/M2/profilo.jsp").forward(request, response);
            }
            else{
                 if(request.getParameter("update")!=null)
                      //CASO BOTTONE AGGIORNAMENTO DATI
                   {
                UserFactory mu = UserFactory.getInstance();
                User u = mu.getUserById(id);
                String name_rec = u.getName();
                String surname_rec = u.getSurname();
                String frase_rec = u.getFrase();
                String email_rec = u.getEmail();
                String psw_rec = u.getPassword();
                String name = request.getParameter("nome"); 
                String surname = request.getParameter("cognome");
                String frase = request.getParameter("presentazione");
                String email = request.getParameter("email");
                String psw = request.getParameter("password");
                String pswconf = request.getParameter("confpassword");
                if(name != null && surname != null && frase!= null && psw!= null )
               {
                u.setName(name);
                u.setSurname(surname);
                u.setFrase(frase);
                u.setEmail(email);
                if(psw.equals(pswconf))
                {
                u.setPassword(psw);    
                mu.updateData(u);
                request.setAttribute("ErrorData",false);
                }else
                {
                    //AVVIO IL RIPRISTINO DATO L'ERRORE DI INSERIMENTO
                    u.setName(name_rec);
                    u.setSurname(surname_rec);
                    u.setEmail(email_rec);
                    u.setFrase(frase_rec);
                    u.setPassword(psw_rec);
                    request.setAttribute("ErrorData",true);

                }
                request.setAttribute("user", u);
                request.setAttribute("user_logged", u);
                request.getRequestDispatcher("/M2/profilo.jsp").forward(request, response);
                   }
                 }
                 else{
                    out.println("CANCELLATO");
                     if(request.getParameter("delete")!=null)
                     {
                         //CASO BOTTONE CANCELLA UTENTE
                          UserFactory uf = UserFactory.getInstance();
                          boolean del = uf.RemoveUser(id);
                          if(del == true)
                         request.getRequestDispatcher("Login?log-out=1").forward(request, response);
                         
                     }else
                         {
                         request.setAttribute("Errordel",true);
                         request.getRequestDispatcher("profilo.html").forward(request, response);
                         }

                 }
                 
            }
        }
        else
        {
            request.setAttribute("ErrorLog",true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
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
            Logger.getLogger(Profilo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Profilo.class.getName()).log(Level.SEVERE, null, ex);
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
