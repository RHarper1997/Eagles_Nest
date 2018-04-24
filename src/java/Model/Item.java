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
public class Item implements Serializable{
    private String itemCode;
    private String name;
    private String category;
    private String description;
    private String rating;
    private String imageURL;
    
    public Item(){
        itemCode = "";
        name = "";
        category = "";
        description = "";
        rating = null;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public void setImageURL(String url){
        imageURL = url;
    }
    public String getImageURL(){
        return imageURL;
    }
}
