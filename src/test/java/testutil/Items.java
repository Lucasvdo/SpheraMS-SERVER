package testutil;

import br.com.spherams.client.inventory.Item;

public class Items {

    public static Item itemWithQuantity(int itemId, int quantity) {
        return new Item(itemId, AnyValues.anyShort(), (short) quantity);
    }
}
