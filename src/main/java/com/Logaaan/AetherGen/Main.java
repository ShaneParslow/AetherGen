package com.Logaaan.AetherGen;

import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin implements Listener {

    public boolean aether;
    public boolean goodies;
    public int ao;
    public double sa;
    public int ah = 150;
    public double sp;
    public double f;
    public double a;
    public double cave_radius;
    public int cave_rarity;
    public int cave_frequency;
    public int cave_min;
    public int cave_max;
    public int cave_individual_rarity;
    public int cave_pocket_chance;
    public int dung;
    public int treechance;
    public int coalc;
    public int coals;
    public boolean coal;
    public int ironc;
    public int irons;
    public boolean iron;
    public int goldc;
    public int golds;
    public boolean gold;
    public int lapisc;
    public int lapiss;
    public boolean lapis;
    public int diac;
    public int dias;
    public boolean dia;
    public int dirtc;
    public int dirts;
    public boolean dirt;
    public int gravelc;
    public int gravels;
    public boolean gravel;
    public int redc;
    public int reds;
    public boolean red;
    public int emec;
    public int emes;
    public boolean eme;
    public int bigtree;
    public int pinecount;
    public int normalcount;
    public int junglecount;
    public int birchcount;
    public int dungcount;
    public boolean features;

    public void onEnable() {
        if (!this.getConfig().isSet("aether")) {
            this.getConfig().set("aether", true);
            saveConfig();
        }
        if (this.getConfig().isSet("prefab_tree_normal_count")) {
            normalcount = getConfig().getInt("prefab_tree_normal_count");
            if (normalcount <= 1) {
                normalcount = 2;
                getServer().getLogger().info("prefab_tree_normal_count had invalid value (lesser or equal to 1), setting to 2");
            }
        } else {
            getConfig().set("prefab_tree_normal_count", 4);
            normalcount = 4;
            saveConfig();
        }
        if (this.getConfig().isSet("prefab_tree_pine_count")) {
            pinecount = getConfig().getInt("prefab_tree_pine_count");
            if (pinecount <= 1) {
                pinecount = 2;
                getServer().getLogger().info("prefab_tree_pine_count had invalid value (lesser or equal to 1), setting to 2");
            }
        } else {
            getConfig().set("prefab_tree_pine_count", 4);
            pinecount = 4;
            saveConfig();
        }
        if (this.getConfig().isSet("prefab_tree_jungle_count")) {
            junglecount = getConfig().getInt("prefab_tree_jungle_count");
            if (junglecount <= 1) {
                junglecount = 2;
                getServer().getLogger().info("prefab_tree_jungle_count had invalid value (lesser or equal to 1), setting to 2");
            }
        } else {
            getConfig().set("prefab_tree_jungle_count", 3);
            junglecount = 3;
            saveConfig();
        }
        if (this.getConfig().isSet("prefab_tree_birch_count")) {
            birchcount = getConfig().getInt("prefab_tree_birch_count");
            if (birchcount <= 1) {
                birchcount = 2;
                getServer().getLogger().info("prefab_tree_birch_count had invalid value (lesser or equal to 1), setting to 2");
            }
        } else {
            getConfig().set("prefab_tree_birch_count", 4);
            birchcount = 4;
            saveConfig();
        }
        if (this.getConfig().isSet("prefab_dungeon_count")) {
            dungcount = getConfig().getInt("prefab_dungeon_count");
            if (dungcount <= 1) {
                dungcount = 2;
                getServer().getLogger().info("prefab_dungeon_count had invalid value (lesser or equal to 1), setting to 2");
            }
        } else {
            getConfig().set("prefab_dungeon_count", 2);
            dungcount = 2;
            saveConfig();
        }
        if (this.getConfig().isSet("generate_additional_features")) {
            coal = getConfig().getBoolean("generate_additional_features");
        } else {
            getConfig().set("generate_additional_features", true);
            features = true;
            saveConfig();
        }
        if (this.getConfig().isSet("generate_coal")) {
            coal = getConfig().getBoolean("generate_coal");
        } else {
            getConfig().set("generate_coal", true);
            coal = true;
            saveConfig();
        }
        if (this.getConfig().isSet("coal_chance")) {
            coalc = getConfig().getInt("coal_chance");
        } else {
            getConfig().set("coal_chance", 500);
            coalc = 500;
            saveConfig();
        }
        if (this.getConfig().isSet("coal_amount")) {
            coals = getConfig().getInt("coal_amount");
        } else {
            getConfig().set("coal_amount", 7);
            coals = 7;
            saveConfig();
        }

        if (this.getConfig().isSet("bigtree_chance")) {
            bigtree = getConfig().getInt("bigtree_chance");
        } else {
            getConfig().set("bigtree_chance", 1000);
            bigtree = 1000;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_iron")) {
            iron = getConfig().getBoolean("generate_iron");
        } else {
            getConfig().set("generate_iron", true);
            iron = true;
            saveConfig();
        }
        if (this.getConfig().isSet("iron_chance")) {
            ironc = getConfig().getInt("iron_chance");
        } else {
            getConfig().set("iron_chance", 2000);
            ironc = 2000;
            saveConfig();
        }
        if (this.getConfig().isSet("iron_amount")) {
            irons = getConfig().getInt("iron_amount");
        } else {
            getConfig().set("iron_amount", 5);
            irons = 5;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_lapis")) {
            lapis = getConfig().getBoolean("generate_lapis");
        } else {
            getConfig().set("generate_lapis", true);
            lapis = true;
            saveConfig();
        }
        if (this.getConfig().isSet("lapis_chance")) {
            lapisc = getConfig().getInt("lapis_chance");
        } else {
            getConfig().set("lapis_chance", 4000);
            lapisc = 4000;
            saveConfig();
        }
        if (this.getConfig().isSet("lapis_amount")) {
            lapiss = getConfig().getInt("lapis_amount");
        } else {
            getConfig().set("lapis_amount", 2);
            lapiss = 2;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_redstone")) {
            red = getConfig().getBoolean("generate_redstone");
        } else {
            getConfig().set("generate_redstone", true);
            red = true;
            saveConfig();
        }
        if (this.getConfig().isSet("redstone_chance")) {
            redc = getConfig().getInt("redstone_chance");
        } else {
            getConfig().set("redstone_chance", 4000);
            redc = 4000;
            saveConfig();
        }
        if (this.getConfig().isSet("redstone_amount")) {
            reds = getConfig().getInt("redstone_amount");
        } else {
            getConfig().set("redstone_amount", 4);
            reds = 4;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_diamonds")) {
            dia = getConfig().getBoolean("generate_diamonds");
        } else {
            getConfig().set("generate_diamonds", true);
            dia = true;
            saveConfig();
        }
        if (this.getConfig().isSet("diamond_chance")) {
            diac = getConfig().getInt("diamond_chance");
        } else {
            getConfig().set("diamond_chance", 10000);
            diac = 10000;
            saveConfig();
        }
        if (this.getConfig().isSet("diamond_amount")) {
            dias = getConfig().getInt("diamond_amount");
        } else {
            getConfig().set("diamond_amount", 3);
            dias = 3;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_emeralds")) {
            eme = getConfig().getBoolean("generate_emeralds");
        } else {
            getConfig().set("generate_emeralds", true);
            eme = true;
            saveConfig();
        }
        if (this.getConfig().isSet("emerald_chance")) {
            emec = getConfig().getInt("emerald_chance");
        } else {
            getConfig().set("emerald_chance", 6000);
            emec = 6000;
            saveConfig();
        }
        if (this.getConfig().isSet("emerald_amount")) {
            emes = getConfig().getInt("emerald_amount");
        } else {
            getConfig().set("emerald_amount", 1);
            emes = 1;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_gold")) {
            gold = getConfig().getBoolean("generate_gold");
        } else {
            getConfig().set("generate_gold", true);
            gold = true;
            saveConfig();
        }
        if (this.getConfig().isSet("gold_chance")) {
            goldc = getConfig().getInt("gold_chance");
        } else {
            getConfig().set("gold_chance", 4000);
            goldc = 4000;
            saveConfig();
        }
        if (this.getConfig().isSet("gold_amount")) {
            golds = getConfig().getInt("gold_amount");
        } else {
            getConfig().set("gold_amount", 4);
            golds = 4;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_dirt")) {
            dirt = getConfig().getBoolean("generate_dirt");
        } else {
            getConfig().set("generate_dirt", true);
            dirt = true;
            saveConfig();
        }
        if (this.getConfig().isSet("dirt_chance")) {
            dirtc = getConfig().getInt("dirt_chance");
        } else {
            getConfig().set("dirt_chance", 900);
            dirtc = 900;
            saveConfig();
        }
        if (this.getConfig().isSet("dirt_amount")) {
            dirts = getConfig().getInt("dirt_amount");
        } else {
            getConfig().set("dirt_amount", 15);
            dirts = 15;
            saveConfig();
        }


        if (this.getConfig().isSet("generate_gravel")) {
            gravel = getConfig().getBoolean("generate_gravel");
        } else {
            getConfig().set("generate_gravel", true);
            gravel = true;
            saveConfig();
        }
        if (this.getConfig().isSet("gravel_chance")) {
            gravelc = getConfig().getInt("gravel_chance");
        } else {
            getConfig().set("gravel_chance", 1900);
            gravelc = 1900;
            saveConfig();
        }
        if (this.getConfig().isSet("gravel_amount")) {
            gravels = getConfig().getInt("gravel_amount");
        } else {
            getConfig().set("gravel_amount", 15);
            gravels = 15;
            saveConfig();
        }

        if (!this.getConfig().isSet("cave_rarity")) {
            this.getConfig().set("cave_rarity", 3);
            saveConfig();
        }
        if (!this.getConfig().isSet("dungeon_chance")) {
            this.getConfig().set("dungeon_chance", 10);
            saveConfig();
        }

        if (!this.getConfig().isSet("cave_frequency")) {
            this.getConfig().set("cave_frequency", 35);
            saveConfig();
        }
        if (!this.getConfig().isSet("cave_min_y")) {
            this.getConfig().set("cave_min_y", 1);
            saveConfig();
        }
        if (!this.getConfig().isSet("cave_max_y")) {
            this.getConfig().set("cave_max_y", 200);
            saveConfig();
        }
        if (!this.getConfig().isSet("cave_individual_rarity")) {
            this.getConfig().set("cave_individual_rarity", 20);
            saveConfig();
        }
        if (!this.getConfig().isSet("cave_pocket_chance")) {
            this.getConfig().set("cave_pocket_chance", 3);
            saveConfig();
        }
        if (!this.getConfig().isSet("cave_radius_multiplier")) {
            this.getConfig().set("cave_radius_multiplier", 1.1);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_goodies")) {
            this.getConfig().set("aether_goodies", false);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_height")) {
            this.getConfig().set("aether_height", 150);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_spawnrate")) {
            this.getConfig().set("aether_spawnrate", 0.1);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_frequency")) {
            this.getConfig().set("aether_frequency", 1.45D);
            saveConfig();
        }

        if (!this.getConfig().isSet("aether_amplitude")) {
            this.getConfig().set("aether_amplitude", 0.95D);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_octaves")) {
            this.getConfig().set("aether_octaves", 7);
            saveConfig();
        }
        if (!this.getConfig().isSet("aether_scale")) {
            this.getConfig().set("aether_scale", 0.0337);
            saveConfig();
        }
        if (!this.getConfig().isSet("general_tree_chance")) {
            this.getConfig().set("general_tree_chance", 50);
            saveConfig();
        } else {
            this.treechance = this.getConfig().getInt("general_tree_chance");
        }
        if (this.getConfig().getBoolean("aether")) {
            aether = true;
            ah = this.getConfig().getInt("aether_height");
            goodies = this.getConfig().getBoolean("aether_gooies");
            ao = this.getConfig().getInt("aether_octaves");
            sa = this.getConfig().getDouble("aether_scale");
            sp = this.getConfig().getDouble("aether_spawnrate");
            f = this.getConfig().getDouble("aether_frequency");
            a = this.getConfig().getDouble("aether_amplitude");
        } else {
            aether = false;
        }
        if (this.getConfig().isSet("cave_rarity")) {
            cave_rarity = getConfig().getInt("cave_rarity");
            cave_frequency = getConfig().getInt("cave_frequency");
            cave_min = getConfig().getInt("cave_min_y");
            cave_max = getConfig().getInt("cave_max_y");
            cave_individual_rarity = getConfig().getInt("cave_individual_rarity");
            cave_pocket_chance = getConfig().getInt("cave_individual_chance");
            cave_radius = getConfig().getDouble("cave_radius");
        }
        if (this.getConfig().isSet("dungeon_chance")) {
            dung = getConfig().getInt("dungeon_chance");
        }

        TeleportRunnable tr = new TeleportRunnable();
        this.getServer().getPluginManager().registerEvents(tr, this);

        // Timer to teleport players who drop out of aether
		tr.runTaskTimer(this, 0L, 10L);
    }

    public void onDisable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new AetherGen(worldName, this);
    }
}
