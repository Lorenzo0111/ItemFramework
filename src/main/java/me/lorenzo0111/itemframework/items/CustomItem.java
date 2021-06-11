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

package me.lorenzo0111.itemframework.items;

import me.lorenzo0111.itemframework.actions.Action;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom Item
 */
public class CustomItem {
    private final ItemStack item;
    private final ItemMeta meta;
    private Action<PlayerInteractEvent> action;
    private Action<BlockBreakEvent> breakAction;

    /**
     * @param id identifier
     */
    public CustomItem(short id) {
        this(new ItemStack(Material.DIAMOND_AXE),id);
    }

    /**
     * @param item ItemStack
     * @param id identifier
     */
    public CustomItem(ItemStack item, short id) {
        this.item = item;
        this.item.setDurability(id);
        this.meta = item.getItemMeta();
    }

    /**
     * Change the item name
     * @param name Name to set
     * @return the same instance
     */
    public CustomItem setName(String name) {
        this.meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    /**
     * @param lore List of lore lines
     * @return the same instance
     */
    public CustomItem setLore(String... lore) {
        List<String> list = Arrays.asList(lore);
        list = list.stream()
                .map(i -> i = ChatColor.translateAlternateColorCodes('&', i))
                .collect(Collectors.toList());
        meta.setLore(list);
        return this;
    }

    /**
     * Set the click action that is called when someone uses the item
     * @param event Event that handles the action
     * @return the same instance
     */
    public CustomItem setClickAction(Action<PlayerInteractEvent> event) {
        this.action = event;
        return this;
    }

    /**
     * Set the break action that is called when someone breaks a block with the item
     * @param event Event that handles the action
     * @return the same instance
     */
    public CustomItem setBreakAction(Action<BlockBreakEvent> event) {
        this.breakAction = event;
        return this;
    }

    /**
     * Used internally from the listener
     * @param event Event that will call the {@link Action}
     */
    public void performInteract(PlayerInteractEvent event) {
        if (this.action == null)
            return;
        this.action.execute(event);
    }

    /**
     * Used internally from the listener
     * @param event Event that will call the {@link Action}
     */
    public void performBreak(BlockBreakEvent event) {
        if (this.breakAction == null)
            return;

        this.breakAction.execute(event);
    }

    /**
     * Build the item into an ItemStack
     * @return item builded
     */
    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
