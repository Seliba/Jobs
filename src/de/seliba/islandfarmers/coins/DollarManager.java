package de.seliba.islandfarmers.coins;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.Main;
import org.bukkit.entity.Player;

public class DollarManager {

    public static long getDollars(Player player) {
        return Main.getDollar(player);
    }

    public static void setDollars(Player player, long value) {
        Main.setDollar(player, value);
    }

    public static void addDollars(Player player, long value) {
        setDollars(player, getDollars(player) + value);
        Main.getPlayers().save();
    }

    public static void removeDollars(Player player, long value) {
        setDollars(player, getDollars(player) - value);
        Main.getPlayers().save();
    }

    public static boolean hasDollars(Player player, long value) {
        return getDollars(player) >= value;
    }

}
