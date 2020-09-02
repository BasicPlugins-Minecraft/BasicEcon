package me.mob.basicecon.basicecon;

import java.util.UUID;

public class PlayerInfo {

    private final BasicEcon plugin = BasicEcon.getInstance;
    private final EconomyImplementer eIm = plugin.economyImplementer;

    public UUID getPlayerUUID(String username) {
        /* JSONObject puuid = Unirest.get("https://mc-heads.net/minecraft/profile/{user}")
                .routeParam("user", username)
                .asJson()
                .getBody()
                .getObject()
                .getJSONObject("id");

        String suuid = puuid.toString();
        UUID pluid = UUID.fromString(suuid);*/
        String lusr = username.toLowerCase();
        if (plugin.offlinePlayers.containsKey(lusr)) {
            return plugin.offlinePlayers.get(lusr);
        }
        return null;
    }
}
