/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Throwables
 *  joptsimple.internal.Strings
 */
package com.m1zark.m1utilities.api.Discord;

import com.google.common.base.Throwables;
import com.m1zark.m1utilities.M1Config;
import com.m1zark.m1utilities.M1utilities;
import com.m1zark.m1utilities.api.Discord.DiscordOption;
import com.m1zark.m1utilities.api.Discord.Embed;
import com.m1zark.m1utilities.api.Discord.Message;
import com.m1zark.m1utilities.api.IDiscordNotifier;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import javax.net.ssl.HttpsURLConnection;
import joptsimple.internal.Strings;

public class DiscordNotifier
implements IDiscordNotifier {
    @Override
    public Message forgeMessage(DiscordOption option) {
        Embed embeds = new Embed(option.getColor().getRGB() & 0xFFFFFF, option.getTitle(), option.getDescription());
        option.getFields().forEach(embeds::addField);
        if (!Strings.isNullOrEmpty(option.getThumbnail())) {
            embeds.addThumbnail(option.getThumbnail());
        }
        if (!Strings.isNullOrEmpty(option.getImage())) {
            embeds.addImage(option.getImage());
        }
        if (!option.getFooter().isEmpty()) {
            embeds.addFooter(option.getFooter());
        }
        if (option.isTimestamp()) {
            embeds.addTimestamp(option.isTimestamp());
        }
        return new Message(option.getWebhookChannels(), option.getUsername(), option.getAvatar_url(), option.getContent()).addEmbed(embeds);
    }

    @Override
    public CompletableFuture<Void> sendMessage(Message message) {
        return this.makeFuture(() -> {
            List<String> URLS = message.getWebhooks();
            for (String URL2 : URLS) {
                if (M1Config.enableDebug) {
                    M1utilities.getInstance().getLogger().info("[WebHook-Debug] Sending webhook payload to " + URL2);
                }
                if (M1Config.enableDebug) {
                    M1utilities.getInstance().getLogger().info("[WebHook-Debug] Payload: " + message.getJsonString());
                }
                HttpsURLConnection connection = message.send(URL2);
                if (!M1Config.enableDebug) continue;
                M1utilities.getInstance().getLogger().info("[WebHook-Debug] Payload info received, status code: " + connection.getResponseCode());
            }
        });
    }

    private CompletableFuture<Void> makeFuture(ThrowingRunnable runnable) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            }
            catch (Exception e) {
                throw new CompletionException(e);
            }
        }, (Executor)M1utilities.getInstance().getAsyncExecutorService());
    }

    private static interface ThrowingRunnable {
        public void run() throws Exception;
    }
}

