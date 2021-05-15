package com.ryan.ryansays;

import com.ryan.ryansays.activities.DrinkPotions;
import com.ryan.ryansays.commands.StartCommand;
import com.ryan.ryansays.listeners.PlayerEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    private static Main plugin;
    
    public static Main getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DrinkPotions(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        
        StartCommand startCommand = new StartCommand();
        
        getCommand("ryansays").setExecutor(startCommand);
        getCommand("ryansays").setTabCompleter(startCommand);
        
        plugin = this;
    }
    
    @Override
    public void onDisable() {
    }
}
