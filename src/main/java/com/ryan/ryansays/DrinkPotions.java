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
import java.util.Random;

public class DrinkPotions implements Listener {
    
    private static final Random random = new Random();
    public static PotionType randomPotion;
    private static Main plugin;
    
    public DrinkPotions(Main pl) {
        plugin = pl;
    }
    
    public static void givePotions(World world) {
        GameTimer.startTimer();
        Game.isPlaying = true;
        List<ItemStack> potions = getRandomPotions();
        Bukkit.getServer().broadcastMessage("Drink a " + randomPotion.getEffectType().getName() + " potion.");
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
        
        int index = random.nextInt(potionTypes.size());
        randomPotion = potionTypes.get(index);
        System.out.println("A " + randomPotion.name() + " potion was selected");
        
        Collections.shuffle(potions);
        return potions;
    }
    
    @EventHandler
    public void onPotionDrink(EntityPotionEffectEvent event) {
        if (event.getEntity() instanceof Player && Game.isPlaying && event.getCause() == EntityPotionEffectEvent.Cause.POTION_DRINK
                && event.getNewEffect().getType().equals(randomPotion.getEffectType())) {
            Player player = (Player) event.getEntity();
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has completed the activity!");
            PlayerManager.hasCompletedObjective.replace(player.getUniqueId(), true);
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.getInventory().clear(), 5);
        }
    }
}
