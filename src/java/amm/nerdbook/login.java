/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.GroupsFactory;
import amm.nerdbook.Classi.PostFactory;
import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luigi
 */
@WebServlet(loadOnStartup = 0) 
public class login extends HttpServlet {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb;create=true";
    @Override
   public void init(){
       //String dbConnection = "jdbc:derby://localhost:1527/ammdb";
       String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
       try {
           Class.forName(JDBC_DRIVER);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
       }
       UserFactory.getInstance().setConnectionString(dbConnection);
       PostFactory.getInstance().setConnectionString(dbConnection);
       GroupsFactory.getInstance().setConnectionString(dbConnection); 
   }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Apro una sessione
        HttpSession sessione = request.getSession(false);
        //CASO LOGOUT
        if(request.getParameter("log-out")!=null)
        {
            sessione.invalidate();
            request.setAttribute("page","login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
         //Controllo che l'utente non sia già loggato. Nel caso lo reindirizzo (assicurandomi
        //che l'utente sia valido) alla bacheca
        //Se l'utente è loggato la sessione "log" avrà un valore true
        if(sessione!=null && sessione.getAttribute("log")!=null &&
           sessione.getAttribute("log").equals(true))
        {
            UserFactory mu = UserFactory.getInstance();
            User u = mu.getUserById((Integer)sessione.getAttribute("user_id"));
            request.setAttribute("user",u);
            if(u.getName()==null || u.getSurname()==null || u.getFrase()==null)
            {
              request.setAttribute("page","profilo");
              request.getRequestDispatcher("profilo.html").forward(request, response);
            }
                
            else
            {
                request.setAttribute("page","bacheca");
                request.getRequestDispatcher("Bacheca").forward(request, response); 
            }  
            return;
        }
        else
        {
            //Utente non loggato-->Setto la sessione e lo reindirizzo al profilo. se
            //le credenziali sono corrispondono.Ricarico la pagina per il login
            //con un messaggio di username e pass errata
            String username = request.getParameter("userN"); 
            String password = request.getParameter("pass");//pass e UserN devono corrispondere all'id degli input
            //Creo un'istanza di MakeUser
            UserFactory mu = UserFactory.getInstance();
            if(username != null && password != null)
            {
                int id = mu.getidByUsernameAndPassword(username, password);
                //Questo metodo restituisce l'id della persona. In caso non ci sia un match
                //restituisce -1;
                //Se id e password esistono
                if(id != -1)
                {
                    //Setto i parametri della sessione
                    sessione.setAttribute("log", true); //posso controllare facilmente se l'utente è loggato
                    sessione.setAttribute("user_id", id);
                    //Passo alla prossima jsp (profilo) l'oggetto user
                    request.setAttribute("user", mu.getUserById(id));
                    request.setAttribute("user_logged", mu.getUserById(id));
                    request.setAttribute("page","profilo");
                    request.getRequestDispatcher("/M2/profilo.jsp").forward(request, response);
                    return;
                }
                else
                {
                    //nel caso le credenziali siano errate
                    //mando alla jsp di login un flag tale che dia all'utente un messaggio di errore
                    request.setAttribute("invalidData",true);
                    request.setAttribute("page","login");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }
            }
        }
         /*
          Se non si verifica nessuno degli altri casi, 
          tentativo di accesso diretto alla servlet Login -> reindirizzo verso 
          il form di login.
        */
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
