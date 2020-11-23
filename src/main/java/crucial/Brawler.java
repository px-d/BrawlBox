package crucial;

import enums.Teams;
import main.BrawlBox;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import util.ValueHolder;

public class Brawler {

    private Player player;
    private int kills = 0;
    private int coins = 300;

    private Teams team = Teams.NONE;

    public boolean isAlive = true;

    public Brawler(Player player) {
        this.player = player;
    }

    public String getStringTeam() {

        if (team == Teams.BLUE) {
            return ValueHolder.tacc("&9Blue");
        } else if (team == Teams.RED) {
            return ValueHolder.tacc("&cRed");
        } else {
            return ValueHolder.tacc("&7&oRandom");
        }
    }

    public Teams getTeam() {
        return team;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCoins() {
        return coins;
    }

    public int getKills() {
        return kills;
    }

    public void setTeam(Teams red) {
        this.team = red;
    }

    public void addKill() {
        this.kills += 1;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public boolean canBuy(int amount) {
        return coins >= amount ? true : false;
    }

    public void buy(int amount) {
        if (coins >= amount) {
            coins -= amount;
        }
    }

    public void kill() {
        player.getInventory().clear();
        player.setHealth(20.0);
        isAlive = false;

        switch (team) {
            case BLUE:
                player.teleport(ValueHolder.blue_prep);
                break;
            case RED:
                player.teleport(ValueHolder.red_prep);
                break;
            default:
                Bukkit.broadcastMessage(ValueHolder.tacc("&cERROR CODE 003"));
                break;
        }
    }

    public void heal() {
        player.setHealth(20);
    }


}
