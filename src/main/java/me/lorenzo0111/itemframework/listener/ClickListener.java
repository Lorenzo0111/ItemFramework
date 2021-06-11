package me.lorenzo0111.itemframework.listener;

import me.lorenzo0111.itemframework.ItemFramework;
import me.lorenzo0111.itemframework.items.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

/**
 * Listener
 */
public class ClickListener implements Listener {

    public ClickListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        CustomItem ci = this.get(e.getPlayer());

        if (ci == null)
            return;

        ci.performInteract(e);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        CustomItem ci = this.get(e.getPlayer());

        if (ci == null)
            return;

        ci.performBreak(e);
    }

    @Nullable
    public CustomItem get(Player player) {
        if (player == null)
            return null;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null)
            return null;

        if (!ItemFramework.isCustomItem(item))
            return null;

        return ItemFramework.getCustomItem(item);
    }
}

