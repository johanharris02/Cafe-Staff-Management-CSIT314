package PROJECTS.control_layer;

import PROJECTS.boundary_layer.userWorkSpace;

public class User {

    private String account;
    private String password;
    private String name;
    private String profile;

    public User() {
    }

    public User(String account, String password, String name, String profile) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.profile = profile;
    }

    public User(String description) {
        this.profile = description;
    }
    public User(String name, String profile) {
        this.name = name;
        this.profile = profile;
    }

    public void UserLogin(){
        new userWorkSpace(name,profile);
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
