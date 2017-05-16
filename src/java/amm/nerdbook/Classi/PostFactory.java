/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

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
    public Post getPostByid(int id)
            
    {
        for(Post p: this.getPostList())
        {
            if(p.getId()==id)
                return p;
        }
        return null;
    }
    public List<Post> getPostById(int id) //IN ID CI VA L'ID DELL'UTENTE ASSOCIATO ALLA BACHECA. NON MOSTRA
    {//IL POST DELL'UTENTE MA I POST DELLA BACHECA ASSOCIATA ALL'ID
        List<Post> p = new ArrayList<>();
        for(Post post:this.getPostList())
        {
            if(post.getId_user_bacheca()==id)
                p.add(post);
        }
        return p;
    }
    public ArrayList<Post> getPostListByGroup(Group gr)
    {
        ArrayList<Post> posts = new ArrayList<>();
        for(Post p:this.getPostList())
        {
            if(p.getGroupId() == gr.getId())
                posts.add(p);
        }
        return posts;
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
