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

package me.lorenzo0111.itemframework;

import me.lorenzo0111.itemframework.exceptions.InitializationException;
import me.lorenzo0111.itemframework.items.CustomItem;
import me.lorenzo0111.itemframework.items.IdentifierType;
import me.lorenzo0111.itemframework.listener.ClickListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main class of the framework
 */
@SuppressWarnings("unused")
public final class ItemFramework {
    private static final Map<Integer, CustomItem> STORAGE = new HashMap<>();
    private static JavaPlugin plugin;

    private ItemFramework() {}

    /**
     * Check if an item is a custom item from the framework
     * @param item Item to check
     * @return true if it is a custom item
     */
    public static boolean isCustomItem(ItemStack item) {
        return getCustomItem(item) != null;
    }

    /**
     * Get a custom item from the ItemStack
     * @param item Item to get
     * @return A custom item instance from the item
     */
    @SuppressWarnings("deprecation")
    @Nullable
    public static CustomItem getCustomItem(ItemStack item) {
        int found = -1;

        if (item == null)
            return null;

        try {
            found = Objects.requireNonNull(item.getItemMeta()).getCustomModelData();
        } catch (Exception ignored) {}

        if (item.getDurability() > 0)
            found = item.getDurability();

        try {
            found = Objects.requireNonNull(((Damageable) item.getItemMeta())).getDamage();
        } catch (Exception ignored) {}

        return STORAGE.get(found);
    }

    /**
     * @param id identifier, it is the durability of the ItemStack
     * @return a new custom item or null if that id already exist
     */
    @Nullable
    public static CustomItem createCustomItem(int id) {
        if (STORAGE.containsKey(id))
            return null;

        CustomItem item = new CustomItem(id);
        STORAGE.put(id,item);
        return item;
    }

    /**
     * @param id identifier, it is the durability of the ItemStack
     * @param type Type of identifier
     * @return a new custom item or null if that id already exist
     */
    @Nullable
    public static CustomItem createCustomItem(int id, IdentifierType type) {
        if (STORAGE.containsKey(id))
            return null;

        CustomItem item = new CustomItem(type,id);
        STORAGE.put(id,item);
        return item;
    }


    /**
     * Required to make the framework work
     * @param plugin Plugin that is using the framework
     */
    public static void init(JavaPlugin plugin) {
        if (ItemFramework.plugin != null)
            throw new InitializationException("The framework has already been initialized.");

        new ClickListener(plugin);
    }

    /**
     * @return Storage
     */
    public static Map<Integer, CustomItem> getStorage() {
        return STORAGE;
    }
}
