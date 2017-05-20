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
public class Post {
    private int id;
    private User user; //Mittente
    private String content;
    private String data;
    private ArrayList<Attached> att;
    private int GroupId;
    private int id_user_bacheca;
    public Post(int id,User user,String content,String data,ArrayList<Attached> a,int id_user)
    {
        
        this.id = id;
        this.user = user;
        this.content = content;
        this.data = data;
        this.att = a;
        this.id_user_bacheca = id_user;
    }
     public Post(int id,User user,String content,ArrayList<Attached> a,int id_g)
    {
        this.id = id;
        this.user = user;
        this.content = content;
        this.att = a;
        this.GroupId = id_g;
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
    public ArrayList<Attached> getAtt() {
        return att;
    }

    /**
     * @param att the att to set
     */
    public void setAtt(ArrayList<Attached> att) {
        this.att = att;
    }
       /**
     * @return the GroupId
     */
    public int getGroupId() {
        return GroupId;
    }

    /**
     * @param GroupId the GroupId to set
     */
    public void setGroupId(int GroupId) {
        this.GroupId = GroupId;
    }
     /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
     /**
     * @return the id_user_bacheca
     */
    public int getId_user_bacheca() {
        return id_user_bacheca;
    }

    /**
     * @param id_user_bacheca the id_user_bacheca to set
     */
    public void setId_user_bacheca(int id_user_bacheca) {
        this.id_user_bacheca = id_user_bacheca;
    }

}
