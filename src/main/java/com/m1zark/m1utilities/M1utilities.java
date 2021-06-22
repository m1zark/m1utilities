/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.google.inject.Inject
 *  ninja.leaping.configurate.commented.CommentedConfigurationNode
 *  ninja.leaping.configurate.loader.ConfigurationLoader
 *  org.slf4j.Logger
 *  org.spongepowered.api.Sponge
 *  org.spongepowered.api.command.source.ConsoleSource
 *  org.spongepowered.api.config.DefaultConfig
 *  org.spongepowered.api.event.Listener
 *  org.spongepowered.api.event.Order
 *  org.spongepowered.api.event.game.GameReloadEvent
 *  org.spongepowered.api.event.game.state.GamePreInitializationEvent
 *  org.spongepowered.api.event.service.ChangeServiceProviderEvent
 *  org.spongepowered.api.plugin.Plugin
 *  org.spongepowered.api.plugin.PluginContainer
 *  org.spongepowered.api.scheduler.SpongeExecutorService
 *  org.spongepowered.api.service.economy.EconomyService
 *  org.spongepowered.api.text.Text
 */
package com.m1zark.m1utilities;

import com.google.inject.Inject;
import com.m1zark.m1utilities.M1Config;
import com.m1zark.m1utilities.api.Discord.DiscordNotifier;
import com.m1zark.m1utilities.api.IDiscordNotifier;
import java.io.File;
import java.util.Optional;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.service.ChangeServiceProviderEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.SpongeExecutorService;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.text.Text;

@Plugin(id="m1utilities", name="M1utilities", version="1.0.5", description="Common classes for m1zarks plugins", authors={"m1zark"})
public class M1utilities {
    private static M1utilities instance;
    @Inject
    private PluginContainer pluginContainer;
    @Inject
    private Logger logger;
    @Inject
    @DefaultConfig(sharedRoot=true)
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Inject
    @DefaultConfig(sharedRoot=true)
    private File configFile;
    private EconomyService economy;
    private SpongeExecutorService asyncExecutorService;
    private IDiscordNotifier discordNotifier;

    @Listener(order=Order.EARLY)
    public void onPreInit(GamePreInitializationEvent e) {
        instance = this;
        M1Config.init(this.configLoader, this.configFile);
        this.asyncExecutorService = Sponge.getScheduler().createAsyncExecutor(this);
        this.discordNotifier = new DiscordNotifier();
        this.getConsole().ifPresent(console -> console.sendMessage(Text.of("M1Utils loaded...")));
    }

    @Listener
    public void onReload(GameReloadEvent event) {
        M1Config.loadConfig();
    }

    @Listener
    public void registerServices(ChangeServiceProviderEvent e) {
        if (e.getService().equals(EconomyService.class)) {
            this.economy = (EconomyService)e.getNewProviderRegistration().getProvider();
        }
    }

    public static M1utilities getInstance() {
        return instance;
    }

    public Optional<ConsoleSource> getConsole() {
        return Optional.ofNullable(Sponge.isServerAvailable() ? Sponge.getServer().getConsole() : null);
    }

    public Optional<IDiscordNotifier> getDiscordNotifier() {
        return Optional.ofNullable(this.discordNotifier);
    }

    public PluginContainer getPluginContainer() {
        return this.pluginContainer;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ConfigurationLoader<CommentedConfigurationNode> getConfigLoader() {
        return this.configLoader;
    }

    public File getConfigFile() {
        return this.configFile;
    }

    public EconomyService getEconomy() {
        return this.economy;
    }

    public SpongeExecutorService getAsyncExecutorService() {
        return this.asyncExecutorService;
    }
}

