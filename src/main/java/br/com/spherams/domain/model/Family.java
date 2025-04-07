package br.com.spherams.domain.model;

import br.com.spherams.client.FamilyEntry;
import br.com.spherams.net.server.Server;
import br.com.spherams.net.server.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Family {
    private static final AtomicInteger familyIDCounter = new AtomicInteger();

    private final Map<Integer, FamilyEntry> members = new ConcurrentHashMap<>();

    private final int id, world;
    private FamilyEntry leader;
    private String name;
    private String preceptsMessage = "";
    private int totalGenerations;

    public Family(int id, int world) {
        int newId = id;
        if (id == -1) {
            // get next available family id
            while (idInUse(newId = familyIDCounter.incrementAndGet())) {
            }
        }
        this.id = newId;
        this.world = world;
    }

    private static boolean idInUse(int id) {
        for (World world : Server.getInstance().getWorlds()) {
            if (world.getFamily(id) != null) {
                return true;
            }
        }
        return false;
    }


}
