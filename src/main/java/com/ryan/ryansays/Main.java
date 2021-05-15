package com.ryan.ryansays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public final Game game = new Game();
    public final DrinkPotions drinkPotions = new DrinkPotions(this);
    public final GameTimer gameTimer = new GameTimer(this);
    public static Location gameSpawn = null;
    public static boolean setSpawn = false;
    
    @Override
    public void onEnable() {
        getCommand("ryansays").setTabCompleter(new TabAutocomplete());
        getServer().getPluginManager().registerEvents(game, this);
        getServer().getPluginManager().registerEvents(drinkPotions, this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
    }
    
    @Override
    public void onDisable() {
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ryansays") && sender instanceof Player && args.length > 0) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("start") && !Game.isPlaying && setSpawn) {
                startGame(player);
            } else if (args[0].equalsIgnoreCase("stop")) {
                stopGame();
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Please use /ryansays start/stop");
        }
        return true;
    }
    
    private void startGame(Player player) {
        player.getServer().broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " has started a game of Ryan Says!");
        
        for (int i = 0; i < player.getWorld().getPlayers().size(); i++) {
            Player playerI = player.getWorld().getPlayers().get(i);
            playerI.teleport(gameSpawn);
            PlayerManager.hasCompletedObjective.clear();
            PlayerManager.addPlayer(playerI);
        }
        
        game.startActivity(player.getWorld(), player.getServer());
    }
    
    private void stopGame() {
        getServer().broadcastMessage(ChatColor.RED + "The game has ended!");
        Game.isPlaying = false;
    }
    
    public Main getPlugin() {
        return this;
    }
}
