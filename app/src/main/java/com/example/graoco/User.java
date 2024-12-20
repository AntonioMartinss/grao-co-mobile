package com.example.graoco;

public class User {
    int id;
    String name;
    String email;
    int password;
    String photo;
    int usersCategories_id;

    public User(int id, String name, String email, int password, String photo, int usersCategories_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.usersCategories_id = usersCategories_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getUsersCategories_id() {
        return usersCategories_id;
    }

    public void setUsersCategories_id(int usersCategories_id) {
        this.usersCategories_id = usersCategories_id;
    }
}
