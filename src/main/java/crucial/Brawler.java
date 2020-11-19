package crucial;

import enums.Teams;
import main.BrawlBox;
import org.bukkit.entity.Player;
import util.ValueHolder;

public class Brawler {

    private Player player;
    private int kills = 0;
    private int coins = 300;

    private Teams team = Teams.NONE;


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
}
