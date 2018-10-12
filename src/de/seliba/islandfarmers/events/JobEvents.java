package de.seliba.islandfarmers.events;

/*
IslandFarmers by Seliba
*/

import de.seliba.islandfarmers.Main;
import de.seliba.islandfarmers.coins.DollarManager;
import de.seliba.islandfarmers.jobs.Job;
import de.seliba.islandfarmers.jobs.JobManager;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.material.Crops;

public class JobEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Material material = event.getCurrentItem().getType();
        if(event.getInventory().getName().equals("§cJobs")) {
            event.setCancelled(true);
            for(Job job : Job.values()) {
                if(job.getMaterial().equals(material)) {
                    if(JobManager.hasJob(player, job)) {
                        player.closeInventory();
                        player.sendMessage(Main.getPrefix() + "§cDu bist bereits " + job.getName() + "!");
                    } else if(JobManager.hasJob(player)) {
                        if(DollarManager.hasDollars(player, 1000L)) {
                            DollarManager.removeDollars(player, 1000L);
                            player.closeInventory();
                            JobManager.setJob(player, job);
                            player.sendMessage(Main.getPrefix() + "§7Du bist nun §6" + job.getName() + "§7!");
                        } else {
                            player.closeInventory();
                            player.sendMessage(Main.getPrefix() + "§cDu benötigst 1000 Münzen, um den Job zu wechseln!");
                        }
                    } else {
                        player.closeInventory();
                        JobManager.setJob(player, job);
                        player.sendMessage(Main.getPrefix() + "§7Du bist nun §6" + job.getName() + "§7!");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material material = event.getBlock().getType();
        if(JobManager.hasJob(player)) {
            if(JobManager.hasJob(player, Job.LUMBERJACK)) {
                if(material.equals(Material.LOG) || material.equals(Material.LOG_2)) {
                    DollarManager.addDollars(player, 3L);
                    player.sendMessage(Main.getPrefix() + "§7+§63 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                }
            } else if(JobManager.hasJob(player, Job.MOLE)) {
                if(material.equals(Material.SAND) || material.equals(Material.DIRT) || material.equals(Material.GRASS) || material.equals(Material.GRAVEL) || material.equals(Material.CLAY)) {
                    DollarManager.addDollars(player, 1L);
                    player.sendMessage(Main.getPrefix() + "§7+§61 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                }
            } else if(JobManager.hasJob(player, Job.MINER)) {
                if(material == Material.STONE || material == Material.COBBLESTONE) {
                    DollarManager.addDollars(player, 1L);
                    player.sendMessage(Main.getPrefix() + "§7+§61 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.COAL_ORE) {
                    DollarManager.addDollars(player, 5L);
                    player.sendMessage(Main.getPrefix() + "§7+§65 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.IRON_ORE) {
                    DollarManager.addDollars(player, 20L);
                    player.sendMessage(Main.getPrefix() + "§7+§620 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.GOLD_ORE) {
                    DollarManager.addDollars(player, 30L);
                    player.sendMessage(Main.getPrefix() + "§7+§630 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.LAPIS_ORE) {
                    DollarManager.addDollars(player, 30L);
                    player.sendMessage(Main.getPrefix() + "§7+§630 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.REDSTONE_ORE) {
                    DollarManager.addDollars(player, 15L);
                    player.sendMessage(Main.getPrefix() + "§7+§615 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.DIAMOND_ORE) {
                    DollarManager.addDollars(player, 50L);
                    player.sendMessage(Main.getPrefix() + "§7+§650 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(material == Material.EMERALD_ORE) {
                    DollarManager.addDollars(player, 100L);
                    player.sendMessage(Main.getPrefix() + "§7+§6100 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                }
            } else if(JobManager.hasJob(player, Job.FARMER)) {
                if(material == Material.CROPS) {
                    Crops crops = (Crops) event.getBlock().getState().getData();
                    if(crops.getState() == CropState.RIPE) {
                        DollarManager.addDollars(player, 100);
                        player.sendMessage(Main.getPrefix() + "§7+§6100 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getType() == InventoryType.WORKBENCH && event.getSlotType() == InventoryType.SlotType.RESULT) {
            if(JobManager.hasJob(player, Job.BAKER)) {
                if(event.getCurrentItem().getType() == Material.BREAD) {
                    DollarManager.addDollars(player, event.getCurrentItem().getAmount() * 200);
                    player.sendMessage(Main.getPrefix() + "§7+§6" + event.getCurrentItem().getAmount() * 200 + " Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(event.getCurrentItem().getType() == Material.COOKIE) {
                    DollarManager.addDollars(player, event.getCurrentItem().getAmount() * 400);
                    player.sendMessage(Main.getPrefix() + "§7+§6" + event.getCurrentItem().getAmount() * 400 + " Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                } else if(event.getCurrentItem().getType() == Material.CAKE) {
                    DollarManager.addDollars(player, event.getCurrentItem().getAmount() * 500);
                    player.sendMessage(Main.getPrefix() + "§7+§6" + event.getCurrentItem().getAmount() * 500 + " Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
                }
            }
        } else if(event.getInventory().getType() == InventoryType.FURNACE && event.getSlotType() == InventoryType.SlotType.RESULT) {
            if(JobManager.hasJob(player, Job.SMELTER)) {
                DollarManager.addDollars(player, event.getCurrentItem().getAmount() * 10);
                player.sendMessage(Main.getPrefix() + "§7+§6" + event.getCurrentItem().getAmount() * 10 + " Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
            }
        }
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if(player != null) {
            if(JobManager.hasJob(player, Job.HUNTER)) {
                DollarManager.addDollars(player, 25);
                player.sendMessage(Main.getPrefix() + "§7+§625 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
            }
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if(JobManager.hasJob(player, Job.FISHERMAN)) {
                DollarManager.addDollars(player, 25);
                player.sendMessage(Main.getPrefix() + "§7+§625 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
            }
        }
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        Player player = event.getEnchanter();
        if(JobManager.hasJob(player, Job.ENCHANTER)) {
            DollarManager.addDollars(player, 100);
            player.sendMessage(Main.getPrefix() + "§7+§6100 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
        }
    }

    @EventHandler
    public void onBrew(BrewEvent event) {
        Player player = (Player) event.getContents().getViewers().get(0);
        if(JobManager.hasJob(player, Job.ENCHANTER)) {
            DollarManager.addDollars(player, 50);
            player.sendMessage(Main.getPrefix() + "§7+§650 Dollar §7(§6Gesamt§7: §6" + DollarManager.getDollars(player) + "§7)");
        }
    }

}
