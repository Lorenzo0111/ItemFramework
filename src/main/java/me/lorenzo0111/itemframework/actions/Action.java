package me.lorenzo0111.itemframework.actions;

import org.bukkit.event.Event;

@FunctionalInterface
public interface Action<T extends Event> {
    /**
     * @param event Event that handles the action
     */
    void execute(final T event);
}