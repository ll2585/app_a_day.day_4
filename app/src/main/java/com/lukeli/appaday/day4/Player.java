package com.lukeli.appaday.day4;

public class Player {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return myRole;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role myRole) {
        this.myRole = myRole;
    }

    private String id;
    private String name;
    private Role myRole;

    public Player(String name, String id){
        setId(id);
        setName(name);
    }



}
