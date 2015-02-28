package com.lukeli.appaday.day4;

import java.util.ArrayList;

public class Role {
    private String roleName;
    private Knowledge knowledge;
    private boolean isRed;

    public Role(String roleName, boolean isRed){
        this.roleName = roleName;
        this.isRed = isRed;
    }

    public boolean isRed(){
        return this.isRed;
    }

    public void setKnowledge(String info, ArrayList<String> relevantPlayers){
        knowledge = new Knowledge(info, relevantPlayers);
    }

    public ArrayList<String> getRelevantPlayers(){
        return knowledge.relevantPlayers;
    }

    public String getInfo(){
        return knowledge.info;
    }

    public String getRoleName(){
        return roleName;
    }

    protected class Knowledge{
        protected String info;
        protected ArrayList<String> relevantPlayers;

        protected Knowledge(String info, ArrayList<String> relevantPlayers){
            this.info = info;
            this.relevantPlayers = relevantPlayers;
        }
    }
}
