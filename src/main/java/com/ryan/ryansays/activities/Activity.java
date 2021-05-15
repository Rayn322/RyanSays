package com.ryan.ryansays.activities;

import org.bukkit.event.Listener;

public interface Activity extends Listener {
    
    void setup();
    
    void onTaskComplete();
    
    void cleanup();
    
}