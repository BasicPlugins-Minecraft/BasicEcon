package me.mob.basicecon.basicecon;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class EconomyImplementer implements Economy {

    private final BasicEcon plugin = BasicEcon.getInstance;

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "BasicEcon";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return String.valueOf(amount);
    }

    @Override
    public String currencyNamePlural() {
        return "bitcoins";
    }

    @Override
    public String currencyNameSingular() {
        return "bitcoin";
    }

    @Override
    public boolean hasAccount(String playerName) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        UUID uuid = player.getUniqueId();
        return plugin.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        return plugin.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        UUID uuid = player.getUniqueId();
        return plugin.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        UUID uuid = player.getUniqueId();
        return plugin.playerBank.containsKey(uuid);
    }

    @Override
    public double getBalance(String playerName) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        UUID uuid = player.getUniqueId();
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        if (uuid != null) {
            if (plugin.playerBank.get(uuid) != null) {
                return plugin.playerBank.get(uuid);
            }
        }
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        if (uuid != null) {
            if (plugin.playerBank.get(uuid) != null) {
                return plugin.playerBank.get(uuid);
            }
        }
        return 0;
    }

    @Override
    public double getBalance(String playerName, String world) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        if (uuid != null) {
            if (plugin.playerBank.get(uuid) != null) {
                return plugin.playerBank.get(uuid);
            }
        }
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        if (uuid != null) {
            if (plugin.playerBank.get(uuid) != null) {
                return plugin.playerBank.get(uuid);
            }
        }
        return 0;
    }

    @Override
    public boolean has(String playerName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double camt = plugin.playerBank.get(uuid);
        return camt == amount;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double camt = plugin.playerBank.get(uuid);
        return camt == amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double camt = plugin.playerBank.get(uuid);
        return camt == amount;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double camt = plugin.playerBank.get(uuid);
        return camt == amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance - amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance - amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance - amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance - amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance + amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance + amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(plugin.offlinePlayers.get(playerName));
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance + amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)) {
            createPlayerAccount(player);
        }
        UUID uuid = player.getUniqueId();
        double oldBalance = plugin.playerBank.get(uuid);
        plugin.playerBank.put(uuid, oldBalance + amount);
        EconomyResponse econ = new EconomyResponse(amount,getBalance(player), EconomyResponse.ResponseType.SUCCESS, "");
        return econ;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        UUID uuid = plugin.offlinePlayers.get(playerName);
        if (!plugin.playerBank.containsKey(uuid)) {
            plugin.playerBank.put(uuid, 100.0);
            return true;
        } else return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        if (!plugin.playerBank.containsKey(uuid)) {
            plugin.playerBank.put(uuid, 100.0);
            return true;
        } else return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        UUID uuid = plugin.offlinePlayers.get(playerName);
        if (!plugin.playerBank.containsKey(uuid)) {
            plugin.playerBank.put(uuid, 100.0);
            return true;
        } else return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        UUID uuid = player.getUniqueId();
        if (!plugin.playerBank.containsKey(uuid)) {
            plugin.playerBank.put(uuid, 100.0);
            return true;
        } else return false;
    }
}
