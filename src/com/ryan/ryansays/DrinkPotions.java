package com.ryan.ryansays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrinkPotions implements Listener {
    
    private static Main plugin;
    public DrinkPotions(Main pl) {
        plugin = pl;
    }
    
    
    private static boolean drinkingPotions = false;
    
    public static void givePotions(World world) {
        startTimer();
        drinkingPotions = true;
        List<ItemStack> potions = getRandomPotions();
        for (int i = 0; i < world.getPlayers().size(); i++) {
            Player playerI = world.getPlayers().get(i);
            PlayerInventory inventory = playerI.getInventory();
            inventory.clear();
            for (int j = 0; j < potions.size(); j++) {
                inventory.setItem(j, potions.get(j));
            }
        }
    }
    
    private static List<ItemStack> getRandomPotions() {
        List<ItemStack> potions = new ArrayList<>();
        List<PotionType> potionTypes = new ArrayList<>();
        potionTypes.add(PotionType.SPEED);
        potionTypes.add(PotionType.FIRE_RESISTANCE);
        potionTypes.add(PotionType.POISON);
        potionTypes.add(PotionType.REGEN);
        potionTypes.add(PotionType.WATER_BREATHING);
        potionTypes.add(PotionType.NIGHT_VISION);
        potionTypes.add(PotionType.INVISIBILITY);
        potionTypes.add(PotionType.JUMP);
        potionTypes.add(PotionType.SLOWNESS);
        
        for (PotionType effectType : potionTypes) {
            ItemStack potion = new ItemStack(Material.POTION);
            PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
            potionMeta.setBasePotionData(new PotionData(effectType));
            potion.setItemMeta(potionMeta);
            potions.add(potion);
        }
        
        Collections.shuffle(potions);
        return potions;
    }
    
    private static void startTimer() {
        Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getServer().broadcastMessage(ChatColor.RED + "Time is up!"), 200);
        if (drinkingPotions) drinkingPotions = false;
    }
    
    @EventHandler
    public void onPotionDrink(EntityPotionEffectEvent event) {
        if (event.getEntity() instanceof Player && drinkingPotions) {
            Player player = (Player) event.getEntity();
            player.getInventory().clear();
            player.getActivePotionEffects().clear();
        }
    }
    
}
