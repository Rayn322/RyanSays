package com.ryan.ryansays;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {
    
    public static final HashMap<Player, Boolean> hasCompletedObjective = new HashMap<>();
    
    public static void addPlayer(Player player) {
        if (!hasCompletedObjective.containsKey(player)) {
            hasCompletedObjective.put(player, false);
        }
    }
}