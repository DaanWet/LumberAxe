package me.damascus2000.lumberaxe;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockBreak implements Listener {

    private ArrayList<Material> wood = new ArrayList<>(Arrays.asList(Material.ACACIA_LOG, Material.BIRCH_LOG, Material.OAK_LOG,
            Material.DARK_OAK_LOG, Material.JUNGLE_LOG, Material.SPRUCE_LOG));
    private List<Vector> directions = Arrays.asList( new Vector(0, 0, 1), new Vector(0, 1, 0), new Vector(1, 0, 0),
            new Vector(0, 0, -1), new Vector(0, -1, 0), new Vector(-1, 0, 0));


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (true /*check axe*/){
            if (wood.contains(e.getBlock().getType())){
                breakWood(e.getBlock(), e.getPlayer().getItemOnCursor());
            }
        }
    }

    private void breakWood(Block block, ItemStack tool){
        for (Vector vec : directions){
            Block bl = block.getLocation().add(vec).getBlock();
            if (wood.contains(bl.getType())){
                bl.breakNaturally(tool);
                breakWood(bl, tool);
            }
        }
    }

}
