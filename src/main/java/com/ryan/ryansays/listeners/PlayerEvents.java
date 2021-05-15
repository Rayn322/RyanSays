package com.ryan.ryansays.listeners;

import com.ryan.ryansays.Game;
import com.ryan.ryansays.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {
    
    @EventHandler
    public static void removePlayerOnLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerManager.hasCompletedObjective.remove(player);
    }
    
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Game.gameSpawn = new Location(player.getWorld(), 0.5, 31, 0.5);
        Game.setSpawn = true;
        if (Game.isPlaying) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.RED + "A game of Ryan Says is being played, you have been put in spectator mode.");
        }
    }
    
    @EventHandler
    public static void returnToPlatform(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() < 20) player.teleport(Game.gameSpawn);
    }
    
    @EventHandler
    public static void negateDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}
