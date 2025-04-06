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
package br.com.spherams.server.quest.requirements;

import br.com.spherams.client.Character;
import br.com.spherams.provider.Data;
import br.com.spherams.provider.DataTool;
import br.com.spherams.server.quest.Quest;
import br.com.spherams.server.quest.QuestRequirementType;

/**
 * @author Ronan
 */
public class BuffExceptRequirement extends AbstractQuestRequirement {
    private int buffId = -1;

    public BuffExceptRequirement(Quest quest, Data data) {
        super(QuestRequirementType.BUFF);
        processData(data);
    }

    @Override
    public void processData(Data data) {
        // item buffs are negative
        buffId = -1 * Integer.parseInt(DataTool.getString(data));
    }

    @Override
    public boolean check(Character chr, Integer npcid) {
        return !chr.hasBuffFromSourceid(buffId);
    }
}
