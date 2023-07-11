package com.feldsher.feldshergreg.loaders;

import com.feldsher.feldshergreg.FeldsherGregsItems;
import ic2.core.Ic2Items;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;

public final class RecipeLoader {
    public static void load() {
        long bits = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry1, bits, new Object[]{"WWW", "EME", "CCC", 'M', Ic2Items.advminer, 'W', OrePrefixes.frameGt.get(Materials.Titanium), 'E', OrePrefixes.circuit.get(Materials.Data), 'C', ItemList.Electric_Motor_EV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry2, bits, new Object[]{"WWW", "EME", "CCC", 'M', FeldsherGregsItems.quarry1, 'W', OrePrefixes.frameGt.get(Materials.TungstenSteel), 'E', OrePrefixes.circuit.get(Materials.Elite), 'C', ItemList.Electric_Motor_IV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry3, bits, new Object[]{"WWW", "EME", "CCC", 'M', FeldsherGregsItems.quarry2, 'W', OrePrefixes.frameGt.get(Materials.Osmiridium), 'E', OrePrefixes.circuit.get(Materials.Master), 'C', ItemList.Electric_Motor_LuV});
        GT_ModHandler.addCraftingRecipe(FeldsherGregsItems.quarry4, bits, new Object[]{"WWW", "EME", "CCC", 'M', FeldsherGregsItems.quarry3, 'W', OrePrefixes.frameGt.get(Materials.Tritanium), 'E', OrePrefixes.circuit.get(Materials.Ultimate), 'C', ItemList.Electric_Motor_ZPM});
    } 
}