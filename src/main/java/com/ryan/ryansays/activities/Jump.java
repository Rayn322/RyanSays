package com.ryan.ryansays.activities;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class Jump extends Activity {
    
    @Override
    public void setup() {
        activityType = ActivityType.JUMP;
        instructions = "Jump in the air!";
        System.out.println(ChatColor.GREEN + "jump setup called");
        super.setup();
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        System.out.println(ChatColor.GREEN + "jump cleanup called");
    }
    
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        if (isPlaying) {
            onTaskComplete(event.getPlayer());
        }
    }
}
