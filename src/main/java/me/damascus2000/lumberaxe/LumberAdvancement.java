package me.damascus2000.lumberaxe;

import eu.endercentral.crazy_advancements.*;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import org.bukkit.Material;

public class LumberAdvancement {



    private LumberAxe plugin;
    private AdvancementManager advManager;


    public LumberAdvancement(LumberAxe plugin){
        this.plugin = plugin;
        advManager = CrazyAdvancements.getNewAdvancementManager();
        AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.DIAMOND_AXE, "Only For Real Men", "Craft a LumberAxe", AdvancementDisplay.AdvancementFrame.TASK, false, false, AdvancementVisibility.HIDDEN);
        rootDisplay.setBackgroundTexture("textures/block/concrete_yellow.png");
        Advancement root = new Advancement(null, new NameKey("custom", "root"), rootDisplay);
        advManager.addAdvancement(root);
    }

    public AdvancementManager getAdvManager() {
        return advManager;
    }
}
