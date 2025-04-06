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
package br.com.spherams.client.creator.novice;

import br.com.spherams.client.Client;
import br.com.spherams.client.Job;
import br.com.spherams.client.creator.CharacterFactory;
import br.com.spherams.client.creator.CharacterFactoryRecipe;
import br.com.spherams.client.inventory.InventoryType;
import br.com.spherams.constants.id.ItemId;
import br.com.spherams.constants.id.MapId;

/**
 * @author RonanLana
 */
public class BeginnerCreator extends CharacterFactory {

    private static CharacterFactoryRecipe createRecipe(Job job, int level, int map, int top, int bottom, int shoes, int weapon) {
        CharacterFactoryRecipe recipe = new CharacterFactoryRecipe(job, level, map, top, bottom, shoes, weapon);
        giveItem(recipe, ItemId.BEGINNERS_GUIDE, 1, InventoryType.ETC);
        return recipe;
    }

    private static void giveItem(CharacterFactoryRecipe recipe, int itemid, int quantity, InventoryType itemType) {
        recipe.addStartingItem(itemid, quantity, itemType);
    }

    public static int createCharacter(Client c, String name, int face, int hair, int skin, int top, int bottom, int shoes, int weapon, int gender) {
        return createNewCharacter(c, name, face, hair, skin, gender, createRecipe(Job.BEGINNER, 1, MapId.MUSHROOM_TOWN, top, bottom, shoes, weapon));
    }
}
