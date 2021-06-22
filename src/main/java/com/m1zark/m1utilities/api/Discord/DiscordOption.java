/*
 * Decompiled with CFR 0.151.
 */
package com.m1zark.m1utilities.api.Discord;

import com.m1zark.m1utilities.api.Discord.Field;
import java.awt.Color;
import java.util.List;
import java.util.Map;

public class DiscordOption {
    private final String content;
    private final Color color;
    private final String title;
    private final String description;
    private final List<String> webhookChannels;
    private final String username;
    private final String avatar_url;
    private final List<Field> fields;
    private final String thumbnail;
    private final String image;
    private final Map<String, String> footer;
    private boolean timestamp;

    DiscordOption(String content, Color color, String title, String description, List<String> webhookChannels, String username, String avatar_url, List<Field> fields, String thumbnail, String image, Map<String, String> footer, boolean timestamp) {
        this.content = content;
        this.color = color;
        this.title = title;
        this.description = description;
        this.webhookChannels = webhookChannels;
        this.username = username;
        this.avatar_url = avatar_url;
        this.fields = fields;
        this.thumbnail = thumbnail;
        this.image = image;
        this.footer = footer;
        this.timestamp = timestamp;
    }

    public static DiscordOptionBuilder builder() {
        return new DiscordOptionBuilder();
    }

    public String getContent() {
        return this.content;
    }

    public Color getColor() {
        return this.color;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getWebhookChannels() {
        return this.webhookChannels;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public String getImage() {
        return this.image;
    }

    public Map<String, String> getFooter() {
        return this.footer;
    }

    public boolean isTimestamp() {
        return this.timestamp;
    }

    public static class DiscordOptionBuilder {
        private String content;
        private Color color;
        private String title;
        private String description;
        private List<String> webhookChannels;
        private String username;
        private String avatar_url;
        private List<Field> fields;
        private String thumbnail;
        private String image;
        private Map<String, String> footer;
        private boolean timestamp;

        DiscordOptionBuilder() {
        }

        public DiscordOptionBuilder content(String content) {
            this.content = content;
            return this;
        }

        public DiscordOptionBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public DiscordOptionBuilder title(String title) {
            this.title = title;
            return this;
        }

        public DiscordOptionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DiscordOptionBuilder webhookChannels(List<String> webhookChannels) {
            this.webhookChannels = webhookChannels;
            return this;
        }

        public DiscordOptionBuilder username(String username) {
            this.username = username;
            return this;
        }

        public DiscordOptionBuilder avatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
            return this;
        }

        public DiscordOptionBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }

        public DiscordOptionBuilder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public DiscordOptionBuilder image(String image) {
            this.image = image;
            return this;
        }

        public DiscordOptionBuilder footer(Map<String, String> footer) {
            this.footer = footer;
            return this;
        }

        public DiscordOptionBuilder timestamp(boolean timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public DiscordOption build() {
            return new DiscordOption(this.content, this.color, this.title, this.description, this.webhookChannels, this.username, this.avatar_url, this.fields, this.thumbnail, this.image, this.footer, this.timestamp);
        }

        public String toString() {
            return "DiscordOption.DiscordOptionBuilder(content=" + this.content + ", color=" + this.color + ", title=" + this.title + ", description=" + this.description + ", webhookChannels=" + this.webhookChannels + ", username=" + this.username + ", avatar_url=" + this.avatar_url + ", fields=" + this.fields + ", thumbnail=" + this.thumbnail + ", image=" + this.image + ", footer=" + this.footer + ", timestamp=" + this.timestamp + ")";
        }
    }
}

