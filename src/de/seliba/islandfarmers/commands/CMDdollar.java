package de.seliba.islandfarmers.commands;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.Main;
import de.seliba.islandfarmers.coins.DollarManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDdollar implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("dollar")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length == 0) {
                    player.sendMessage(Main.getPrefix() + "§7Du besitzt aktuell §6" + DollarManager.getDollars(player) + " §7Dollar!");
                } else if(args.length == 1 || args.length == 2) {
                    player.sendMessage(Main.getPrefix() + "§cBitte benutze §6/dollar <set / add / remove> <Spieler> <Anzahl>!");
                } else {
                    if(player.hasPermission("skyfarmers.admin")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if(target != null) {
                            try {
                                long amount = Long.valueOf(args[2]);
                                if(args[0].equalsIgnoreCase("set")) {
                                    DollarManager.setDollars(target, amount);
                                    player.sendMessage(Main.getPrefix() + "§7Du hast §6" + target.getName() + " §7erfolgreich §6" + amount + " §7Dollar gesetzt!");
                                } else if(args[0].equalsIgnoreCase("add")) {
                                    DollarManager.addDollars(target, amount);
                                    player.sendMessage(Main.getPrefix() + "§7Du hast §6" + target.getName() + " §7erfolgreich §6" + amount + " §7Dollar gegeben!");
                                } else if(args[0].equalsIgnoreCase("remove")) {
                                    DollarManager.removeDollars(target, amount);
                                    player.sendMessage(Main.getPrefix() + "§7Du hast §6" + target.getName() + " §7erfolgreich §6" + amount + " §7Dollar entfernt!");
                                }
                            } catch(NumberFormatException ex) {
                                player.sendMessage(Main.getPrefix() + "§cBitte gebe eine gültige Zahl ein!");
                            }
                        } else {
                            player.sendMessage(Main.getPrefix() + "§cDieser Spieler ist nicht online!");
                        }
                    } else {
                        player.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!");
                    }
                }
            } else {
                sender.sendMessage(Main.getPrefix() + "§cDu musst ein Spieler sein, um dies zu tun!");
            }
        }
        return true;
    }
}
