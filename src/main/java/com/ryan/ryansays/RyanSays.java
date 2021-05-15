package com.ryan.ryansays;

import com.ryan.ryansays.activities.DrinkPotion;
import com.ryan.ryansays.activities.Jump;
import com.ryan.ryansays.listeners.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RyanSays extends JavaPlugin {
    
    private static RyanSays plugin;
    
    public static RyanSays getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
        registerListeners();
        Game.setGameSpawn(Bukkit.getWorld("ryansays"));
    }
    
    @Override
    public void onDisable() {
    }
    
    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new Jump(), this);
        getServer().getPluginManager().registerEvents(new DrinkPotion(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }
}
