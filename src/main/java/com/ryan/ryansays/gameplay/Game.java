package com.ryan.ryansays.gameplay;

import com.ryan.ryansays.activity.Activity;
import com.ryan.ryansays.activity.ActivityRandomizer;
import com.ryan.ryansays.activity.ActivityType;
import com.ryan.ryansays.util.NameUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    
    public static boolean hasStarted = false;
    public static World world;
    public static Location gameSpawn;
    private static final ArrayList<Player> playersPlaying = new ArrayList<>();
    private static final ArrayList<Player> playersCompletedTask = new ArrayList<>();
    private static final ArrayList<Player> playersFailedTask = new ArrayList<>();
    private static ArrayList<ActivityType> activityList;
    
    public static void startGame(Player player) {
        if (hasStarted) {
            player.sendMessage("A game has already started!");
        }
        
        world = player.getWorld();
        activityList = ActivityRandomizer.getActivityList();
        
        for (Player playerInWorld : world.getPlayers()) {
            playerInWorld.teleport(gameSpawn);
            playersPlaying.add(playerInWorld);
        }
        
        startNextActivity();
    }
    
    public static void startNextActivity() {
        if (activityList.isEmpty()) {
            // TODO: end the game
            world.sendMessage(Component.text("Game ended"));
            return;
        }
        
        ActivityType activityType = activityList.get(0);
        Activity nextActivity = ActivityRandomizer.getActivity(activityType);
        activityList.remove(activityType);
        
        beginActivity(nextActivity);
    }
    
    public static void beginActivity(Activity activity) {
        activity.setup();
        GameTimer.scheduleActivityEnd(activity);
    }
    
    public static void endActivity(Activity activity) {
        String completed;
        String failed = "";
        
        if (playersCompletedTask.isEmpty()) {
            completed = ChatColor.DARK_GREEN + "Nobody completed the task!";
        } else if (playersCompletedTask.size() > 1){
            completed = NameUtils.commaSeparate(playersCompletedTask, ChatColor.DARK_GREEN) + " have completed the task";
        } else {
            completed = NameUtils.getColoredName(playersCompletedTask.get(0), ChatColor.DARK_GREEN) + " has completed the task!";
        }
        
        if (playersFailedTask.size() > 1) {
            failed = NameUtils.commaSeparate(playersFailedTask, ChatColor.DARK_RED) + " have failed the task";
        } else if (playersFailedTask.size() == 1) {
            failed = NameUtils.getColoredName(playersFailedTask.get(0), ChatColor.DARK_RED) + " has failed the task!";
        }
    
        Game.world.sendMessage(Component.text(completed));
        if (!failed.isBlank()) Game.world.sendMessage(Component.text(failed));
    
        activity.cleanup();
        resetLists();
        GameTimer.scheduleNextActivityBegin();
    }
    
    public static boolean hasAttemptedTask(Player player) {
        return playersCompletedTask.contains(player) || playersFailedTask.contains(player);
    }
    
    public static boolean playerIsPlaying(Player player) {
        return playersPlaying.contains(player);
    }
    
    public static void playerCompletedTask(Player player) {
        playersCompletedTask.add(player);
    }
    
    public static void playerFailedTask(Player player) {
        playersFailedTask.add(player);
    }
    
    public static void resetLists() {
        playersCompletedTask.clear();
        playersFailedTask.clear();
    }
    
    public static ArrayList<Player> getPlayersPlaying() {
        return playersPlaying;
    }
    
    public static void setGameSpawn(World newWorld) {
        world = newWorld;
        gameSpawn = new Location(newWorld, 0.5, 31, 0.5);
    }
}
