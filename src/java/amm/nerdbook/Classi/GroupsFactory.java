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
        for(Group g:this.GroupList)
            if(g.getId()==id)
                return g;
        return null;
    }
    public ArrayList<Group> getGroupByUser(User u)
    {
        //Restituisce una lista di gruppi a cui l utente passato è iscritto
        ArrayList<Group> g=new ArrayList<>();
        for(Group group:this.GroupList)
        {
            for(User user:group.getUserList())
            {
                if(user.getId()==u.getId())
                    g.add(group);
            }
        }
        return g;
    }

}
