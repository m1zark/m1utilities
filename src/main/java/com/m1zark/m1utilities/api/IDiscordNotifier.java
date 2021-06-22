/*
 * Decompiled with CFR 0.151.
 */
package com.m1zark.m1utilities.api;

import com.m1zark.m1utilities.api.Discord.DiscordOption;
import com.m1zark.m1utilities.api.Discord.Message;
import java.util.concurrent.CompletableFuture;

public interface IDiscordNotifier {
    public Message forgeMessage(DiscordOption var1);

    public CompletableFuture<Void> sendMessage(Message var1);
}

