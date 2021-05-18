package com.ryan.ryansays.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ActivityRandomizer {
    
    public static Jump jump;
    public static DrinkPotion drinkPotion;
    
    public static ArrayList<ActivityType> getActivityList() {
        ArrayList<ActivityType> activities = new ArrayList<>(Arrays.asList(ActivityType.values()));
        Collections.shuffle(activities);
        return activities;
    }
    
    public static Activity getActivity(ActivityType activityType) {
        switch (activityType) {
            case JUMP:
                if (jump == null) {
                    jump = new Jump();
                }
                return jump;
                
            case DRINKPOTION:
                if (drinkPotion == null) {
                    drinkPotion = new DrinkPotion();
                }
                return drinkPotion;
        }
        return jump;
    }
}
