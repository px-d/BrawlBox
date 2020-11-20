package main;

import events.*;
import exec.FreezeExec;
import exec.IExec;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import util.inventory.InventoryManager;

import java.util.Objects;

public class BrawlBox extends JavaPlugin {

    public static BrawlBox INSTANCE;
    public static GameManager gm;

    public InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        gm = new GameManager();
        inventoryManager = new InventoryManager(this);
        inventoryManager.init();
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Register all events for BrawlBox
     */
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitEvent(), this);
        pluginManager.registerEvents(new GeneralEvents(), this);
        pluginManager.registerEvents(new CombatEvents(), this);
        pluginManager.registerEvents(new AxeEvents(), this);
        pluginManager.registerEvents(new ExplosiveBowEvent(), this);
        pluginManager.registerEvents(new SafetyPlatformEvents(), this);
    }

    /**
     * Register all commands for BrawlBox
     */
    private void registerCommands() {
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeExec());
        Objects.requireNonNull(getCommand("i")).setExecutor(new IExec());
    }

}
