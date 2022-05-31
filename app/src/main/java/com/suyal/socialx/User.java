package com.suyal.socialx;

public class User {

    String image,name,email,password,phone,userid,lastMessage;

    public User(String image, String name, String email, String password, String phone, String userid, String lastMessage) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userid = userid;
        this.lastMessage = lastMessage;
    }

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
