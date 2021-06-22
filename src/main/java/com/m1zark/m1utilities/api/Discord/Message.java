/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package com.m1zark.m1utilities.api.Discord;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.m1zark.m1utilities.api.Discord.Embed;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Message {
    private List<Embed> embeds = Lists.newArrayList();
    private String username;
    private String avatar_url;
    private String content;
    private transient List<String> webhook_url;

    public Message(List<String> channels, String username, String avatar, String content) {
        this.webhook_url = channels;
        this.username = username;
        this.avatar_url = avatar;
        this.content = content;
    }

    public Message addEmbed(Embed embed) {
        this.embeds.add(embed);
        return this;
    }

    HttpsURLConnection send(String url) throws Exception {
        HttpsURLConnection connection = (HttpsURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        String json = this.getJsonString();
        connection.setRequestProperty("Content-length", String.valueOf(json.length()));
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
        dos.write(json.getBytes("UTF-8"));
        dos.flush();
        dos.close();
        return connection;
    }

    String getJsonString() {
        JsonObject json = new JsonObject();
        if (this.username != null) {
            json.addProperty("username", this.username);
        }
        if (this.avatar_url != null) {
            json.addProperty("avatar_url", this.avatar_url);
        }
        if (this.content != null) {
            json.addProperty("content", this.content);
        }
        if (!this.embeds.isEmpty()) {
            JsonArray jArray = new JsonArray();
            for (Embed embed : this.embeds) {
                jArray.add(embed.getJson());
            }
            json.add("embeds", jArray);
        }
        return json.toString();
    }

    public List<String> getWebhooks() {
        return this.webhook_url;
    }
}

