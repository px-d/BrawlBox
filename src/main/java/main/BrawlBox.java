package main;

import enums.State;
import events.*;
import exec.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        gm.state = State.END;
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("Bitte join gleich wieder :(");
        }
    }

    /**
     * Register all events for BrawlBox
     */
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitEvent(), this);
        pluginManager.registerEvents(new GeneralEvents(), this);
        pluginManager.registerEvents(new CombatEvents(), this);
//        pluginManager.registerEvents(new AxeEvents(), this);
        pluginManager.registerEvents(new ExplosiveBowEvent(), this);
        pluginManager.registerEvents(new SafetyPlatformEvents(), this);
        pluginManager.registerEvents(new ChatEvents(), this);
        pluginManager.registerEvents(new ShieldEvents(), this);
    }

    /**
     * Register all commands for BrawlBox
     */
    private void registerCommands() {
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeExec());
        Objects.requireNonNull(getCommand("i")).setExecutor(new IExec());
        Objects.requireNonNull(getCommand("start")).setExecutor(new StartExecutor());
        Objects.requireNonNull(getCommand("hub")).setExecutor(new HubExec());
        Objects.requireNonNull(getCommand("rounds")).setExecutor(new SetRoundExec());
    }

}
