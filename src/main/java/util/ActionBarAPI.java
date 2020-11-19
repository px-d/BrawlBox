package util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBarAPI {

    public static void sendABToAll(String msg){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ValueHolder.tacc(msg)));
        }
    }

}
