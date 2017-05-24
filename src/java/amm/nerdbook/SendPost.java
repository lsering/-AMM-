/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook;

import amm.nerdbook.Classi.Attached;
import amm.nerdbook.Classi.UserFactory;
import amm.nerdbook.Classi.Post;
import amm.nerdbook.Classi.PostFactory;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            int id=(int)sessione.getAttribute("user_id"); //Prelevo l'id del mittente
            String text = request.getParameter("textPost");
            if(request.getParameter("visit_user")!=null)
            {    //devo inviare ad una bacheca
                int dest = Integer.parseInt(request.getParameter("visit_user"));
                if(dest == 0) //Sto inviando nella bacheca dell'user connesso
                    dest = id;
                Date date = new Date();
                UserFactory mu = UserFactory.getInstance();
            //La procedura per inserire allegati(file o link) verrà
            //implementata in seguito
                PostFactory pf = PostFactory.getInstance();
            ArrayList<Attached> a = new ArrayList<>(); //PER ORA NON INVIO ALLEGATI
            if(text != null)
            {
                Post p = new Post(id,mu.getUserById(id),text,new Timestamp(date.getTime()).toString(),a,dest);
                //INSERISCO I DATI NEL DB RICHIAMANDO UN METODO DELLA CLASSE POSTFACTORY
                pf.InsertData_bacheca(p);
                 pf.addPost(p);
                 request.setAttribute("user", mu.getUserById(dest));
                 request.setAttribute("posts", pf.getPostById(dest));
                 request.setAttribute("users",mu.getUserList(id));
                 request.setAttribute("visit_user", dest);
                 String path ="Bacheca?visit_user="+dest;
                request.getRequestDispatcher(path).forward(request, response);  
                
            }
       }else
            {
                //NEL CASO CONTRARIO PER FORZA SARA' UN GRUPPO MA PER SICUREZZA METTO UN IF
                if(request.getParameter("visit_group")!=null)
                {
                    int dest = Integer.parseInt(request.getParameter("visit_group"));
                    Date date = new Date();
                    UserFactory mu = UserFactory.getInstance();
                    PostFactory pf = PostFactory.getInstance();
                    ArrayList<Attached> a = new ArrayList<>(); //PER ORA NON INVIO ALLEGATI
                    if(text != null)
                     {
                        Post p = new Post(id,mu.getUserById(id),text,a,dest);
                        p.setData(new Timestamp(date.getTime()).toString());
                        //INSERISCO I DATI NEL DB RICHIAMANDO UN METODO DELLA CLASSE POSTFACTORY
                        pf.InsertData_groups(p);
                        pf.addPost(p);
                        request.setAttribute("user", mu.getUserById(dest));
                        request.setAttribute("posts", pf.getPostById(dest));
                        request.setAttribute("users",mu.getUserList(id));
                        request.setAttribute("visit_group", dest);
                        String path ="Bacheca?visit_group="+dest;
                        request.getRequestDispatcher(path).forward(request, response);  
                
                      }
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

