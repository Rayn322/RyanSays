package com.ryan.ryansays.activity;

import com.ryan.ryansays.gameplay.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.Ticks;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
    
        for (Player player : Game.getPlayersPlaying()) {
            player.getInventory().clear();
        }
    }
    
    public void giveInstructions(String instructions) {
        Title.Times times = Title.Times.of(Ticks.duration(0), Ticks.duration(40), Ticks.duration(10));
        Title title = Title.title(Component.empty(), Component.text(ChatColor.GREEN + instructions), times);
        
        Game.world.showTitle(title);
    }
    
    public void onTaskComplete(Player player) {
        if (!Game.hasAttemptedTask(player) && Game.playerIsPlaying(player)) {
            Game.playerCompletedTask(player);
    
            player.getInventory().clear();
            player.sendMessage(Component.text(ChatColor.DARK_GREEN + "You've completed the task!"));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
    }
    
    public void onTaskFail(Player player) {
        if (!Game.hasAttemptedTask(player) && Game.playerIsPlaying(player)) {
            Game.playerFailedTask(player);
    
            player.getInventory().clear();
            player.sendMessage(Component.text(ChatColor.DARK_RED + "You've failed the task!"));
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        }
    }
}
