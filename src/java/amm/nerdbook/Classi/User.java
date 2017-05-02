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
public class User {

    
   private int id;
   private String username;
   private String name;
   private String surname;
   private String frase;
   private String email;
   private String password;
   private String urlImmagineProfilo;
   private ArrayList<Integer> followed;
  public User(int id,String username,String name,String surname,String email,String password,String urlim,String frase)
  {
      this.id=id;
      this.username = username;
      this.name=name;
      this.surname = surname;
      this.password=password;
      this.email=email;
      this.urlImmagineProfilo = urlim;
      this.frase = frase;
  }
  public User(int id,String username,String name,String surname,String email,String password,String urlim)
  {
      this.id=id;
      this.username = username;
      this.name=name;
      this.surname = surname;
      this.password=password;
      this.email=email;
      this.urlImmagineProfilo = urlim;
      this.frase = null;
  }
    public void setUrlImmagineProfilo(String urlImmagineProfilo) {
        this.urlImmagineProfilo = urlImmagineProfilo;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the urlImmagineProfilo
     */
    public String getUrlImmagineProfilo() {
        return urlImmagineProfilo;
    }

    /**
     * @param urlImmagineProfilo the urlImmagineProfilo to set
     */
    /**
     * @return the frase
     */
    public String getFrase() {
        return frase;
    }

    /**
     * @param frase the frase to set
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }

    
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
   

}
