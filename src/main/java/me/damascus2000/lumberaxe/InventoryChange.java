package me.damascus2000.lumberaxe;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryChange implements Listener {

    private LumberAxe plugin;


    public InventoryChange(LumberAxe pl){
        this.plugin = pl;
    }

    @EventHandler
    public void onInvChange(EntityPickupItemEvent e){
        ItemMeta axe = new CraftAxe(plugin).getAxe().getItemMeta();
        ItemMeta item = e.getItem().getItemStack().getItemMeta();
        if (e.getEntity() instanceof Player && item != null && item.hasDisplayName() && item.hasLore() &&  item.getDisplayName().equals(axe.getDisplayName()) &&  item.getLore().equals(axe.getLore())) {
            Player player = (Player) e.getEntity();
            AdvancementManager advancementManager = plugin.getLumberAdvancement().getAdvManager();
            Advancement advancement = advancementManager.getAdvancement(new NameKey("custom", "root"));
            advancementManager.addPlayer(player);
            advancementManager.revokeAdvancement(player, advancement);
            advancementManager.grantAdvancement(player, advancement);
            advancement.displayMessageToEverybody(player);
        }
    }


}
