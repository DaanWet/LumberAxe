package me.damascus2000.lumberaxe;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class LumberAxe extends JavaPlugin {


    private LumberAdvancement adv;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new CraftAxe(this).registerCraftingrecipe();
        adv = new LumberAdvancement(this);
        for (Player p : getServer().getOnlinePlayers()){
            adv.getAdvManager().addPlayer(p);
        }
        getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new InventoryChange(this), this);
    }

    public LumberAdvancement getLumberAdvancement(){
        return adv;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
