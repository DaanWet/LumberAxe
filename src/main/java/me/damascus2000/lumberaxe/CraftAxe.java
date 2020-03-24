package me.damascus2000.lumberaxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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


    public ItemStack getAxe(){
        ItemStack timberAxe = new ItemStack(Material.DIAMOND_AXE);

        ItemMeta timberMeta = timberAxe.getItemMeta();
        timberMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aLumberAxe"));
        List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&bSneak to activate super power"));
        timberMeta.setLore(lore);

        timberAxe.setItemMeta(timberMeta);
        return timberAxe;
    }

    public void registerCraftingrecipe(){

        NamespacedKey key = new NamespacedKey(pl, "LumberAxe");

        ShapedRecipe recipe = new ShapedRecipe(key, getAxe());

        recipe.shape("NDD", " BD", " B ");

        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('N', Material.NETHER_STAR);
        pl.getServer().addRecipe(recipe);

    }
}
