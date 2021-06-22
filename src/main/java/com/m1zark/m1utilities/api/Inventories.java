/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.data.DataQuery
 *  org.spongepowered.api.data.key.Keys
 *  org.spongepowered.api.entity.living.player.Player
 *  org.spongepowered.api.item.inventory.ItemStack
 *  org.spongepowered.api.item.inventory.entity.Hotbar
 *  org.spongepowered.api.item.inventory.entity.MainPlayerInventory
 *  org.spongepowered.api.item.inventory.query.QueryOperation
 *  org.spongepowered.api.item.inventory.query.QueryOperationTypes
 *  org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult$Type
 *  org.spongepowered.api.text.Text
 */
package com.m1zark.m1utilities.api;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.Hotbar;
import org.spongepowered.api.item.inventory.entity.MainPlayerInventory;
import org.spongepowered.api.item.inventory.query.QueryOperation;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;
import org.spongepowered.api.text.Text;

public class Inventories {
    public static boolean giveItem(Player player, ItemStack item, int quantity) {
        return player.getInventory().query(QueryOperationTypes.INVENTORY_TYPE.of(Hotbar.class)).union(player.getInventory().query(QueryOperationTypes.INVENTORY_TYPE.of(MainPlayerInventory.class))).offer(ItemStack.builder().from(item).quantity(quantity).build()).getType() == InventoryTransactionResult.Type.SUCCESS;
    }

    public static boolean removeItem(Player player, ItemStack item, int quantity) {
        return player.getInventory().query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(item)).poll(quantity).isPresent();
    }

    public static int getItemCount(Player player, ItemStack item) {
        return player.getInventory().query(QueryOperationTypes.ITEM_STACK_IGNORE_QUANTITY.of(item)).totalItems();
    }

    public static Text getItemName(ItemStack item) {
        if (item.get(Keys.ITEM_LORE).isPresent()) {
            return (Text)item.get(Keys.DISPLAY_NAME).orElse(Text.of(item.getTranslation().get()));
        }
        return Text.of(item.getTranslation().get());
    }

    public static boolean doesHaveNBT(ItemStack item, String id) {
        return item.toContainer().getString(DataQuery.of("UnsafeData", id)).isPresent();
    }
}

