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
import br.com.spherams.provider.Data;
import br.com.spherams.provider.DataTool;
import br.com.spherams.server.quest.Quest;
import br.com.spherams.server.quest.QuestActionType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tyler (Twdtwd)
 */
public class QuestAction extends AbstractQuestAction {
    int mesos;
    Map<Integer, Integer> quests = new HashMap<>();

    public QuestAction(Quest quest, Data data) {
        super(QuestActionType.QUEST, quest);
        questID = quest.getId();
        processData(data);
    }


    @Override
    public void processData(Data data) {
        for (Data qEntry : data) {
            int questid = DataTool.getInt(qEntry.getChildByPath("id"));
            int stat = DataTool.getInt(qEntry.getChildByPath("state"));
            quests.put(questid, stat);
        }
    }

    @Override
    public void run(Character chr, Integer extSelection) {
        for (Integer questID : quests.keySet()) {
            int stat = quests.get(questID);
            chr.updateQuestStatus(new QuestStatus(Quest.getInstance(questID), QuestStatus.Status.getById(stat)));
        }
    }
} 
