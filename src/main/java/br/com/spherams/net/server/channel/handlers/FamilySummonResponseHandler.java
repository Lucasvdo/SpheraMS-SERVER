package br.com.spherams.net.server.channel.handlers;

import br.com.spherams.client.Character;
import br.com.spherams.client.Client;
import br.com.spherams.client.FamilyEntitlement;
import br.com.spherams.client.FamilyEntry;
import br.com.spherams.config.YamlConfig;
import br.com.spherams.net.AbstractPacketHandler;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.net.server.coordinator.world.InviteCoordinator;
import br.com.spherams.net.server.coordinator.world.InviteCoordinator.InviteResult;
import br.com.spherams.net.server.coordinator.world.InviteCoordinator.InviteResultType;
import br.com.spherams.net.server.coordinator.world.InviteCoordinator.InviteType;
import br.com.spherams.server.maps.MapleMap;
import br.com.spherams.tools.PacketCreator;

public class FamilySummonResponseHandler extends AbstractPacketHandler {

    @Override
    public void handlePacket(InPacket p, Client c) {
        if (!YamlConfig.config.server.USE_FAMILY_SYSTEM) {
            return;
        }
        p.readString(); //family name
        boolean accept = p.readByte() != 0;
        InviteResult inviteResult = InviteCoordinator.answerInvite(InviteType.FAMILY_SUMMON, c.getPlayer().getId(), c.getPlayer(), accept);
        if (inviteResult.result == InviteResultType.NOT_FOUND) {
            return;
        }
        Character inviter = inviteResult.from;
        FamilyEntry inviterEntry = inviter.getFamilyEntry();
        if (inviterEntry == null) {
            return;
        }
        MapleMap map = (MapleMap) inviteResult.params[0];
        if (accept && inviter.getMap() == map) { //cancel if inviter has changed maps
            c.getPlayer().changeMap(map, map.getPortal(0));
        } else {
            inviterEntry.refundEntitlement(FamilyEntitlement.SUMMON_FAMILY);
            inviterEntry.gainReputation(FamilyEntitlement.SUMMON_FAMILY.getRepCost(), false); //refund rep cost if declined
            inviter.sendPacket(PacketCreator.getFamilyInfo(inviterEntry));
            inviter.dropMessage(5, c.getPlayer().getName() + " has denied the summon request.");
        }
    }

}
