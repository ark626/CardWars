package com.ark626.games.cardWars.base.gameobject.item;

import org.slf4j.Logger;
import com.ark626.games.cardWars.base.game.LoggingUtil;

public class ItemSlot {
    
    private Item item;
    private int amount;
    private  static final Logger LOG = LoggingUtil.getLogger(ItemSlot.class);
    
    
    public ItemSlot(Item item, int amount) {
        
        this.item = item;
        this.amount = amount;
    }


    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }


    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public void mergeItems(ItemSlot itemSlot) {
        if(itemSlot.item.getName().equals(this.getItem().getName())) {
            this.amount += itemSlot.amount;
            itemSlot.item.remove();
        }
        else {
            LOG.error("Merging tried to merge ItemSlots with different items: "+ this.getItem().getName()+" and "+ itemSlot.item.getName());
        }
    }
    
    

}
