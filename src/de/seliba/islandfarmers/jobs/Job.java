package de.seliba.islandfarmers.jobs;

/*
IslandFarmers by Seliba
*/

import org.bukkit.Material;

public enum Job {

    MINER("Minenarbeiter", "Verdiene Dollar mit dem", "Abbauen von Erzen", Material.IRON_PICKAXE),
    LUMBERJACK("Holzfäller", "Verdiene Dollar mit dem", "Fällen von Bäumen", Material.IRON_AXE),
    MOLE("Maulwurf", "Verdiene Dollar mit dem", "Abbauen von Erde, Sand und Kies", Material.IRON_SPADE),
    BAKER("Bäcker", "Verdiene Dollar mit dem", "Backen von Brot, Kuchen und Keksen", Material.BREAD),
    HUNTER("Jäger", "Verdiene Dollar mit dem", "Töten von Tieren", Material.IRON_SWORD),
    FISHERMAN("Fischer", "Verdiene Dollar mit dem Fischen", null, Material.FISHING_ROD),
    FARMER("Farmer", "Verdiene Dollar mit dem", "Abernten von Weizen", Material.HAY_BLOCK),
    ENCHANTER("Zauberer", "Verdiene Dollar mit dem", "Brauen von Tränken und dem Verzaubern von Items", Material.ENCHANTED_BOOK),
    SMELTER("Itemschmelzer", "Verdiene Dollar mit dem", "Schmelzen von Erzen", Material.FURNACE);

    private String name, description1, description2;
    private Material material;

    Job(String name, String description1, String description2, Material material) {
        this.name = name;
        this.description1 = description1;
        this.description2 = description2;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public String getDescription1() {
        return description1;
    }

    public String getDescription2() {
        return description2;
    }

    public Material getMaterial() {
        return material;
    }

}
