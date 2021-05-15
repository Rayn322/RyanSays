package com.ryan.ryansays;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    
    public static boolean hasStarted = false;
    public static World world;
    private static final ArrayList<Player> playersPlaying = new ArrayList<>();
    private static final ArrayList<Player> playersNotFinished = new ArrayList<>();
    private static final ArrayList<Player> playersCompletedTask = new ArrayList<>();
    private static final ArrayList<Player> playersFailedTask = new ArrayList<>();
    
    public static void startGame(Player player) {
        world = player.getWorld();
    }
    
    public static boolean hasNotCompletedTask(Player player) {
        return Game.playersPlaying.contains(player) || Game.playersNotFinished.contains(player);
    }
    
    public static void playerCompletedTask(Player player) {
        playersNotFinished.remove(player);
        playersCompletedTask.add(player);
    }
    
    public static void playerFailedTask(Player player) {
        playersNotFinished.remove(player);
        playersFailedTask.add(player);
    }
    
    public static void resetLists() {
        playersCompletedTask.clear();
        playersFailedTask.clear();
    }
    
    public static ArrayList<Player> getPlayersPlaying() {
        return playersPlaying;
    }
}
