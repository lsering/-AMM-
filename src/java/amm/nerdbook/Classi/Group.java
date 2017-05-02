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
public class Group {

    

    
    private int id;
    private String groupName;
    private String Description;
    private ArrayList<User> userList;
    private ArrayList<Post> posts;
    private String iconurl;
    public Group(int id,String name,String Desc,ArrayList<User> usL,String iconurl)
    {
        this.id = id;
        this.groupName = name;
        this.Description = Desc;
        this.userList = usL;
        this.posts = new ArrayList<>();
        this.iconurl = iconurl;
    }
    //SE NON CI SONO UTENTI NEL GRUPPO FACCIO UN OVERLOAD DEL METODO COSTRUTTORE 
    //PER NON METTERE COME PARAMETRO LA LISTA
      public Group(int id,String name,String Desc)
    {
        this.id = id;
        this.groupName = name;
        this.Description = Desc;
        this.userList = new ArrayList<>();
        this.posts = new ArrayList<>();
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
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the userList
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    /**
     * @return the posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * @param posts the posts to set
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * @return the iconurl
     */
    public String getIconurl() {
        return iconurl;
    }

    /**
     * @param iconurl the iconurl to set
     */
    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }
}
