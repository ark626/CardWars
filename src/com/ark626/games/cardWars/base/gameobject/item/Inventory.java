package com.ark626.games.cardWars.base.gameobject.item;

import org.slf4j.Logger;
import com.ark626.games.cardWars.base.game.LoggingUtil;

public class Inventory {

    private ItemSlot[] items;
    private static final Logger LOG = LoggingUtil.getLogger(Inventory.class);


    public Inventory(int size) {
        items = new ItemSlot[size];
    }

    public void add(ItemSlot itemSlot) {

        int whereIsItem = checkWhereTheItemIs(itemSlot.getItem());
        if (whereIsItem != -1) {
            items[whereIsItem].mergeItems(itemSlot);
            return;
        }
        int nextEmptySpace = checkWhereNextFreePlaceIs();
        if (nextEmptySpace != -1) {
            items[nextEmptySpace] = itemSlot;
            return;
        }

        LOG.error(
                "Error occured while adding item with name: " + itemSlot.getItem()
                        .getName() + " to the Inventory");

    }



    /**
     * returns the place in the Inventory where the item is If it isnt in inventory it returns -1
     * 
     * @param item
     * @return
     */
    private int checkWhereTheItemIs(Item item) {

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getItem()
                    .getName()
                    .equals(item.getName())) {
                return i;
            }
        }
        return -1;

    }

    /**
     * returns the next place in the Inventory which is free If inventory is full returns -1
     * 
     * @param item
     * @return
     */
    private int checkWhereNextFreePlaceIs() {

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null || items[i].getAmount() < 1) {
                return i;
            }
        }
        return -1;

    }

}
