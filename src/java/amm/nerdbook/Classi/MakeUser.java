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
public class MakeUser {
    private ArrayList<User> buffer;
    public MakeUser()
    {
        this.buffer = new ArrayList<>();
        User user1 = new User(1,"Niky","Nikola","Tesla","nikola@gmail.com","abcde","img/Tesla.jpg");
        buffer.add(user1);
        User user2 = new User(2,"alb1","Albert","Einstein","AEinst@gmail.com","abcde","img/Einstein.jpg","E = M*C^2");
        buffer.add(user2);
        User user3 = new User(3,"Isy","Isaac","Newton","ISNEW@gmail.com","abcde","img/Newton.jpg","F=m * a");
        buffer.add(user3);
        
    }
    public User getUserById(int id)
    {
        for(User u:this.buffer)
            if(u.getId() == id)
                return u;
            
                return null; //IF this method returns null the user does not exists
    }
    public int getNumOfUsers()
    {
        return this.buffer.size();
    }
    public void DelUserById(int id)
    {
        int index=0;
        for(User u:this.buffer)
        {
        if(u.getId() == id)
        {
            this.buffer.remove(index);
        }
                index++;
            }     
    }
    public  int getidByUsernameAndPassword(String us,String psw)
    {
        //If this method returns -1 the user does not exists
        int id=-1;
        for(User o:this.buffer)
        {
         if(us.equals(o.getUsername()) && psw.equals(o.getPassword()) )
         {
             id = o.getId();
             break;
         }
        }
        return id;
    }
    public ArrayList<User> getUserList(int id)
    {
        ArrayList<User> arr = new ArrayList<>();
        //RESTITUISCE LA LISTA DI UTENTI ESCLUDENDO L'UTENTE CON ID PASSATO COME PARAMETRO
        for(User u:this.buffer)
            if(u.getId()!=id)
                arr.add(u);
           return arr;     
    }
    
}
