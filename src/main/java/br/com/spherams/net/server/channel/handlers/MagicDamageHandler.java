/*
	This file is part of the OdinMS Maple Story Server
    Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
		       Matthias Butz <matze@odinms.de>
		       Jan Christian Meyer <vimes@odinms.de>

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

import br.com.spherams.client.*;
import br.com.spherams.client.Character;
import br.com.spherams.config.YamlConfig;
import br.com.spherams.constants.id.MapId;
import br.com.spherams.constants.skills.Bishop;
import br.com.spherams.constants.skills.Evan;
import br.com.spherams.constants.skills.FPArchMage;
import br.com.spherams.constants.skills.ILArchMage;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.net.packet.Packet;
import br.com.spherams.server.StatEffect;
import br.com.spherams.tools.PacketCreator;

import static java.util.concurrent.TimeUnit.SECONDS;

public final class MagicDamageHandler extends AbstractDealDamageHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();

		/*long timeElapsed = currentServerTime() - chr.getAutobanManager().getLastSpam(8);
		if(timeElapsed < 300) {
			AutobanFactory.FAST_ATTACK.alert(chr, "Time: " + timeElapsed);
		}
		chr.getAutobanManager().spam(8);*/

        AttackInfo attack = parseDamage(p, chr, false, true);

        if (chr.getBuffEffect(BuffStat.MORPH) != null) {
            if (chr.getBuffEffect(BuffStat.MORPH).isMorphWithoutAttack()) {
                // How are they attacking when the client won't let them?
                chr.getClient().disconnect(false, false);
                return;
            }
        }

        if (MapId.isDojo(chr.getMap().getId()) && attack.numAttacked > 0) {
            chr.setDojoEnergy(chr.getDojoEnergy() + +YamlConfig.config.server.DOJO_ENERGY_ATK);
            c.sendPacket(PacketCreator.getEnergy("energy", chr.getDojoEnergy()));
        }

        int charge = (attack.skill == Evan.FIRE_BREATH || attack.skill == Evan.ICE_BREATH || attack.skill == FPArchMage.BIG_BANG || attack.skill == ILArchMage.BIG_BANG || attack.skill == Bishop.BIG_BANG) ? attack.charge : -1;
        Packet packet = PacketCreator.magicAttack(chr, attack.skill, attack.skilllevel, attack.stance,
                attack.numAttackedAndDamage, attack.targets, charge, attack.speed, attack.direction, attack.display);

        chr.getMap().broadcastMessage(chr, packet, false, true);
        StatEffect effect = attack.getAttackEffect(chr, null);
        Skill skill = SkillFactory.getSkill(attack.skill);
        StatEffect effect_ = skill.getEffect(chr.getSkillLevel(skill));
        if (effect_.getCooldown() > 0) {
            if (chr.skillIsCooling(attack.skill)) {
                return;
            } else {
                c.sendPacket(PacketCreator.skillCooldown(attack.skill, effect_.getCooldown()));
                chr.addCooldown(attack.skill, currentServerTime(), SECONDS.toMillis(effect_.getCooldown()));
            }
        }
        applyAttack(attack, chr, effect.getAttackCount());
        Skill eaterSkill = SkillFactory.getSkill((chr.getJob().getId() - (chr.getJob().getId() % 10)) * 10000);// MP Eater, works with right job
        int eaterLevel = chr.getSkillLevel(eaterSkill);
        if (eaterLevel > 0) {
            for (Integer oid : attack.targets.keySet()) {
                eaterSkill.getEffect(eaterLevel).applyPassive(chr, chr.getMap().getMapObject(oid), 0);
            }
        }
    }
}
