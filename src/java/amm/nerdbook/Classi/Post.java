/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nerdbook.Classi;

/**
 *
 * @author Luigi Serreli
 */
public class Post {

    private int id;
    private User user;
    private String content;
    private Attached att;
    public Post(int id,User user,String content,Attached a)
    {
        this.id = id;
        this.user = user;
        this.content = content;
        this.att = a;
    }
     public Post(int id,User user,String content)
    {
        this.id = id;
        this.user = user;
        this.content = content;
        this.att = null;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the att
     */
    public Attached getAtt() {
        return att;
    }

    /**
     * @param att the att to set
     */
    public void setAtt(Attached att) {
        this.att = att;
    }
}
