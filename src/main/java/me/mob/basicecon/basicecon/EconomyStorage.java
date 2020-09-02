package me.mob.basicecon.basicecon;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class EconomyStorage {

    public static FileConfiguration data;
    public static File dataFile;

    private static final BasicEcon pl = BasicEcon.getInstance;

    public BasicEcon be;

    public EconomyStorage(BasicEcon be) {
        this.be = be;
        getData();
        loadpB();
        loadoP();
    }

    public static void getData() {
        if (!pl.getDataFolder().exists()) {
            pl.getDataFolder().mkdirs();
        }
        dataFile = new File("plugins/BasicEcon/data.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public void loadpB() {
        ConfigurationSection section = data.getConfigurationSection("playerBank");
        if (section != null) {
            section.getKeys(false).forEach(key -> {
                UUID uuid = UUID.fromString(key);
                double amount = section.getDouble(key);
                pl.playerBank.put(uuid, amount);
            });
        }
    }

    public void loadoP() {
        ConfigurationSection section = data.getConfigurationSection("offlinePlayers");
        if (section != null) {
            section.getKeys(false).forEach(key -> {
                String string1 = key;
                String ts = section.getString(key);
                UUID uuid = UUID.fromString(ts);
                pl.offlinePlayers.put(string1, uuid);
            });
        }
    }

    public void savepB() {
        if (!pl.playerBank.isEmpty()) {
            if (pl.playerBank.size() > 0) {
                pl.playerBank.forEach((key, value) -> data.set("playerBank." + key.toString(), value));
                try {
                    data.save(dataFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveoP() {
        if (!pl.offlinePlayers.isEmpty()) {
            if (pl.offlinePlayers.size() > 0) {
                pl.offlinePlayers.forEach((key, value) -> data.set("offlinePlayers." + key, value.toString()));
                try {
                    data.save(dataFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
