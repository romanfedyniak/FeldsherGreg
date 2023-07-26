package com.feldsher.feldshergreg.item;

public class ItemIngot extends BaseMetaItem {
    private static ItemIngot instance = new ItemIngot();
    
    private ItemIngot() {
    }

    public static ItemIngot getInstance() {
        return instance;
    }

    public String name() {
        return "item_ingot";
    }
}
