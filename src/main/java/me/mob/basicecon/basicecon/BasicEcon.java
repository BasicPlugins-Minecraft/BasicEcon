package me.mob.basicecon.basicecon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class BasicEcon extends JavaPlugin {

    //public static Logger logger = Logger.getLogger("Minecraft");
    public static BasicEcon getInstance;
    public EconomyImplementer economyImplementer;
    public PlayerInfo playerInfo;
    private VaultHook vaultHook;
    public EconomyStorage economyStorage;

    public HashMap<UUID, Double> playerBank = new HashMap<>();
    public HashMap<String, UUID> offlinePlayers = new HashMap<>();

    @Override
    public void onEnable() {
        instanceClasses();
        vaultHook.hook();
        economyStorage = new EconomyStorage(this);

        this.getCommand("send").setExecutor(new EconomyCommands());
        this.getCommand("balance").setExecutor(new EconomyCommands());
        this.getCommand("addcash").setExecutor(new EconomyCommands());
        this.getCommand("setcash").setExecutor(new EconomyCommands());
        this.getCommand("removecash").setExecutor(new EconomyCommands());
        Bukkit.getPluginManager().registerEvents(new EconomyPlayer(), this);
    }

    private void instanceClasses() {
        getInstance = this;
        economyImplementer = new EconomyImplementer();
        playerInfo = new PlayerInfo();
        vaultHook = new VaultHook();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        vaultHook.unhook();
        economyStorage.savepB();
        economyStorage.saveoP();

    }
}
