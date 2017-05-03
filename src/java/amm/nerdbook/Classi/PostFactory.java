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

    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }
    
    private ArrayList<Post> postList = new ArrayList<>();
    public PostFactory()
    {
     MakeUser mu = new MakeUser();
     Post p1 = new Post(1,mu.getUserById(1),"Questo è un post di prova","01/05/2017-22:00",1);
     Attached att1 = new Attached(1,"img/mela.jpg");
     Post p2 = new Post(2,mu.getUserById(2),"","02/03/2017-11:00",att1,2);
     Attached att2 = new Attached(0,"https://it.wikipedia.org/wiki/Onda_gravitazionale");
     Post p3 = new Post(3,mu.getUserById(3),"Avevo Ragione","01/06/2017-20:00",att2,3);
     Post p4 = new Post(4,mu.getUserById(1),"Prova","02/05/2017-241242",2);
     Post p5 = new Post(5,mu.getUserById(2),"Prova","03/10/2016-241242",1);
     this.postList.add(p1);
     this.postList.add(p2);
     this.postList.add(p3);
     this.postList.add(p4);
     this.postList.add(p5);
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
