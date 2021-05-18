package com.ryan.ryansays.listener;

import com.ryan.ryansays.gameplay.Game;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEvents implements Listener {
    
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Game.hasStarted) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.RED + "A game of Ryan Says is being played, you have been set to spectator mode.");
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
    
    @EventHandler
    public static void preventBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}
