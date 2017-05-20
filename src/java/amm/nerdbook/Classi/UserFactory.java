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
                result.getString("password"),result.getString("urlImmagineProfilo"));
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
    public int getNumOfUsers()
    {
        return this.buffer.size();
    }
    public void DelUserById(int id)
    {
        int index=0;
        for(User u:this.buffer)
        {
        if(u.getId() == id)
        {
            this.buffer.remove(index);
        }
                index++;
            }     
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
            String query = "DELETE FROM POST WHERE receiver = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
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
            conn.rollback();
                }
            catch (SQLException sqle2)
            {
                e.printStackTrace();
                value= false;
            }
        finally{
        conn.setAutoCommit(true);
    }

   }
        return value;
 }
}
