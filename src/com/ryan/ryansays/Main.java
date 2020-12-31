package com.ryan.ryansays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public final Game game = new Game(this);
    
    @Override
    public void onEnable() {
        getCommand("ryansays").setTabCompleter(new TabAutocomplete());
        getServer().getPluginManager().registerEvents(game, this);
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ryansays") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("start")) {
                startGame(player);
            } else if (args[0].equalsIgnoreCase("stop")) {
                stopGame();
            }
        } else {
            sender.sendMessage("Sorry! Console can't play!");
        }
        return true;
    }
    
    private void startGame(Player player) {
        player.getServer().broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " has started a game of Ryan Says!");
        Location gameSpawn = new Location(player.getWorld(), 0.5, 31, 0.5);
        
        for (int i = 0; i < player.getWorld().getPlayers().size(); i++) {
            Player playerI = player.getWorld().getPlayers().get(i);
            playerI.teleport(gameSpawn);
        }
        
        game.startActivity(player.getWorld(), player.getServer());
    }
    
    private void stopGame() {
        getServer().broadcastMessage(ChatColor.RED + "The game has ended!");
    }
}
