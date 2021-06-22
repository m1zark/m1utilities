/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.Sponge
 *  org.spongepowered.api.data.key.Keys
 *  org.spongepowered.api.data.type.DyeColor
 *  org.spongepowered.api.entity.living.player.Player
 *  org.spongepowered.api.event.item.inventory.ClickInventoryEvent
 *  org.spongepowered.api.event.item.inventory.InteractInventoryEvent$Close
 *  org.spongepowered.api.item.ItemTypes
 *  org.spongepowered.api.item.inventory.Inventory
 *  org.spongepowered.api.item.inventory.Inventory$Builder
 *  org.spongepowered.api.item.inventory.InventoryArchetypes
 *  org.spongepowered.api.item.inventory.InventoryProperty
 *  org.spongepowered.api.item.inventory.ItemStack
 *  org.spongepowered.api.item.inventory.Slot
 *  org.spongepowered.api.item.inventory.property.InventoryDimension
 *  org.spongepowered.api.item.inventory.property.InventoryTitle
 *  org.spongepowered.api.item.inventory.property.SlotIndex
 *  org.spongepowered.api.item.inventory.query.QueryOperation
 *  org.spongepowered.api.item.inventory.query.QueryOperationTypes
 *  org.spongepowered.api.item.inventory.type.GridInventory
 *  org.spongepowered.api.text.Text
 *  org.spongepowered.api.text.format.TextColors
 */
package com.m1zark.m1utilities.api.GUI;

import com.m1zark.m1utilities.M1utilities;
import com.m1zark.m1utilities.api.GUI.Clickable;
import com.m1zark.m1utilities.api.GUI.Icon;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.InventoryProperty;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.query.QueryOperation;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.item.inventory.type.GridInventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class InventoryManager {
    protected Player player;
    private Inventory.Builder builder;
    private Map<Integer, Icon> icons;
    private Inventory inventory;
    private int size;
    private boolean border = false;

    public InventoryManager(Player player, int size, Text title) {
        this.player = player;
        this.size = size;
        this.icons = new HashMap<Integer, Icon>();
        this.builder = Inventory.builder().property("inventorytitle", InventoryTitle.of(title)).property("inventorydimension", InventoryDimension.of(9, size)).of(InventoryArchetypes.MENU_GRID).listener(ClickInventoryEvent.class, this::processClick).listener(InteractInventoryEvent.Close.class, this::processClose);
    }

    public Inventory.Builder getInventoryBuilder() {
        return this.builder;
    }

    private void processClick(ClickInventoryEvent event) {
        event.setCancelled(true);
        event.getCause().first(Player.class).ifPresent(player -> event.getTransactions().forEach(transaction -> transaction.getSlot().getProperty(SlotIndex.class, "slotindex").ifPresent(slot -> {
            Icon icon = this.icons.get(slot.getValue());
            if (icon != null) {
                Sponge.getScheduler().createTaskBuilder().execute(() -> icon.process(new Clickable(player, event))).delayTicks(1L).submit(M1utilities.getInstance());
            }
        })));
    }

    protected void processClose(InteractInventoryEvent.Close event) {
    }

    public void addIcon(Icon icon) {
        this.icons.put(icon.getSlot(), icon);
    }

    public void removeIcon(int slot) {
        this.icons.remove(slot);
    }

    public Optional<Icon> getIcon(int slot) {
        return Optional.ofNullable(this.icons.get(slot));
    }

    public void clearIcons(int ... slots) {
        for (int slot : slots) {
            this.icons.remove(slot);
        }
    }

    public Map<Integer, Icon> getAllIcons() {
        return this.icons;
    }

    public void drawBorder(int rows, DyeColor color) {
        ItemStack border = ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DISPLAY_NAME, Text.of(TextColors.BLACK, "")).add(Keys.DYE_COLOR, color).build();
        for (int y = 0; y < rows; ++y) {
            if (y == 0 || y == rows - 1) {
                for (int x = 0; x < 9; ++x) {
                    this.addIcon(new Icon(x + 9 * y, border));
                }
                continue;
            }
            this.addIcon(new Icon(9 * y, border));
            this.addIcon(new Icon(8 + 9 * y, border));
        }
        this.border = true;
    }

    public boolean isInBorder(int slot) {
        return !this.border || slot % 9 != 0 && slot % 9 != 8 && slot > 9 && slot < (this.size - 1) * 9;
    }

    public void updateContents(int ... slots) {
        GridInventory inv = this.inventory.query(new QueryOperation[]{QueryOperationTypes.INVENTORY_TYPE.of(GridInventory.class)});
        for (int sl : slots) {
            Slot slot = inv.getSlot(SlotIndex.of(sl)).orElseThrow(() -> new IllegalArgumentException("Invalid index: " + sl));
            if (this.icons.containsKey(sl)) {
                slot.set(this.icons.get(sl).getDisplay());
                continue;
            }
            slot.clear();
        }
    }

    public void updateContents(int min, int max) {
        GridInventory inv = this.inventory.query(new QueryOperation[]{QueryOperationTypes.INVENTORY_TYPE.of(GridInventory.class)});
        for (int i = min; i < max + 1; ++i) {
            int index = i;
            Slot slot = inv.getSlot(SlotIndex.of(i)).orElseThrow(() -> new IllegalArgumentException("Invalid index: " + index));
            if (this.icons.containsKey(i)) {
                slot.set(this.icons.get(i).getDisplay());
                continue;
            }
            slot.clear();
        }
    }

    public void updateContents() {
        GridInventory gridInventory = this.getInventory().query(new QueryOperation[]{QueryOperationTypes.INVENTORY_TYPE.of(GridInventory.class)});
        gridInventory.clear();
        this.icons.forEach((index, inventoryIcon) -> {
            Slot slot = gridInventory.getSlot(SlotIndex.of(index)).orElseThrow(() -> new IllegalArgumentException("Invalid index: " + index));
            slot.set(inventoryIcon.getDisplay());
        });
    }

    public Inventory getInventory() {
        if (this.inventory == null) {
            this.buildInventory();
        }
        return this.inventory;
    }

    private void buildInventory() {
        this.inventory = this.builder.build(M1utilities.getInstance());
        GridInventory gridInventory = this.inventory.query(new QueryOperation[]{QueryOperationTypes.INVENTORY_TYPE.of(GridInventory.class)});
        this.icons.forEach((index, inventoryIcon) -> {
            Slot slot = gridInventory.getSlot(SlotIndex.of(index)).orElseThrow(() -> new IllegalArgumentException("Invalid index: " + index));
            slot.set(inventoryIcon.getDisplay());
        });
    }
}

