/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.ConfigurationNode
 *  ninja.leaping.configurate.commented.CommentedConfigurationNode
 *  ninja.leaping.configurate.loader.ConfigurationLoader
 */
package com.m1zark.m1utilities;

import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class M1Config {
    private static ConfigurationLoader<CommentedConfigurationNode> loader;
    private static CommentedConfigurationNode node;
    private static File file;
    public static boolean enableDebug;

    public static void init(ConfigurationLoader<CommentedConfigurationNode> loader, File file) {
        M1Config.loader = loader;
        M1Config.file = file;
        M1Config.loadConfig();
    }

    public static void loadConfig() {
        if (!file.exists()) {
            M1Config.saveConfig();
        }
        M1Config.load();
        enableDebug = node.getNode("settings", "enable-debug-messages").getBoolean();
    }

    public static void saveConfig() {
        M1Config.load();
        node.getNode("settings", "enable-debug-messages").setValue(false);
        M1Config.save();
    }

    private static void load() {
        try {
            node = loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void save() {
        try {
            loader.save(node);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

