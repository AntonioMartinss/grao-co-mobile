package com.example.graoco;

public class Coffe {
    int id;
    String name;
    int value;
    String description;
    int quantity;
    int categories_id;
    String category_name;
    String path;
    private boolean success;
    private String message;

    public Coffe(int id, String name, int value, String description, int quantity, int categories_id, String category_name, String path, boolean success, String message) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.description = description;
        this.quantity = quantity;
        this.categories_id = categories_id;
        this.category_name = category_name;
        this.path = path;
        this.success = success;
        this.message = message;
    }
    public Coffe(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public String getCategory_name() {
        return category_name;
    }
    public String getPath() {
        return path;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
