package com.ryan.ryansays.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class NameUtils {
    
    public static String commaSeparate(List<Player> players, ChatColor followingTextColor) {
        StringBuilder sb = new StringBuilder();
        
        if (players.size() == 1) {
            sb.append(players.get(0).getName());
            
        } else if (players.size() == 2) {
            sb.append(getColoredName(players.get(0), followingTextColor)).append(" and ").append(getColoredName(players.get(1), followingTextColor));
            
        } else {
            for (Player player : players) {
                if (players.indexOf(player) == players.size() - 1) {
                    sb.append(", and ").append(getColoredName(player, followingTextColor));
                } else if (players.indexOf(player) == 0) {
                    sb.append(getColoredName(player, followingTextColor));
                } else {
                    sb.append(", ").append(getColoredName(player, followingTextColor));
                }
            }
        }
        
        return sb.toString();
    }
    
    public static String getColoredName(Player player, ChatColor followingTextColor) {
        return ChatColor.GOLD + player.getName() + followingTextColor;
    }
}
