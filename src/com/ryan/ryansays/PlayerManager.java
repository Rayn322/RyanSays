package com.ryan.ryansays;

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

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager implements Listener {
    
    public static HashMap<UUID, Boolean> hasCompletedObjective = new HashMap<UUID, Boolean>();
    
    public static void addPlayer(Player player) {
        if (!hasCompletedObjective.containsKey(player.getUniqueId())) {
            hasCompletedObjective.put(player.getUniqueId(), false);
        }
    }
    
    @EventHandler
    public static void removePlayerOnLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        hasCompletedObjective.remove(player.getUniqueId());
    }
    
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.gameSpawn = new Location(player.getWorld(), 0.5, 31, 0.5);
        Main.setSpawn = true;
        if (Game.isPlaying) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.RED + "A game of Ryan Says is being played, you have been set to spectator mode.");
        }
    }
    
    @EventHandler
    public static void returnToPlatform(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() < 20) player.teleport(Main.gameSpawn);
    }
    
    @EventHandler
    public static void negateDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
}