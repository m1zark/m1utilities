/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package com.m1zark.m1utilities.api.Discord;

import com.google.gson.JsonObject;

public class Field {
    private String name;
    private String value;
    private boolean inline;

    public Field(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    public JsonObject getJson() {
        JsonObject json = new JsonObject();
        if (this.name != null) {
            json.addProperty("name", this.name);
        }
        if (this.value != null) {
            json.addProperty("value", this.value);
        }
        if (this.inline) {
            json.addProperty("inline", Boolean.TRUE);
        }
        return json;
    }
}

