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
package br.com.spherams.server.quest.requirements;

import br.com.spherams.client.Character;
import br.com.spherams.client.inventory.Pet;
import br.com.spherams.provider.Data;
import br.com.spherams.provider.DataTool;
import br.com.spherams.server.quest.Quest;
import br.com.spherams.server.quest.QuestRequirementType;

/**
 * @author Tyler (Twdtwd)
 */
public class MinTamenessRequirement extends AbstractQuestRequirement {
    private int minTameness;


    public MinTamenessRequirement(Quest quest, Data data) {
        super(QuestRequirementType.MIN_PET_TAMENESS);
        processData(data);
    }

    /**
     * @param data
     */
    @Override
    public void processData(Data data) {
        minTameness = DataTool.getInt(data);
    }


    @Override
    public boolean check(Character chr, Integer npcid) {
        int curTameness = 0;

        for (Pet pet : chr.getPets()) {
            if (pet == null) {
                continue;
            }

            if (pet.getTameness() > curTameness) {
                curTameness = pet.getTameness();
            }
        }

        return curTameness >= minTameness;
    }
}
