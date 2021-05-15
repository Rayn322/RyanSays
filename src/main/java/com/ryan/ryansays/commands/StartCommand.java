package com.ryan.ryansays.commands;

import com.ryan.ryansays.Game;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements TabExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("ryansays") && sender instanceof Player && args.length > 0) {
            Player player = (Player) sender;
            
            if (args[0].equalsIgnoreCase("start") && !Game.isPlaying && Game.setSpawn) {
                Game.startGame(player);
            } else if (args[0].equalsIgnoreCase("stop")) {
                Game.stopGame();
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Please use /ryansays start/stop");
        }
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            
            List<String> parameters = new ArrayList<>();
            parameters.add("start");
            parameters.add("stop");
            
            return parameters;
        }
        return null;
    }
}
