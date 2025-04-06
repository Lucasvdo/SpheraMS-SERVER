package br.com.spherams.net.server.channel.handlers;

import br.com.spherams.client.Character;
import br.com.spherams.client.Client;
import br.com.spherams.client.QuestStatus;
import br.com.spherams.client.inventory.Inventory;
import br.com.spherams.client.inventory.InventoryType;
import br.com.spherams.client.inventory.manipulator.InventoryManipulator;
import br.com.spherams.net.AbstractPacketHandler;
import br.com.spherams.net.packet.InPacket;
import br.com.spherams.server.ItemInformationProvider;
import br.com.spherams.server.ItemInformationProvider.QuestConsItem;
import br.com.spherams.server.quest.Quest;
import br.com.spherams.tools.PacketCreator;

import java.util.Map;

/**
 * @author Xari
 * @author Ronan - added concurrency protection and quest progress limit
 */
public class RaiseIncExpHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        byte inventorytype = p.readByte();//nItemIT
        short slot = p.readShort();//nSlotPosition
        int itemid = p.readInt();//nItemID

        if (c.tryacquireClient()) {
            try {
                ItemInformationProvider ii = ItemInformationProvider.getInstance();
                QuestConsItem consItem = ii.getQuestConsumablesInfo(itemid);
                if (consItem == null) {
                    return;
                }

                int infoNumber = consItem.questid;
                Map<Integer, Integer> consumables = consItem.items;

                Character chr = c.getPlayer();
                Quest quest = Quest.getInstanceFromInfoNumber(infoNumber);
                if (!chr.getQuest(quest).getStatus().equals(QuestStatus.Status.STARTED)) {
                    c.sendPacket(PacketCreator.enableActions());
                    return;
                }

                int consId;
                Inventory inv = chr.getInventory(InventoryType.getByType(inventorytype));
                inv.lockInventory();
                try {
                    consId = inv.getItem(slot).getItemId();
                    if (!consumables.containsKey(consId) || !chr.haveItem(consId)) {
                        return;
                    }

                    InventoryManipulator.removeFromSlot(c, InventoryType.getByType(inventorytype), slot, (short) 1, false, true);
                } finally {
                    inv.unlockInventory();
                }

                int questid = quest.getId();
                int nextValue = Math.min(consumables.get(consId) + c.getAbstractPlayerInteraction().getQuestProgressInt(questid, infoNumber), consItem.exp * consItem.grade);
                c.getAbstractPlayerInteraction().setQuestProgress(questid, infoNumber, nextValue);

                c.sendPacket(PacketCreator.enableActions());
            } finally {
                c.releaseClient();
            }
        }
    }
}
