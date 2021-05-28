package com.project.chicagoscoretracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chicagoscoretracker.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Created by Lukas Aronsson
 * Date: 26/05/2021
 * Time: 14:36
 * Project: ChicagoScoreTracker
 * Copyright: MIT
 **/
public class GetAndPost {
    URL url;
    HttpURLConnection connection;
    BufferedReader reader;
    String line;
    StringBuffer response = new StringBuffer();
    String protocol;
    List<Player> players = new ArrayList<>();
    List<Player> list = new ArrayList<>();


    private List<Player> connection(String type) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(protocol); //GET request
        connection.setConnectTimeout(5000); //5 sec
        connection.setReadTimeout(5000); //5 sec

        players.clear(); //CLEARS THE List

        if(connection.getResponseCode() > 299){
            reader =new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        while((line = reader.readLine()) != null){ //while there is still things to read
            response.append(line);
        }

        if(type.equals("player")){
            players.add(JsonToPlayer(response.toString()));

        } else if (type.equals("players")){
            players.addAll(JsonToPlayers(response.toString()));
        }else{
            System.out.println("\nSUPER ERROR! ");
        }
        System.out.println("Test: " + players);
        reader.close();
        return players;
    }



    private Player JsonToPlayer(String json) throws JsonProcessingException {
        Player player;
        ObjectMapper objectMapper = new ObjectMapper();
        player = objectMapper.readValue(json, Player.class);
        return player;
    }

    private List<Player> JsonToPlayers(String json) throws JsonProcessingException {
        list.clear();
        String[] temps = StringUtils.substringsBetween(json, "{", "}");

        System.out.println("\nTEMPS TEST: " + Arrays.toString(temps));

        for (String s: temps) {
         list.add(JsonToPlayer("{"+s+"}"));
        }

        System.out.println("\nLIST TEST: " + list);
        return list;
    }

    private JSONObject PlayerToJson(Player player){

        return new JSONObject();
    }



    /**
     * lists all players in the database
     * @return list of players
     */
    public List<Player> getPlayers(){
        List<Player>list = new ArrayList<>();

        try{
            url = new URL("http://localhost:8080/player?");
            protocol = "GET";

            list = connection("players");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTPS connection!\n");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            connection.disconnect();
        }

        return list;
    }

    public Player getPlayerByName(String name){
        List<Player>list = new ArrayList<>();
        try{
            url = new URL("http://localhost:8080//player/name?name="+name);
            protocol = "GET";
            list =connection("player");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTPS connection!\n");
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return list.get(0) ;
    }



    public Player getPlayerById(int id){
        List<Player>list = new ArrayList<>();
        try{
            url = new URL("http://localhost:8080//player/name?id="+id);
            protocol = "GET";
            list =connection("player");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTPS connection!\n");
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return list.get(0);
    }

    public List<Player> getPlayerByWins(){
        List<Player>list = new ArrayList<>();

        try{
            url = new URL("http://localhost:8080/player/wins");
            protocol = "GET";
            list =  connection("players");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTPS connection!\n");
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return list;
    }

    public void savePlayer(Player player){
            try{
                url = new URL("http://localhost:8080/player");
                protocol = "POST";
                connection("POST");

            } catch (MalformedURLException e){
                System.out.println("\nERROR:3: Can't find players!\n");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("\nERROR:4: Bad HTTPS connection!\n");
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
    }

    public void deletePlayer(){
        try{
            url = new URL("http://localhost:8080/player/delete");
            protocol = "POST";
            connection("post");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTPS connection!\n");
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

}
