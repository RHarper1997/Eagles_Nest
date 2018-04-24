/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author regin
 */
public class UserProfile implements Serializable {
    private String userID;
    private ArrayList <ItemRating> ratings;
    
    public UserProfile(){
        userID = "";
        ratings = new ArrayList<>();
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    
    
    
    public void addItem(Item item, String rating, boolean ownIt){
        ItemRating itemRating = new ItemRating();
        itemRating.setItem(item);
        itemRating.setRating(rating);
        itemRating.setOwnIt(ownIt);
        for(int i = 0; i < ratings.size(); i++){
            if(ratings.get(i).getItem().getItemCode().equals(item.getItemCode())){
                ratings.set(i, itemRating);
                return;
            }
        }
        ratings.add(itemRating);
        
}
    
    public void removeItem(Item item){
        for(int i = 0; i < ratings.size(); i++){
            if (ratings.get(i).getItem().getItemCode().equals(item.getItemCode())){
                ratings.remove(i);
            }
        }
    }
    
    public ArrayList<ItemRating> getItems(){
        return ratings;
    }
    
    public void emptyProfile(){
        ratings.clear();
    }
}
