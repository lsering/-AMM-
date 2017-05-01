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

   
   private ArrayList<Group> GroupList = new ArrayList<>();
    public GroupsFactory(){
        Group g1 = new Group(1,"Gli sfigati","Descrizione di prova");
        Group g2 = new Group(2,"Gruppo di studio","Per veri nerd");
        this.GroupList.add(g1);
        this.GroupList.add(g2);
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
