package com.example.dentalcart.Pojo;

public class ItemModel2 {
    private String category ,description , discount , favorite , id , photo , name ;
    private String rating ;
    private String price ;

    public ItemModel2(String category, String description, String discount, String favorite, String id, String name, String photo, String price, String rating) {
        this.category = category;
        this.description = description;
        this.discount = discount;
        this.favorite = favorite;
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.rating = rating;
    }

    public ItemModel2(String category, String description, String discount, String favorite, String id, String name,  String price, String rating) {
        this.category = category;
        this.description = description;
        this.discount = discount;
        this.favorite = favorite;
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
