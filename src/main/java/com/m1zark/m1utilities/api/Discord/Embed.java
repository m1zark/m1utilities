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
import com.m1zark.m1utilities.api.Discord.Field;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Embed {
    private List<Field> fields = Lists.newArrayList();
    private int color;
    private String title;
    private String description;
    private String thumbnail = null;
    private String image = null;
    private Map<String, String> footer = new HashMap<String, String>();
    private boolean timestamp = false;

    public Embed(int color, String title, String description) {
        this.color = color;
        this.title = title;
        this.description = description;
    }

    public Embed addField(Field field) {
        this.fields.add(field);
        return this;
    }

    public Embed addThumbnail(String name) {
        this.thumbnail = name;
        return this;
    }

    public Embed addImage(String name) {
        this.image = name;
        return this;
    }

    public Embed addFooter(Map<String, String> footer) {
        this.footer = footer;
        return this;
    }

    public Embed addTimestamp(boolean timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public JsonObject getJson() {
        JsonObject json = new JsonObject();
        json.addProperty("color", this.color);
        if (this.title != null) {
            json.addProperty("title", this.title);
        }
        if (this.description != null) {
            json.addProperty("description", this.description);
        }
        if (!this.fields.isEmpty()) {
            JsonArray jsonArray = new JsonArray();
            for (Field field : this.fields) {
                jsonArray.add(field.getJson());
            }
            json.add("fields", jsonArray);
        }
        if (this.thumbnail != null) {
            JsonObject thumb = new JsonObject();
            thumb.addProperty("url", this.thumbnail);
            json.add("thumbnail", thumb);
        }
        if (this.image != null) {
            JsonObject image = new JsonObject();
            image.addProperty("url", this.image);
            json.add("image", image);
        }
        if (!this.footer.isEmpty()) {
            JsonObject footer = new JsonObject();
            footer.addProperty("text", this.footer.get("text"));
            footer.addProperty("icon_url", this.footer.get("icon"));
            json.add("footer", footer);
        }
        if (this.timestamp) {
            json.addProperty("timestamp", Instant.now().toString());
        }
        return json;
    }
}

