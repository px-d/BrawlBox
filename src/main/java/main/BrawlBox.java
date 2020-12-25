package main;

import enums.State;
import events.*;
import exec.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import util.inventory.InventoryManager;

import java.io.DataOutputStream;
import java.util.Objects;

public class BrawlBox extends JavaPlugin {

    public static BrawlBox INSTANCE;
    public static GameManager gm;

    public InventoryManager inventoryManager;

    @Override
    public void onEnable() {

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

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

    }

    /**
     * Register all events for BrawlBox
     */
    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitEvent(), this);
        pluginManager.registerEvents(new FragEvents(), this);
        pluginManager.registerEvents(new GeneralEvents(), this);
        pluginManager.registerEvents(new CombatEvents(), this);
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
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnExec());
        Objects.requireNonNull(getCommand("rounds")).setExecutor(new SetRoundExec());
        Objects.requireNonNull(getCommand("lang")).setExecutor(new LangExec());
    }

    public void sendPlayerToServer(Player player, String serverName) {
        ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytearray);
        try {
            out.writeUTF("Connect");
            out.writeUTF(serverName);
        } catch (Exception e) {
        }
        player.sendPluginMessage(this, "BungeeCord", bytearray.toByteArray());
    }

}
