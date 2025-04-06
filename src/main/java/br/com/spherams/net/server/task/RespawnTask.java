package br.com.spherams.net.server.task;

import br.com.spherams.net.server.PlayerStorage;
import br.com.spherams.net.server.Server;
import br.com.spherams.net.server.channel.Channel;
import br.com.spherams.server.maps.MapManager;

/**
 * @author Resinate
 */
public class RespawnTask implements Runnable {

    @Override
    public void run() {
        for (Channel ch : Server.getInstance().getAllChannels()) {
            PlayerStorage ps = ch.getPlayerStorage();
            if (ps != null) {
                if (!ps.getAllCharacters().isEmpty()) {
                    MapManager mapManager = ch.getMapFactory();
                    if (mapManager != null) {
                        mapManager.updateMaps();
                    }
                }
            }
        }
    }
}
