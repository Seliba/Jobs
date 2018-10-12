package de.seliba.islandfarmers.commands;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.Main;
import de.seliba.islandfarmers.jobs.JobManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDjobs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("jobs")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                player.openInventory(JobManager.getJobsInventory());
            } else {
                sender.sendMessage(Main.getPrefix() + "§cDu musst ein Spieler sein, um dies zu tun!");
            }
        }
        return true;
    }
}
