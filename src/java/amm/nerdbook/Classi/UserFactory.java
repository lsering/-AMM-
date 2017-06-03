/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Luigi Serreli
 */

public class UserFactory {
    private static UserFactory singleton;
    public static UserFactory getInstance() {
        if (singleton == null) {
            singleton = new UserFactory();
        }
        return singleton;
    }
    private ArrayList<User> buffer;
    private String connectionString;
    public UserFactory()
    {
    }
    public void setConnectionString(String s){
	this.connectionString = s;
}
public String getConnectionString(){
	return this.connectionString;
}
    public User getUserById(int id)
    { //Restituisce l'utente se trova l'id. In caso contrario restituisce null
        try{
        //Connessione al db
            Connection conn = DriverManager.getConnection(this.connectionString,"root","12345");
            String query = "SELECT * FROM Utente "
                            + "WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery(); //Eseguo l'interrogazione
            if(result.next()){
                User currentUser = new User(result.getInt("id"),result.getString("username"),
                result.getString("name"),result.getString("surname"),result.getString("email"),
                result.getString("password"),result.getString("urlImmagineProfilo"),result.getString("frase"));
                stmt.close();
                conn.close();
                return currentUser;
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
        e.printStackTrace();}
                return null; //IF this method returns null the user does not exists
    }

    public  int getidByUsernameAndPassword(String us,String psw)
    {
        int id=-1;
        //If this method returns -1 the user does not exists
        try{
        //Connessione al db
           // Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb","root","12345");
           Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
           String query = "SELECT id FROM Utente "
                            + "WHERE username = ? AND password = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,us);
            stmt.setString(2,psw);
            ResultSet result = stmt.executeQuery(); //Eseguo l'interrogazione
            if(result.next())
            {
                id = result.getInt("id");
            stmt.close();
            conn.close();
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        return id;
    }
    public ArrayList<User> getUserList(int id)
    {
        ArrayList<User> arr = new ArrayList<>();	
        try{	
            Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            String query = "SELECT * FROM Segue s, Utente u " +
			    "WHERE s.Follower = ? AND " +
			    "s.Followed = u.id";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            while(result.next())
	 {
		User currentUser = new User(result.getInt("id"),result.getString("username"),
                result.getString("name"),result.getString("surname"),result.getString("email"),
                result.getString("password"),result.getString("urlImmagineProfilo"));
		arr.add(currentUser);
	 }
            stmt.close();
            conn.close();
	}
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return arr;
    }
   public boolean RemoveUser(int id) throws SQLException
   {
       boolean value = true;
       Connection conn=null;
    try{
        //Cancello prima i post
            conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            conn.setAutoCommit(false);
            //Cancello la presenza di allegati nei post che cancellerò dato che sono collegati con una chiave esterna.
            //Devo trovare gli id dei post dell'utente
            String query = "SELECT p.id as id_p " +
                            "FROM post p, attached a,utente u " +
                            "WHERE a.id_p = p.id " +
                            "AND (u.id=p.sender OR u.id = p.receiver) " +
                            "AND u.id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            while(result.next())
            {
                //Per ogni id post trovato cancello l'allegato
                 query = "DELETE FROM Attached WHERE id_p = ?";
                 stmt = conn.prepareStatement(query);
                 stmt.setInt(1,result.getInt("id_p"));
                stmt.executeUpdate();
            }
            //Cancello i post
            query = "DELETE FROM POST WHERE receiver = ? OR sender = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setInt(2,id);
            stmt.executeUpdate();
            //Cancello gli amici
            query = "DELETE FROM segue WHERE follower = ? OR followed = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setInt(2,id);
            stmt.executeUpdate();
            //Cancello la presenza nel gruppo
            query = "DELETE FROM Appartiene WHERE id_u = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            query = "DELETE FROM Utente WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            conn.commit();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            try{
            value= false;
            conn.rollback();
                }
            catch (SQLException sqle2)
            {
                e.printStackTrace();            }
        finally{
        conn.setAutoCommit(true);
    }

   }
        return value;
 }
   public void updateData(User u) 
   {
       try{
       Connection conn= DriverManager.getConnection(this.getConnectionString(),"root","12345");
       String query = "Update utente "
               + "set name = ?, surname=?, frase =?, "+
               "email = ?, password = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
           stmt.setString(1,u.getName());
           stmt.setString(2,u.getSurname());
           stmt.setString(3,u.getFrase());
           stmt.setString(4,u.getEmail());
           stmt.setString(5,u.getPassword());
          //  stmt.setString(7,u.getUrlImmagineProfilo());
           stmt.setInt(6,u.getId());
           stmt.executeUpdate();
           stmt.close();
           conn.close();
       
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
   }
   public ArrayList<User> getFollowedListByName(int id,String name){
       //Questo metodo rende gli utenti seguiti dall'utente con l'id passato per parametro con nome simile a name o surname
       ArrayList<User> arr = new ArrayList<>();
   try{
       Connection conn= DriverManager.getConnection(this.getConnectionString(),"root","12345");
       String query = "SELECT * FROM Segue s, Utente u " +
			    "WHERE s.Follower = ? AND " +
			    "s.Followed = u.id AND "+
                            "(u.name like ? OR u.surname like ? OR "+
                            " u.name || ' ' || u.surname like ?   )";
       PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setString(2, "%" + name + "%");
            stmt.setString(3, "%" + name + "%");
            stmt.setString(4, "%" + name + "%");
            ResultSet result = stmt.executeQuery();
            while(result.next())
            {
                User currentUser = new User(result.getInt("id"),result.getString("username"),
                result.getString("name"),result.getString("surname"),result.getString("email"),
                result.getString("password"),result.getString("urlImmagineProfilo"));
		arr.add(currentUser);
            }
            stmt.close();
            conn.close();
   }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return arr;
   }
}
