package com.ryan.ryansays.command;

import com.ryan.ryansays.gameplay.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        if (label.equalsIgnoreCase("ryansays") && sender instanceof Player) {
            Game.startGame((Player) sender);
        }
        
        return true;
    }
}
