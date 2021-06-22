/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.item.inventory.ItemStack
 */
package com.m1zark.m1utilities.api.GUI;

import com.m1zark.m1utilities.api.GUI.Clickable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.spongepowered.api.item.inventory.ItemStack;

public class Icon {
    private int slot;
    private ItemStack display;
    private Set<Consumer<Clickable>> listeners;

    public Icon(int slot, ItemStack display) {
        this.slot = slot;
        this.display = display;
        this.listeners = new HashSet<Consumer<Clickable>>();
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ItemStack getDisplay() {
        return this.display;
    }

    public void setDisplay(ItemStack display) {
        this.display = display;
    }

    public Set<Consumer<Clickable>> getListeners() {
        return this.listeners;
    }

    public void addListener(Consumer<Clickable> listener) {
        this.listeners.add(listener);
    }

    public void process(Clickable clickable) {
        for (Consumer<Clickable> listener : this.listeners) {
            listener.accept(clickable);
        }
    }
}

