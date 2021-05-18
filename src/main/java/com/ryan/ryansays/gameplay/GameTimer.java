package com.ryan.ryansays.gameplay;

import com.ryan.ryansays.RyanSays;
import com.ryan.ryansays.activity.Activity;
import org.bukkit.Bukkit;

public class GameTimer {
    
    public static void scheduleActivityEnd(Activity activity) {
        Bukkit.getScheduler().runTaskLater(RyanSays.getPlugin(), () -> Game.endActivity(activity), 200);
    }
    
    public static void scheduleNextActivityBegin() {
        Bukkit.getScheduler().runTaskLater(RyanSays.getPlugin(), Game::startNextActivity, 60);
    }
}
