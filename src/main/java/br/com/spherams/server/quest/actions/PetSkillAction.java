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
package br.com.spherams.server.quest.actions;

import br.com.spherams.client.Character;
import br.com.spherams.client.QuestStatus;
import br.com.spherams.constants.inventory.ItemConstants;
import br.com.spherams.provider.Data;
import br.com.spherams.provider.DataTool;
import br.com.spherams.server.quest.Quest;
import br.com.spherams.server.quest.QuestActionType;

/**
 * @author Tyler (Twdtwd)
 */
public class PetSkillAction extends AbstractQuestAction {
    int flag;

    public PetSkillAction(Quest quest, Data data) {
        super(QuestActionType.PETSKILL, quest);
        questID = quest.getId();
        processData(data);
    }


    @Override
    public void processData(Data data) {
        flag = DataTool.getInt("petskill", data);
    }

    @Override
    public boolean check(Character chr, Integer extSelection) {
        QuestStatus status = chr.getQuest(Quest.getInstance(questID));
        if (!(status.getStatus() == QuestStatus.Status.NOT_STARTED && status.getForfeited() > 0)) {
            return false;
        }

        return chr.getPet(0) != null;
    }

    @Override
    public void run(Character chr, Integer extSelection) {
        chr.getPet(0).setFlag((byte) ItemConstants.getFlagByInt(flag));
    }
} 
