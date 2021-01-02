package com.ryan.ryansays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GameTimer {
    
    private static Main plugin;
    
    public GameTimer(Main pl) {
        plugin = pl;
    }
    
    public static void startTimer() {
        Bukkit.getScheduler().runTaskLater(plugin, GameTimer::endActivity, 200);
        Game.isPlaying = true;
    }
    
    private static void endActivity() {
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "Time is up!");
        for (UUID uuid : PlayerManager.hasCompletedObjective.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (PlayerManager.hasCompletedObjective.get(uuid)) {
                player.sendMessage(ChatColor.GREEN + "You successfully completed the activity!");
                PlayerManager.hasCompletedObjective.replace(uuid, false);
            } else {
                player.sendMessage(ChatColor.RED + "You didn't complete the activity!");
            }
        }
    }
    
}