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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luigi Serreli
 */

public class PostFactory {

     //Pattern Design Singleton
    private static PostFactory singleton;
    private String connectionString;
    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }
    
    private ArrayList<Post> postList = new ArrayList<>();
    public PostFactory()
    {}
    public void setConnectionString(String s){
	this.connectionString = s;
}
public String getConnectionString(){
	return this.connectionString;
}
    public Post getOnePostByid(int id)
            
    {
        for(Post p: this.getPostList())
        {
            if(p.getId()==id)
                return p;
        }
        return null;
    }
    public ArrayList<Post> getPostById(int id) //IN ID CI VA L'ID DELL'UTENTE ASSOCIATO ALLA BACHECA. NON MOSTRA
    {//IL POST DELL'UTENTE MA I POST DELLA BACHECA ASSOCIATA ALL'ID
        ArrayList<Post> post_list = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            String query = "SELECT * FROM Post "+
                            "WHERE Receiver = ? ORDER BY data DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            while(result.next())
            {
                int id_p = result.getInt("id");
                UserFactory u = UserFactory.getInstance();
                String query2 = "SELECT * FROM Attached WHERE id_p = ?";
                PreparedStatement stmt2 = conn.prepareStatement(query2);
                stmt2.setInt(1, id_p);
                ResultSet result_att = stmt2.executeQuery();
                ArrayList<Attached> att_List = new ArrayList<>();
                while(result_att.next())
                {
                Attached currentAtt = new Attached(result_att.getInt("type"),result_att.getString("path"));
                att_List.add(currentAtt);
                }
                Post currentPost = new Post(id_p,u.getUserById(result.getInt("sender")),result.getString("content"),result.getString("data"),att_List,result.getInt("receiver"));
                post_list.add(currentPost);
                stmt2.close();
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return post_list;
    }
    public ArrayList<Post> getPostListByGroup(int id) //Come parametro c'è l'id del gruppo
    {
        ArrayList<Post> posts_list = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            String query = "SELECT * FROM Post "+
                            "WHERE id_g = ? ORDER BY data DESC";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet result = stmt.executeQuery();
            while(result.next())
            {
                int id_p = result.getInt("id");
                UserFactory u = UserFactory.getInstance();
                String query2 = "SELECT * FROM Attached WHERE id_p = ?";
                PreparedStatement stmt2 = conn.prepareStatement(query2);
                stmt2.setInt(1, id_p);
                ResultSet result_att = stmt2.executeQuery();
                ArrayList<Attached> att_List = new ArrayList<>();
                while(result_att.next())
                {
                Attached currentAtt = new Attached(result_att.getInt("type"),result_att.getString("path"));
                att_List.add(currentAtt);
                }
                Post currentPost = new Post(id_p,u.getUserById(result.getInt("sender")),result.getString("content"),result.getString("data"),att_List,result.getInt("id_g"));
                posts_list.add(currentPost);
                stmt2.close();
            }
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return posts_list;
    }
    public void InsertData_bacheca(Post post)
    {
        try{
            Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            String query = "INSERT INTO Post (id, content,sender,receiver,data) "
                    + "values (default, ? , ? , ? , ? )";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, post.getContent());
            stmt.setInt(2, post.getId());
            stmt.setInt(3, post.getId_user_bacheca());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getData()));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void InsertData_groups(Post post)
    {
        try{
            Connection conn = DriverManager.getConnection(this.getConnectionString(),"root","12345");
            String query = "INSERT INTO Post (id, content,sender,id_g,data) "
                    + "values (default, ? , ? , ? , ? )";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, post.getContent());
            stmt.setInt(2, post.getId());
            stmt.setInt(3, post.getGroupId());
            stmt.setTimestamp(4, Timestamp.valueOf(post.getData()));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void removePostsById(int id)
    {//Rimuove i post dell'utente dalla sua bacheca.Se l'operazione va a buon fine restituisce true
        
        
    }
    
    /**
     * @return the postList
     */
    public ArrayList<Post> getPostList() {
        return postList;
    }

    /**
     * @param postList the postList to set
     */
    public void setPostList(ArrayList<Post> postList) {
        this.postList = postList;
    }
    public void addPost(Post p)
    {
        this.postList.add(p);
    }
}
