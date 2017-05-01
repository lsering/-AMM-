/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

import java.util.ArrayList;

/**
 *
 * @author Luigi Serreli
 */
public class PostFactory {
    private ArrayList<Post> postList = new ArrayList<>();
    public PostFactory()
    {
     MakeUser mu = new MakeUser();
     Post p1 = new Post(1,mu.getUserById(1),"Questo è un post di prova");
     Attached att1 = new Attached(1,"img/mela.jpg");
     Post p2 = new Post(2,mu.getUserById(2),"",att1);
     Attached att2 = new Attached(0,"https://it.wikipedia.org/wiki/Onda_gravitazionale");
     Post p3 = new Post(3,mu.getUserById(3),"Avevo Ragione",att2);
     this.postList.add(p1);
     this.postList.add(p2);
     this.postList.add(p3);
             }
    public Post getPostByid(int id)
    {
        for(Post p: this.postList)
        {
            if(p.getId()==id)
                return p;
        }
        return null;
    }
    public ArrayList<Post> getPostbyUser(User u)
    {
        ArrayList<Post> p = new ArrayList<>();
        for(Post post:this.postList)
        {
            if(post.getId()==u.getId())
                p.add(post);
        }
        return p;
    }
    
}
