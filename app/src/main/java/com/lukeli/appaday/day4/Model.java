package com.lukeli.appaday.day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Model {
    private ArrayList<Player> players;
    private HashMap<String, Role> possibleRoles;
    private ArrayList<Role> availableRoles;

    public Model(){
        this.players = new ArrayList<Player>();
        possibleRoles = new HashMap<String, Role>();

        generatePossibleRoles();
    }

    private void generatePossibleRoles(){
        possibleRoles.put("merlin", new Role("Merlin", false));
        possibleRoles.put("vanilla_red_1", new Role("Vanilla Red", true));
        possibleRoles.put("vanilla_red_2", new Role("Vanilla Red", true));
        possibleRoles.put("vanilla_red_3", new Role("Vanilla Red", true));
        possibleRoles.put("mordred", new Role("Mordred", true));
        possibleRoles.put("morgana", new Role("Morgana", true));
        possibleRoles.put("assassin", new Role("Assassin", true));
        possibleRoles.put("percival", new Role("Percival", false));
        possibleRoles.put("vanilla_blue_1", new Role("Vanilla Blue", false));
        possibleRoles.put("vanilla_blue_2", new Role("Vanilla Blue", false));
        possibleRoles.put("vanilla_blue_3", new Role("Vanilla Blue", false));
        possibleRoles.put("vanilla_blue_4", new Role("Vanilla Blue", false));
        possibleRoles.put("vanilla_blue_5", new Role("Vanilla Blue", false));
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public Player getPlayerFromID(String id){
        for(Player p: players){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public Player getPlayerFromName(String name){
        for(Player p: players){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public int numPlayers(){
        return this.players.size();
    }

    public void assignRoles(){
        availableRoles = new ArrayList<Role>();
        availableRoles.add(possibleRoles.get("merlin"));
        availableRoles.add(possibleRoles.get("assassin"));
        availableRoles.add(possibleRoles.get("percival"));
        availableRoles.add(possibleRoles.get("vanilla_blue_1"));
        availableRoles.add(possibleRoles.get("morgana"));
        availableRoles.add(possibleRoles.get("mordred"));
        availableRoles.add(possibleRoles.get("vanilla_blue_2"));

        Collections.shuffle(availableRoles);
        for(int i = 0; i < this.players.size(); i++){
            this.players.get(i).setRole(availableRoles.get(i));
        }

        possibleRoles.get("merlin").setKnowledge(getKnowledgeString("merlin"), getKnowledgeIDs("merlin"));
        possibleRoles.get("assassin").setKnowledge(getKnowledgeString("assassin"), getKnowledgeIDs("assassin"));
        possibleRoles.get("percival").setKnowledge(getKnowledgeString("percival"), getKnowledgeIDs("percival"));
        possibleRoles.get("vanilla_blue_1").setKnowledge(getKnowledgeString("vanilla_blue_1"), getKnowledgeIDs("vanilla_blue_1"));
        possibleRoles.get("morgana").setKnowledge(getKnowledgeString("morgana"), getKnowledgeIDs("morgana"));
        possibleRoles.get("mordred").setKnowledge(getKnowledgeString("mordred"), getKnowledgeIDs("mordred"));
        possibleRoles.get("vanilla_blue_2").setKnowledge(getKnowledgeString("vanilla_blue_2"), getKnowledgeIDs("vanilla_blue_2"));

    }
    public boolean playerExists(String name){
        for(Player p: players){
            if(p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    private String getKnowledgeString(String role){
        String knowledge = "You are " + possibleRoles.get(role).getRoleName() + ". ";
        switch(role){
            case "merlin":
                String knownReds = "";
                for(Player p: players){
                    if(p.getRole().isRed() && p.getRole().getRoleName() != "Mordred"){
                        knownReds += p.getName() + ", ";
                    }
                }
                knownReds = knownReds.substring(0, knownReds.length() - 2);
                knowledge += "The reds are " + knownReds + ".";
                break;
            case "vanilla_blue_1":
                knowledge += "";
                break;
            case "vanilla_blue_2":
                knowledge += "";
                break;
            case "percival":
                String merlinOrMorgana = "";
                for(Player p: players){
                    if(p.getRole().getRoleName() == "Morgana" || p.getRole().getRoleName() == "Merlin"){
                        merlinOrMorgana += p.getName() + " and ";
                    }
                }
                merlinOrMorgana = merlinOrMorgana.substring(0, merlinOrMorgana.length() - 5);
                knowledge += merlinOrMorgana + " are either Merlin or Morgana.";
                break;
            default:
                String allReds = "";
                for(Player p: players){
                    if(p.getRole().isRed()){
                        allReds += p.getName() + ", ";
                    }
                }
                allReds = allReds.substring(0, allReds.length() - 2);
                knowledge += "The reds are " + allReds + ".";
                break;
        }
        knowledge += " Have fun!";
        return knowledge;
    }
    private ArrayList<String> getKnowledgeIDs(String role){
        ArrayList<String> possibleIDs = new ArrayList<String>();
        switch(role){
            case "merlin":
                String knownReds = "";
                for(Player p: players){
                    if(p.getRole().isRed() && p.getRole().getRoleName() != "Mordred"){
                        possibleIDs.add(p.getId());
                    }
                }
                break;
            case "vanilla_blue_1":
                break;
            case "vanilla_blue_2":
                break;
            case "percival":
                String merlinOrMorgana = "";
                for(Player p: players){
                    if(p.getRole().getRoleName() == "Morgana" || p.getRole().getRoleName() == "Merlin"){
                        possibleIDs.add(p.getId());
                    }
                }
                break;
            default:
                String allReds = "";
                for(Player p: players){
                    if(p.getRole().isRed()){
                        possibleIDs.add(p.getId());
                    }
                }
                break;
        }
        return possibleIDs;
    }
}
