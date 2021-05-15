package com.ryan.ryansays.activities;

import com.ryan.ryansays.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.Ticks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class Activity implements Listener {
    
    public boolean isPlaying;
    public ActivityType activityType;
    public String instructions = "This activity was not given instructions";
    
    public void setup() {
        isPlaying = true;
        giveInstructions(instructions);
    }
    
    public void cleanup() {
        isPlaying = false;
        Game.resetLists();
    
        for (Player player : Game.getPlayersPlaying()) {
            player.getInventory().clear();
        }
    }
    
    public void giveInstructions(String instructions) {
        Title.Times times = Title.Times.of(Ticks.duration(0), Ticks.duration(40), Ticks.duration(10));
        Title title = Title.title(Component.text(ChatColor.GREEN + instructions), Component.empty(), times);
        
        Game.world.showTitle(title);
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
