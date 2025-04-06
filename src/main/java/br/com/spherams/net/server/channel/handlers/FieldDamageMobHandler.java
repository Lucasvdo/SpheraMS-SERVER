/*
    This file is part of the HeavenMS MapleStory Server
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
package br.com.spherams.net.server.channel.handlers;

import br.com.spherams.client.Character;
import br.com.spherams.client.Client;
import br.com.spherams.constants.game.GameConstants;
import br.com.spherams.net.AbstractPacketHandler;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.server.life.Monster;
import br.com.spherams.server.life.MonsterInformationProvider;
import br.com.spherams.server.maps.MapleMap;
import br.com.spherams.tools.PacketCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldDamageMobHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(FieldDamageMobHandler.class);

    @Override
    public final void handlePacket(InPacket p, Client c) {
        int mobOid = p.readInt();    // packet structure found thanks to Darter (Rajan)
        int dmg = p.readInt();

        Character chr = c.getPlayer();
        MapleMap map = chr.getMap();

        if (map.getEnvironment().isEmpty()) {   // no environment objects activated to actually hit the mob
            log.warn("Chr {} tried to use an obstacle on mapid {} to attack", c.getPlayer().getName(), map.getId());
            return;
        }

        Monster mob = map.getMonsterByOid(mobOid);
        if (mob != null) {
            if (dmg < 0 || dmg > GameConstants.MAX_FIELD_MOB_DAMAGE) {
                log.warn("Chr {} tried to use an obstacle on mapid {} to attack {} with damage {}", c.getPlayer().getName(),
                        map.getId(), MonsterInformationProvider.getInstance().getMobNameFromId(mob.getId()), dmg);
                return;
            }

            map.broadcastMessage(chr, PacketCreator.damageMonster(mobOid, dmg), true);
            map.damageMonster(chr, mob, dmg);
        }
    }
}
