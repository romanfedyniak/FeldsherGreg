package com.feldsher.feldshergreg.item;

public class ItemDust extends BaseMetaItem {
    private static ItemDust instance = new ItemDust();
    
    private ItemDust() {
    }

    public static ItemDust getInstance() {
        return instance;
    }

    public String name() {
        return "item_dust";
    }
}
