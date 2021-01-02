package com.ryan.ryansays;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.event.Listener;

import java.util.Random;

public class Game implements Listener {
    
    private static final Random random = new Random();
    
    public void startActivity(World world, Server server) {
        switch (randomActivity()) {
            case DRINKPOTION:
                DrinkPotions.givePotions(world);
                break;
            default:
                server.broadcastMessage(ChatColor.RED + "I haven't added the activity that was rolled. Run the command again.");
        }
    }
    
    private Activity randomActivity() {
        return Activity.values()[random.nextInt(Activity.values().length)];
    }
    
}
