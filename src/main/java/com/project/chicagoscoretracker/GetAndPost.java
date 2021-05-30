package com.project.chicagoscoretracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.project.chicagoscoretracker.model.Player;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private final OkHttpClient httpClient = new OkHttpClient();
    /**
     * connection for GET requests
     * @param type which json converter to use
     * @return list of players
     * @throws IOException thrown if connection was interrupted
     */
    private List<Player> connection(String type) throws IOException {
        // TODO: 29/05/2021 Realised after that this is the wort way of doing a get and post request and i will later replace it with OkHttp get request
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET"); //GET request
        connection.setConnectTimeout(5000); //5 sec
        connection.setReadTimeout(5000); //5 sec
        connection.setDoOutput(false);

        List<Player> players = new ArrayList<>();

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
        reader.close();
        return players;
    }

    /**
     * connection fo POST request using OkHttpClient
     * @param json json that will be pushed to the database
     * @throws IOException throws exception if connection was interrupted
     * @link https://square.github.io/okhttp/4.x/okhttp/okhttp3/
     */
    private void connectionPost(String json) throws IOException {

       RequestBody body = RequestBody.create(MediaType.parse("application/json"),json); // TODO: 29/05/2021 figure out what to replace this with, it works for now !

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(Objects.requireNonNull(response.body()).string());
        }
    }


    private void connectionDelete() throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
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
     */
    private Player jsonToPlayer(String json) {
        Player player = new Player();
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            player = gson.fromJson(reader, Player.class);

        }catch(Exception e ){
            e.printStackTrace();
        }

        return player;
    }

    /**
     * Converts a JSON array to a list of the class Player
     * @param json JSON to convert
     * @return a list of the class Player
     */
    private List<Player> jsonToPlayers(String json) {
        List<Player> list = new ArrayList<>();
        String[] temps = StringUtils.substringsBetween(json, "{", "}");

        try{
            for (String s: temps) {
                list.add(jsonToPlayer("{"+s+"}"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Converts the class Player to JSON
     * @param player Player to convert
     * @return a String that represents a JSON object
     */
    private String playerToJson(Player player) {
        return new Gson().toJson(player);
    }

    /**
     * lists all players in the database
     * @return list of players
     */
    public List<Player> getPlayers(){
        List<Player>list1 = new ArrayList<>();
        try{
            url = new URL("http://localhost:8080/player");

            list1 = connection("players");

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

        return list1;
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
     * @param id id of player that will be deleted
     */
    public void deletePlayer(Long id){
        try{
            url = new URL("http://localhost:8080/deleteplayer/"+id);
            connectionDelete();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
