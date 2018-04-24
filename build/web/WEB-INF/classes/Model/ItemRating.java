/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author regin
 */
public class ItemRating implements Serializable {
    private Item item;
    private String rating;
    private boolean ownIt;
    
    public ItemRating(){
        item = null;
        rating = null;
        ownIt = false;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isOwnIt() {
        return ownIt;
    }

    public void setOwnIt(boolean ownIt) {
        this.ownIt = ownIt;
    }
    
    
}
