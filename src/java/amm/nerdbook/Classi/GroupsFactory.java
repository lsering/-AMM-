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

public class GroupsFactory {
    private static GroupsFactory singleton;
    public static GroupsFactory getInstance() {
        if (singleton == null) {
            singleton = new GroupsFactory();
        }
        return singleton;
    }
   private String connectionString;
   private ArrayList<Group> GroupList = new ArrayList<>();
    public GroupsFactory(){}
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
     /**
     * @return the GroupList
     */
    public ArrayList<Group> getGroupList() {
        return GroupList;
    }
    /**
     * @param GroupList the GroupList to set
     */
    public void setGroupList(ArrayList<Group> GroupList) {
        this.GroupList = GroupList;
    }
    public Group getGroupById(int id)
    {
        try{
        //Connessione al db
            Connection conn = DriverManager.getConnection(this.connectionString,"root","12345");
            String query = "SELECT * FROM Gruppo "
                            + "WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery(); //Eseguo l'interrogazione
            if(result.next()){
                Group currentGroup = new Group(result.getInt("id"),result.getString("name"),
                result.getString("description"));
                stmt.close();
                conn.close();
                return currentGroup;
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException e){
        e.printStackTrace();}
                return null; //IF this method returns null the user does not exists
    }
    public ArrayList<Group> getGroupByUser(User u)
    {
        //Restituisce una lista di gruppi a cui l utente passato è iscritto
        ArrayList<Group> g=new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(this.connectionString,"root","12345");
            String query = "SELECT * FROM Gruppo g,Appartiene a "
                    + "WHERE a.id_u = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,u.getId());
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                Group CurrentGroup = new Group(result.getInt("id"),result.getString("name"),result.getString("description"));
                g.add(CurrentGroup);
            }
        }
        catch(SQLException e)
        {
            e.getStackTrace();
        }
        return g;
    }

}
