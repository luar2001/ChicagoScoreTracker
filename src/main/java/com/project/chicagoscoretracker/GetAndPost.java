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
import java.util.Objects;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

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
    List<Player> players = new ArrayList<>();
    List<Player> list = new ArrayList<>();
    private final OkHttpClient httpClient = new OkHttpClient();

    /**
     * connection for GET requests
     * @param type which json converter to use
     * @return list of players
     * @throws IOException thrown if connection was interrupted
     */
    private List<Player> connection(String type) throws IOException {
        // TODO: 29/05/2021 Realised after that this is the wort way of doing a get and post request and i will later replace it with OkHttp
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET"); //GET request
        connection.setConnectTimeout(5000); //5 sec
        connection.setReadTimeout(5000); //5 sec
        connection.setDoOutput(false);

        players.clear();

        if(connection.getResponseCode() > 299){
            reader =new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        while((line = reader.readLine()) != null){ //while there is still things to read
            response.append(line);
        }

        if(type.equals("player")){
            players.add(jsonToPlayer(response.toString()));

        } else if (type.equals("players")){
            players.addAll(jsonToPlayers(response.toString()));
        }else{
            System.out.println("\nSUPER ERROR! ");
        }
        System.out.println("Test: " + players);
        reader.close();
        return players;
    }

    /**
     * connection fo POST request using OkHttpClient
     * @param json json that will be pushed to the database
     * @throws IOException throws exception if connection was interrupted
     */
    private void connectionPost(String json) throws IOException {

        RequestBody body = RequestBody.create( //JSON BODY!
                json,
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/") //i have no clue what this is
                .post(body)
                .build();



        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(Objects.requireNonNull(response.body()).string());
        }
    }

    /**
     * Converts JSON to the class Player
     * @param json JSON to convert
     * @return a Player
     * @throws JsonProcessingException throws exception if the JSON had problems converting
     */
    private Player jsonToPlayer(String json) throws JsonProcessingException {
        Player player;
        ObjectMapper objectMapper = new ObjectMapper();
        player = objectMapper.readValue(json, Player.class);
        return player;
    }

    /**
     * Converts a JSON array to a list of the class Player
     * @param json JSON to convert
     * @return a list of the class Player
     */
    private List<Player> jsonToPlayers(String json) {
        list.clear();
        String[] temps = StringUtils.substringsBetween(json, "{", "}");

        System.out.println("\nTEMPS TEST: " + Arrays.toString(temps));

        try{
            for (String s: temps) {
                list.add(jsonToPlayer("{"+s+"}"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("\nLIST TEST: " + list);
        return list;
    }

    /**
     * Converts the class Player to JSON
     * @param player Player to convert
     * @return a String that represents a JSON object
     */
    private String playerToJson(Player player) {
        String json = "";
        ObjectMapper jsonMapper = new ObjectMapper();

        try{
            json = jsonMapper.writeValueAsString(player);

        } catch(Exception e){
           e.printStackTrace();
        }
        System.out.println("JSON TEST: " + json);

        return json;
    }

    /**
     * lists all players in the database
     * @return list of players
     */
    public List<Player> getPlayers(){
        List<Player>list = new ArrayList<>();
        players.clear(); //CLEARS THE List
        try{
            url = new URL("http://localhost:8080/player");

            list = connection("players");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTP connection!\n");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            connection.disconnect();
        }

        return list;
    }

    /**
     * gets player by its name
     * @param name name of the player
     * @return player with that name
     */
    public Player getPlayerByName(String name){
        List<Player>list = new ArrayList<>();
        try{
            url = new URL("http://localhost:8080//player/name?name="+name);
            list =connection("player");

        } catch (MalformedURLException e){
            System.out.println("\nERROR:3: Can't find players!\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("\nERROR:4: Bad HTTP connection!\n");
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return list.get(0) ;
    }

    /**
     * Saves a player to the database by using a POST request
     * @param player player to save
     */
    public void savePlayer(Player player){
            try{
                url = new URL("http://localhost:8080/player");
                connectionPost(playerToJson(player));
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    /**
     * deletes a player from the database
     * @param player player that will be deleted
     */
    public void deletePlayer(Player player){
        try{
            url = new URL("http://localhost:8080/player/delete");
            connectionPost(playerToJson(player));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
