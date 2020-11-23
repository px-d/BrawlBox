package events;

import main.BrawlBox;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class ShieldEvents implements Listener {

    @EventHandler
    public void onHit(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.getInventory().getItemInMainHand().getType() == Material.SHIELD) {
                if (p.isBlocking()) {
                    Bukkit.getScheduler().runTaskLater(BrawlBox.INSTANCE, x -> {
                        p.getInventory().setHeldItemSlot(new Random().nextInt(9));
                    }, 5);
                }
            }
        }
    }
}
