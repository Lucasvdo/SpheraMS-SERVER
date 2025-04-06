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
import br.com.spherams.config.YamlConfig;
import br.com.spherams.net.AbstractPacketHandler;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.tools.PacketCreator;

/**
 * @author Ubaware
 */
public final class OpenFamilyHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_FAMILY_SYSTEM) {
            return;
        }
        Character chr = c.getPlayer();
        c.sendPacket(PacketCreator.getFamilyInfo(chr.getFamilyEntry()));
    }
}

