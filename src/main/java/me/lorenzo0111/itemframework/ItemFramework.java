package me.lorenzo0111.itemframework;

import me.lorenzo0111.itemframework.items.CustomItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class of the framework
 */
public final class ItemFramework {
    private static final Map<Short, CustomItem> STORAGE = new HashMap<>();

    /**
     * Check if an item is a custom item from the framework
     * @param item Item to check
     * @return true if it is a custom item
     */
    public static boolean isCustomItem(ItemStack item) {
        return getCustomItem(item) != null;
    }

    /**
     * Get a custom item from the itemstack
     * @param item Item to get
     * @return A custom item instance from the item
     */
    @Nullable
    public static CustomItem getCustomItem(ItemStack item) {
        if (item == null)
            return null;

        if (item.getDurability() <= 0)
            return null;

        short id = item.getDurability();
        return STORAGE.get(id);
    }

    /**
     * @param id identifier, it is the durability of the ItemStack
     * @return a new custom item or null if that id already exist
     */
    @Nullable
    public static CustomItem createCustomItem(short id) {
        if (STORAGE.containsKey(id))
            return null;

        CustomItem item = new CustomItem(id);
        STORAGE.put(id,item);
        return item;
    }

    /**
     * @return Storage
     */
    public static Map<Short, CustomItem> getStorage() {
        return STORAGE;
    }
}
