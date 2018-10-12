package de.seliba.islandfarmers;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.commands.CMDdollar;
import de.seliba.islandfarmers.commands.CMDjobs;
import de.seliba.islandfarmers.config.Config;
import de.seliba.islandfarmers.events.JobEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Config config, players, messages;
    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("[IslandFarmers] Gestartet!");
        setupConfig();
        setupCommands();
        setupEvents();
    }

    @Override
    public void onDisable() {

    }

    private void setupConfig() {
        config = new Config("config.yml", this);
        players = new Config("players.yml", this);
        messages = new Config("messages.yml", this);

        if(config.getString("prefix") == null) {
            config.set("prefix", "&7[&aIslandFarmers&7]");
            config.save();
        }
        if(!messages.exists()) {
            //Don't work
            messages.save();
        }
        players.save();


    }

    private void setupCommands() {
        getCommand("jobs").setExecutor(new CMDjobs());
        getCommand("dollar").setExecutor(new CMDdollar());
    }

    private void setupEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new JobEvents(), this);
    }

    public static Config getMessages() {
        return messages;
    }

    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + " ";
    }

    public static Config getPlayers() {
        return players;
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static void setDollar(Player player, long value) {
        players.set(player.getUniqueId() + ".dollars", value);
        players.save();
    }

    public static long getDollar(Player player) {
        if(players.get(player.getUniqueId() + ".dollars") != null) {
            return players.getLong(player.getUniqueId() + ".dollars");
        } else {
            setDollar(player, 0L);
            return 0L;
        }
    }

}