package com.ryan.ryansays.activities;

import com.ryan.ryansays.Game;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class Activity implements Listener {
    
    public boolean isPlaying;
    public ActivityType activityType;
    
    public void setup() {
        isPlaying = true;
    }
    
    public void cleanup() {
        isPlaying = false;
        Game.resetLists();
    }
    
    public void onTaskComplete(Player player) {
        if (Game.hasNotCompletedTask(player)) {
            Game.playerCompletedTask(player);
        }
        
        player.getInventory().clear();
        player.getServer().sendMessage(Component.text(ChatColor.GOLD + player.getName() + ChatColor.DARK_GREEN + " has completed the task!"));
    }
    
    public void onTaskFail(Player player) {
        if (Game.hasNotCompletedTask(player)) {
            Game.playerFailedTask(player);
        }
        
        player.getInventory().clear();
        player.getServer().sendMessage(Component.text(ChatColor.GOLD + player.getName() + ChatColor.DARK_RED + " has failed the task!"));
    }
}
