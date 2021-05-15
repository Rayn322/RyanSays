package com.ryan.ryansays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameTimer {
    
    public static void startTimer() {
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), GameTimer::endActivity, 200);
        Game.isPlaying = true;
    }
    
    private static void endActivity() {
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "Time is up!");
        for (Player player : PlayerManager.hasCompletedObjective.keySet()) {
            if (PlayerManager.hasCompletedObjective.get(player)) {
                player.sendMessage(ChatColor.GREEN + "You successfully completed the activity!");
                PlayerManager.hasCompletedObjective.replace(player, false);
            } else {
                player.sendMessage(ChatColor.RED + "You didn't complete the activity!");
            }
        }
        Game.isPlaying = false;
    }
    
}