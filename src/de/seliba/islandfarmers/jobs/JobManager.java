package de.seliba.islandfarmers.jobs;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.List;

public class JobManager {

    public static boolean hasJob(Player player, Job job) {
        if(hasJob(player)) {
            return getJob(player) == job;
        } else {
            return false;
        }
    }

    public static boolean hasJob(Player player) {
        return getJob(player) != null;
    }

    public static void setJob(Player player, Job job) {
        Main.getPlugin().getPlayers().set(player.getUniqueId() + ".job", job.name());
        Main.getPlugin().getPlayers().save();
    }

    public static Job getJob(Player player) {
        if(Main.getPlayers().getString(player.getUniqueId() + ".job") != null) {
            return Job.valueOf(Main.getPlayers().getString(player.getUniqueId() + ".job"));
        } else {
            return null;
        }
    }


    public static String getDescription(Job job) {
        if(job.getDescription2() == null) return job.getDescription1();
        return job.getDescription1() + " " + job.getDescription2();
    }

    public static String getName(Job job) {
        return job.getName();
    }

    public static Inventory getJobsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, "§cJobs");

        ItemStack hunter = new ItemStack(Job.HUNTER.getMaterial());
        ItemStack miner = new ItemStack(Job.MINER.getMaterial());
        ItemStack lumberjack = new ItemStack(Job.LUMBERJACK.getMaterial());
        ItemStack mole = new ItemStack(Job.MOLE.getMaterial());
        ItemStack smelter = new ItemStack(Job.SMELTER.getMaterial());
        ItemStack farmer = new ItemStack(Job.FARMER.getMaterial());
        ItemStack baker = new ItemStack(Job.BAKER.getMaterial());
        ItemStack fisherman = new ItemStack(Job.FISHERMAN.getMaterial());
        ItemStack enchanter = new ItemStack(Job.ENCHANTER.getMaterial());
        ItemStack glas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);

        ItemMeta hunterMeta = hunter.getItemMeta();
        ItemMeta minerMeta = miner.getItemMeta();
        ItemMeta lumberjackMeta = lumberjack.getItemMeta();
        ItemMeta moleMeta = mole.getItemMeta();
        ItemMeta smelterMeta = smelter.getItemMeta();
        ItemMeta farmerMeta = farmer.getItemMeta();
        ItemMeta bakerMeta = baker.getItemMeta();
        ItemMeta fishermanMeta = fisherman.getItemMeta();
        ItemMeta enchanterMeta = enchanter.getItemMeta();
        ItemMeta glasMeta = glas.getItemMeta();

        hunterMeta.setDisplayName("§6" + Job.HUNTER.getName());
        minerMeta.setDisplayName("§6" + Job.MINER.getName());
        lumberjackMeta.setDisplayName("§6" + Job.LUMBERJACK.getName());
        moleMeta.setDisplayName("§6" + Job.MOLE.getName());
        smelterMeta.setDisplayName("§6" + Job.SMELTER.getName());
        farmerMeta.setDisplayName("§6" + Job.FARMER.getName());
        bakerMeta.setDisplayName("§6" + Job.BAKER.getName());
        fishermanMeta.setDisplayName("§6" + Job.FISHERMAN.getName());
        enchanterMeta.setDisplayName("§6" + Job.ENCHANTER.getName());
        glasMeta.setDisplayName(" ");

        hunterMeta.setLore(getLore(Job.HUNTER));
        minerMeta.setLore(getLore(Job.MINER));
        lumberjackMeta.setLore(getLore(Job.LUMBERJACK));
        moleMeta.setLore(getLore(Job.MOLE));
        smelterMeta.setLore(getLore(Job.SMELTER));
        farmerMeta.setLore(getLore(Job.FARMER));
        bakerMeta.setLore(getLore(Job.BAKER));
        fishermanMeta.setLore(getLore(Job.FISHERMAN));
        enchanterMeta.setLore(getLore(Job.ENCHANTER));

        hunter.setItemMeta(hunterMeta);
        miner.setItemMeta(minerMeta);
        lumberjack.setItemMeta(lumberjackMeta);
        mole.setItemMeta(moleMeta);
        smelter.setItemMeta(smelterMeta);
        farmer.setItemMeta(farmerMeta);
        baker.setItemMeta(bakerMeta);
        fisherman.setItemMeta(fishermanMeta);
        enchanter.setItemMeta(enchanterMeta);
        glas.setItemMeta(glasMeta);

        for(int i = 0; i < 9; i++) {
            inventory.setItem(i, glas);
        }
        inventory.setItem(9, hunter);
        inventory.setItem(10, miner);
        inventory.setItem(11, lumberjack);
        inventory.setItem(12, mole);
        inventory.setItem(13, smelter);
        inventory.setItem(14, farmer);
        inventory.setItem(15, baker);
        inventory.setItem(16, fisherman);
        inventory.setItem(17, enchanter);
        for(int i = 18; i < 27; i++) {
            inventory.setItem(i, glas);
        }

        return inventory;
    }

    private static List<String> getLore(Job job) {
        List<String> list = new ArrayList<>();
        list.add("§a" + job.getDescription1());
        if(job.getDescription2() != null) list.add("§a" + job.getDescription2());
        list.add(" ");
        list.add("§6Linksklick für Jobwechsel §7(§61000 Dollar§7)");
        return list;
    }

}
