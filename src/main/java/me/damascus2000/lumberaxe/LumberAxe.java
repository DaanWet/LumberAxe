package me.damascus2000.lumberaxe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LumberAxe extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new CraftAxe(this).registerCraftingrecipe();
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
