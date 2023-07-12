package com.feldsher.feldshergreg.loaders;

import com.feldsher.feldshergreg.FeldsherGregsItems;
import com.feldsher.feldshergreg.item.ItemDust;
import com.feldsher.feldshergreg.item.ItemIngot;

public final class ItemsLoader {
    public static void load() {
        loadItemDust();
        loadItemIngot();
    }

    private static void loadItemDust() {
        ItemDust itemDust = ItemDust.getInstance();

        FeldsherGregsItems.dustSuperconductorBaseLV = itemDust.newItem();
        FeldsherGregsItems.dustSuperconductorBaseMV = itemDust.newItem();
        FeldsherGregsItems.dustSuperconductorBaseHV = itemDust.newItem();
        FeldsherGregsItems.dustSuperconductorBaseEV = itemDust.newItem();
        FeldsherGregsItems.dustSuperconductorBaseIV = itemDust.newItem();

        itemDust.registerItem();
    }

    private static void loadItemIngot() {
        ItemIngot itemIngot = ItemIngot.getInstance();

        FeldsherGregsItems.ingotSuperconductorBaseLV = itemIngot.newItem();
        FeldsherGregsItems.ingotSuperconductorBaseMV = itemIngot.newItem();
        FeldsherGregsItems.ingotSuperconductorBaseHV = itemIngot.newItem();
        FeldsherGregsItems.ingotSuperconductorBaseEV = itemIngot.newItem();
        FeldsherGregsItems.ingotSuperconductorBaseIV = itemIngot.newItem();
    
        itemIngot.registerItem();
    }
}
