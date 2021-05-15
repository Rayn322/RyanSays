package com.ryan.ryansays.activities;

import com.ryan.ryansays.Game;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DrinkPotion extends Activity {
    
    public PotionType randomPotionType;
    public ArrayList<PotionType> possiblePotions = new ArrayList<>();
    
    @Override
    public void setup() {
        activityType = ActivityType.DRINKPOTION;
        instructions = "Drink the " + randomPotionType.getEffectType().getName() + " potion!";
        System.out.println(ChatColor.GREEN + "drink potion setup called");
        
        possiblePotions.add(PotionType.SPEED);
        possiblePotions.add(PotionType.FIRE_RESISTANCE);
        possiblePotions.add(PotionType.POISON);
        possiblePotions.add(PotionType.REGEN);
        possiblePotions.add(PotionType.WATER_BREATHING);
        possiblePotions.add(PotionType.NIGHT_VISION);
        possiblePotions.add(PotionType.INVISIBILITY);
        possiblePotions.add(PotionType.JUMP);
        possiblePotions.add(PotionType.SLOWNESS);
        
        selectPotion();
        givePotions();
        super.setup();
    }
    
    @Override
    public void cleanup() {
        super.cleanup();
        possiblePotions.clear();
        System.out.println(ChatColor.GREEN + "drink potion cleanup called");
    }
    
    public void selectPotion() {
        Random random = new Random();
        int i = random.nextInt(possiblePotions.size());
        randomPotionType = possiblePotions.get(i);
    }
    
    public void givePotions() {
        Collections.shuffle(possiblePotions);
        
        for (PotionType potionType : possiblePotions) {
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
            
            potionMeta.setBasePotionData(new PotionData(potionType));
            potion.setItemMeta(potionMeta);
            
            for (Player player : Game.getPlayersPlaying()) {
                player.getInventory().addItem(potion);
            }
        }
    }
    
    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        if (isPlaying && event.getItem().getType() == Material.POTION) {
            PotionMeta potionMeta = (PotionMeta) event.getItem().getItemMeta();
            event.setCancelled(true);
            
            if (potionMeta.getBasePotionData().getType() == randomPotionType) {
                onTaskComplete(event.getPlayer());
            } else {
                onTaskFail(event.getPlayer());
            }
        }
    }
}
