package me.damascus2000.lumberaxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class CraftAxe {

    private Plugin pl;

    public CraftAxe(Plugin pl){
        this.pl = pl;
    }

    public void registerCraftingrecipe(){
        ItemStack timberAxe = new ItemStack(Material.DIAMOND_AXE);

        ItemMeta timberMeta = timberAxe.getItemMeta();
        timberMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLumberAxe"));
        List<String> lore = Arrays.asList("&fOnly for real men.");
        timberMeta.setLore(lore);

        timberAxe.setItemMeta(timberMeta);
        NamespacedKey key = new NamespacedKey(pl, "LumberAxe");

        ShapedRecipe recipe = new ShapedRecipe(key, timberAxe);

        recipe.shape("NDD", " SD", " S ");

        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('N', Material.NETHER_STAR);
        pl.getServer().addRecipe(recipe);

    }
}
