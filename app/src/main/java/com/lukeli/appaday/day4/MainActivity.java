package com.lukeli.appaday.day4;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {
    private Model m;
    private HashMap<String, TextView> userIDTextMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRoles();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void generateRoles(){
        m = new Model();
        userIDTextMap = new HashMap<String, TextView>();
        m.addPlayer(new Player("You", "youID"));
        userIDTextMap.put("youID", (TextView) findViewById(R.id.player1_name));
        userIDTextMap.get("youID").setText("You");
        String random_name = getRandomPlayerName();
        m.addPlayer(new Player(random_name, "player_2"));
        userIDTextMap.put("player_2", (TextView) findViewById(R.id.player2_name));
        userIDTextMap.get("player_2").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_3"));
        userIDTextMap.put("player_3", (TextView) findViewById(R.id.player3_name));
        userIDTextMap.get("player_3").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_4"));
        userIDTextMap.put("player_4", (TextView) findViewById(R.id.player4_name));
        userIDTextMap.get("player_4").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_5"));
        userIDTextMap.put("player_5", (TextView) findViewById(R.id.player5_name));
        userIDTextMap.get("player_5").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_6"));
        userIDTextMap.put("player_6", (TextView) findViewById(R.id.player6_name));
        userIDTextMap.get("player_6").setText(random_name);

        random_name = getRandomPlayerName();
        while(m.playerExists(random_name)){
            random_name = getRandomPlayerName();
        }
        m.addPlayer(new Player(random_name, "player_7"));
        userIDTextMap.put("player_7", (TextView) findViewById(R.id.player7_name));
        userIDTextMap.get("player_7").setText(random_name);

        m.assignRoles();

    }

    private String getRandomPlayerName(){
        String[] possibleNames = {"Joel_N", "Deanna", "Lil_Lee_N", "Al_Lane", "Kay_Win", "Ping_Lee", "Alexa", "Brianna", "Steefanie", "Janeis"};
        Random rand = new Random();
        int rand_index =  rand.nextInt(possibleNames.length);
        return possibleNames[rand_index];
        
    }

    public void onClickRegenerateRoles(View view) {
        generateRoles();
    }

    public void onShowRolesClick(View view) {
        Player p = m.getPlayerFromID("youID");
        String knowledge = p.getRole().getInfo();
        final ArrayList<String> playerIDs = p.getRole().getRelevantPlayers();
        for(String id : playerIDs){
            userIDTextMap.get(id).setBackgroundColor (Color.RED);
        }
        /**
        Toast.makeText(MainActivity.this, knowledge, Toast.LENGTH_SHORT).show();
            for (String id : playerIDs) {
                userIDTextMap.get(id).setBackgroundColor(Color.TRANSPARENT);
            }**/
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Your Info");
        builder.setMessage(knowledge);
        builder.setCancelable(true);

        final AlertDialog dlg = builder.create();

        dlg.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                makeNotRed(playerIDs);

            }
        }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
    }
    private void makeNotRed(final ArrayList<String> playerIDs){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(String id : playerIDs){
                    userIDTextMap.get(id).setBackgroundColor (Color.TRANSPARENT);
                }
//stuff that updates ui

            }
        });
    }
}
