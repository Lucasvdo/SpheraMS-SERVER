/*
    This file is part of the HeavenMS MapleStory Server, commands OdinMS-based
    Copyleft (L) 2016 - 2019 RonanLana

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
   @Author: Arthur L - Refactored command content into modules
*/
package br.com.spherams.client.command.commands.gm3;

import br.com.spherams.client.Character;
import br.com.spherams.client.Client;
import br.com.spherams.client.command.Command;
import br.com.spherams.constants.id.MobId;
import br.com.spherams.server.life.Monster;
import br.com.spherams.server.maps.MapObject;
import br.com.spherams.server.maps.MapObjectType;
import br.com.spherams.server.maps.MapleMap;

import java.util.Arrays;
import java.util.List;

public class KillAllCommand extends Command {
    {
        setDescription("Kill all mobs in the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        MapleMap map = player.getMap();
        List<MapObject> monsters = map.getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapObjectType.MONSTER));
        int count = 0;
        for (MapObject monstermo : monsters) {
            Monster monster = (Monster) monstermo;
            if (!monster.getStats().isFriendly() && !(monster.getId() >= MobId.DEAD_HORNTAIL_MIN && monster.getId() <= MobId.HORNTAIL)) {
                map.damageMonster(player, monster, Integer.MAX_VALUE);
                count++;
            }
        }
        player.dropMessage(5, "Killed " + count + " monsters.");
    }
}
