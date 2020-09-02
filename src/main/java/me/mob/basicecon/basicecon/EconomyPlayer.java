package me.mob.basicecon.basicecon;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class EconomyPlayer implements Listener {

    private final BasicEcon plugin = BasicEcon.getInstance;
    private final EconomyImplementer eIm = plugin.economyImplementer;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String username = player.getName().toLowerCase();
        if (!plugin.offlinePlayers.containsKey(username)) {
            UUID uuid = player.getUniqueId();
            plugin.offlinePlayers.put(username, uuid);
        }
        if (!eIm.hasAccount(player)) {
            eIm.createPlayerAccount(player);
        }
    }
}
