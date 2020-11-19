package main;

import events.GeneralEvents;
import events.JoinQuitEvent;
import exec.FreezeExec;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import util.inventory.InventoryManager;

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
    }

    /**
     * Register all commands for BrawlBox
     */
    private void registerCommands(){
        getCommand("freeze").setExecutor(new FreezeExec());
    }

}
