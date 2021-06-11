/*
 * This file is part of RocketPlaceholders, licensed under the MIT License.
 *
 * Copyright (c) Lorenzo0111
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

