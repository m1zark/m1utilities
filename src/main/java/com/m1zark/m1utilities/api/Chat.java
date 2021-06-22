/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.spongepowered.api.Sponge
 *  org.spongepowered.api.command.CommandSource
 *  org.spongepowered.api.text.Text
 *  org.spongepowered.api.text.serializer.TextSerializers
 */
package com.m1zark.m1utilities.api;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class Chat {
    public static void sendServerWideMessage(String message) {
        if (message != null && !message.equals("")) {
            Sponge.getServer().getBroadcastChannel().send(Text.of(Chat.embedColours(message)));
        }
    }

    public static void sendBroadcastMessage(CommandSource src, String message) {
        Sponge.getServer().getOnlinePlayers().forEach(player -> {
            if (player != src) {
                player.sendMessage(Text.of(Chat.embedColours(message)));
            }
        });
    }

    public static void sendMessage(CommandSource src, String message) {
        if (message != null && !message.equals("")) {
            src.sendMessage(Text.of(Chat.embedColours(message)));
        }
    }

    public static void sendMessage(CommandSource src, Text message) {
        if (message != null && !message.equals("")) {
            src.sendMessage(message);
        }
    }

    public static Text embedColours(String str) {
        return TextSerializers.FORMATTING_CODE.deserialize(str);
    }
}

