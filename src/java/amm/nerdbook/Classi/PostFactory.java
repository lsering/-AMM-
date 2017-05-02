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
    private ArrayList<Post> postList = new ArrayList<>();
    public PostFactory()
    {
     MakeUser mu = new MakeUser();
     Post p1 = new Post(1,mu.getUserById(1),"Questo è un post di prova","01/05/2017-22:00");
     Attached att1 = new Attached(1,"img/mela.jpg");
     Post p2 = new Post(2,mu.getUserById(2),"","02/03/2017-11:00",att1);
     Attached att2 = new Attached(0,"https://it.wikipedia.org/wiki/Onda_gravitazionale");
     Post p3 = new Post(3,mu.getUserById(3),"Avevo Ragione","01/06/2017-20:00",att2);
     Post p4 = new Post(4,mu.getUserById(1),"Prova","02/05/2017-241242");
     Post p5 = new Post(5,mu.getUserById(2),"Prova","03/10/2016-241242");
     this.postList.add(p1);
     this.postList.add(p2);
     this.postList.add(p3);
     this.postList.add(p4);
     this.postList.add(p5);
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
    public List<Post> getPostbyUser(User u)
    {
        List<Post> p = new ArrayList<>();
        for(Post post:this.postList)
        {
            if(post.getUser().getId()==u.getId())
                p.add(post);
        }
        return p;
    }
    public ArrayList<Post> getPostListByGroup(Group gr)
    {
        ArrayList<Post> posts = new ArrayList<>();
        for(Post p:this.postList)
        {
            if(p.getGroupId() == gr.getId())
                posts.add(p);
        }
        return posts;
    }
    
}
