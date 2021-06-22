/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.entity.living.player.Player
 *  org.spongepowered.api.event.item.inventory.ClickInventoryEvent
 */
package com.m1zark.m1utilities.api.GUI;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;

public class Clickable {
    private final Player player;
    private final ClickInventoryEvent event;

    public Player getPlayer() {
        return this.player;
    }

    public ClickInventoryEvent getEvent() {
        return this.event;
    }

    Clickable(Player player, ClickInventoryEvent event) {
        this.player = player;
        this.event = event;
    }
}

