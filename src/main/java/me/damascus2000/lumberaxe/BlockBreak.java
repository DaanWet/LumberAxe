package me.damascus2000.lumberaxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.*;

public class BlockBreak implements Listener {

    private Random random = new Random();


    private ArrayList<Material> wood = new ArrayList<>(Arrays.asList(Material.ACACIA_LOG, Material.BIRCH_LOG, Material.OAK_LOG,
            Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.SPRUCE_LOG));
    private List<Material> leaves = Arrays.asList(Material.ACACIA_LEAVES, Material.BIRCH_LEAVES, Material.OAK_LEAVES,
            Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.SPRUCE_LEAVES);
    private List<Vector> directions = Arrays.asList(new Vector(0, 0, 1), new Vector(0, 1, 0), new Vector(1, 0, 0),
            new Vector(0, 0, -1), new Vector(-1, 0, 0), new Vector(-1, 0, 1), new Vector(1, 0, -1),
            new Vector(-1, 0, -1), new Vector(1, 0, 1), new Vector(-1, 1, 1), new Vector(1, 1, -1),
            new Vector(-1, 1, -1), new Vector(1, 1, 1), new Vector(0, 1, 1), new Vector(0, 1, -1),
            new Vector(1, 1, 0), new Vector(-1, 1, 0));
    private LumberAxe plugin;

    public BlockBreak(LumberAxe plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ItemMeta item = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        ItemMeta axe = new CraftAxe(plugin).getAxe().getItemMeta();
        if (item != null &&  item.hasDisplayName() && item.getDisplayName().equals(axe.getDisplayName())
                && item.hasLore() && item.getLore().equals(axe.getLore())
                && e.getPlayer().isSneaking()) {
            if (wood.contains(e.getBlock().getType())) {
                breakWood(e.getBlock(), e.getPlayer().getInventory().getItemInMainHand(), e.getBlock().getX(), e.getBlock().getZ());
            }
        }
    }

    private void breakWood(Block block, ItemStack tool, int x, int z) {
        for (Vector vec : directions) {
            Block bl = block.getLocation().add(vec).getBlock();
            if (wood.contains(bl.getType()) && Math.abs(x - bl.getX()) <= 3 && Math.abs(z - bl.getZ()) <= 3) {
                bl.breakNaturally(tool);
                ItemMeta toolMeta = tool.getItemMeta();
                if (toolMeta != null){
                    Map<Enchantment, Integer> enchants = toolMeta.getEnchants();
                    int chance = 100 / (1 + enchants.getOrDefault(Enchantment.DURABILITY, 0));
                    Damageable damageable = (Damageable) toolMeta;
                    damageable.setDamage(damageable.getDamage() + random.nextInt(100) < chance ? 1 : 0);
                    breakWood(bl, tool, x, z);
                }
            } else if (leaves.contains(bl.getType()) && Math.abs(x - bl.getX()) < 5 && Math.abs(z - bl.getZ()) < 5) {
                bl.breakNaturally();
                breakWood(bl, tool, x, z);
            }
        }
    }

}
