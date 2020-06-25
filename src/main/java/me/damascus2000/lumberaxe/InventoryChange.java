package me.damascus2000.lumberaxe;

import eu.endercentral.crazy_advancements.Advancement;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryChange implements Listener {

    private LumberAxe plugin;


    public InventoryChange(LumberAxe pl){
        this.plugin = pl;
    }

    @EventHandler
    public void onInvChange(EntityPickupItemEvent e){
        ItemMeta axe = new CraftAxe(plugin).getAxe().getItemMeta();
        ItemMeta item = e.getItem().getItemStack().getItemMeta();
        if (e.getEntity() instanceof Player && item != null) {
            Player player = (Player) e.getEntity();
            AdvancementManager advancementManager = plugin.getLumberAdvancement().getAdvManager();
            NameKey namekey = new NameKey("custom", "root");
            Advancement advancement = advancementManager.getAdvancement(namekey);
            if ( advancement.isGranted(player) && item.hasDisplayName() && item.hasLore() &&
                    item.getDisplayName().equals(axe.getDisplayName()) && item.getLore().equals(axe.getLore())) {

                advancementManager.grantAdvancement(player, advancement);
                advancement.displayMessageToEverybody(player);
                advancementManager.saveProgress(player, "custom");
            }
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        new BukkitRunnable() {
            @Override
            public void run() {
                AdvancementManager advm = plugin.getLumberAdvancement().getAdvManager();
                advm.loadProgress(e.getPlayer(), "custom");
                advm.addPlayer(e.getPlayer());
            }
        }.runTaskLater(plugin, 5);
    }


}
