package com.example.webshopsport.model;

public class AccountStatusUpdateModel {
    private String username;
    private int status;
    
    @Override
    public String toString() {
        return "AccountStatusUpdateModel [username=" + username + ", status=" + status + "]";
    }
    public AccountStatusUpdateModel() {
    }
    public AccountStatusUpdateModel(String username, int status) {
        this.username = username;
        this.status = status;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
