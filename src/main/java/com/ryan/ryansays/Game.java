package com.ryan.ryansays;

import com.ryan.ryansays.activities.ActivityType;
import com.ryan.ryansays.activities.DrinkPotions;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class Game {
    
    private static final Random random = new Random();
    public static boolean isPlaying = false;
    public static Location gameSpawn;
    public static boolean setSpawn = false;
    public static World world;
    
    public static void startGame(Player player) {
        player.getServer().sendMessage(Component.text(ChatColor.YELLOW + player.getName() + " has started a game of Ryan Says!"));
        
        for (int i = 0; i < player.getWorld().getPlayers().size(); i++) {
            Player playerI = player.getWorld().getPlayers().get(i);
            playerI.teleport(gameSpawn);
            PlayerManager.hasCompletedObjective.clear();
            PlayerManager.addPlayer(playerI);
        }
        
        startActivity();
    }
    
    public static void stopGame() {
        getServer().sendMessage(Component.text(ChatColor.RED + "The game has ended!"));
        Game.isPlaying = false;
    }
    
    public static void startActivity() {
        switch (randomActivity()) {
            case DRINKPOTION:
                new DrinkPotions().setup();
                break;
            default:
                Bukkit.getServer().sendMessage(Component.text(ChatColor.RED + "I haven't added the activity that was rolled. Run the command again."));
        }
    }
    
    private static ActivityType randomActivity() {
        return ActivityType.values()[random.nextInt(ActivityType.values().length)];
    }
    
}
