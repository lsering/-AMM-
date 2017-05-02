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
public class Attached {

    private int type;
    private String content;
    //Se l'allegato non è presente type=-1
    //Se l'allegato ha type=0 è un link
    //Se l'allegato ha type=1 è un immagine
    public Attached(int type,String content)
    {
        this.type = type;
        this.content = content;
    }
    public Attached(int type)
    {
        this.type = type;
        this.content = "";
    }
    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
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
}
