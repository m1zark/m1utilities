/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.data.key.Keys
 *  org.spongepowered.api.data.type.SkullTypes
 *  org.spongepowered.api.item.ItemTypes
 *  org.spongepowered.api.item.inventory.ItemStack
 *  org.spongepowered.api.profile.GameProfile
 *  org.spongepowered.api.profile.property.ProfileProperty
 *  org.spongepowered.api.text.Text
 */
package com.m1zark.m1utilities.api.GUI.Icons;

import com.m1zark.m1utilities.api.GUI.Icons.EnumTextures;
import java.util.List;
import java.util.UUID;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.SkullTypes;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.profile.property.ProfileProperty;
import org.spongepowered.api.text.Text;

public class SharedIcons {
    public static ItemStack createSkull(UUID uuid, Text name, List<Text> lore) {
        return ItemStack.builder().itemType(ItemTypes.SKULL).add(Keys.DISPLAY_NAME, name).add(Keys.ITEM_LORE, lore).add(Keys.SKULL_TYPE, SkullTypes.PLAYER).add(Keys.REPRESENTED_PLAYER, GameProfile.of(uuid)).build();
    }

    public static ItemStack createSkull(EnumTextures texture, Text name, List<Text> lore) {
        GameProfile profile = GameProfile.of(UUID.randomUUID());
        profile.addProperty(ProfileProperty.of("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv" + texture.value));
        return ItemStack.builder().itemType(ItemTypes.SKULL).add(Keys.DISPLAY_NAME, name).add(Keys.ITEM_LORE, lore).add(Keys.SKULL_TYPE, SkullTypes.PLAYER).add(Keys.REPRESENTED_PLAYER, profile).build();
    }
}

