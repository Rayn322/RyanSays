package com.ryan.ryansays;

import com.ryan.ryansays.activity.ActivityRandomizer;
import com.ryan.ryansays.activity.ActivityType;
import com.ryan.ryansays.command.StartCommand;
import com.ryan.ryansays.gameplay.Game;
import com.ryan.ryansays.listener.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RyanSays extends JavaPlugin {
    
    private static RyanSays plugin;
    
    public static RyanSays getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
        registerListenersAndCommands();
        Game.setGameSpawn(Bukkit.getWorld("ryansays"));
        
        plugin = this;
    }
    
    @Override
    public void onDisable() {
    }
    
    public void registerListenersAndCommands() {
        getServer().getPluginManager().registerEvents(ActivityRandomizer.getActivity(ActivityType.JUMP), this);
        getServer().getPluginManager().registerEvents(ActivityRandomizer.getActivity(ActivityType.DRINKPOTION), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        
        getCommand("ryansays").setExecutor(new StartCommand());
    }
}
